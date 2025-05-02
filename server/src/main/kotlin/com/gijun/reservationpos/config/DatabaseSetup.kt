package com.gijun.reservationpos.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.r2dbc.connection.init.ScriptUtils
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

/**
 * Database setup component that initializes the database schema
 * after Spring Boot has fully started.
 */
@Component
class DatabaseSetup(private val databaseClient: DatabaseClient) {
    
    private val logger = LoggerFactory.getLogger(DatabaseSetup::class.java)
    
    /**
     * Initialize database after Spring Boot application has fully started.
     * This approach executes the schema.sql file in a controlled manner.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun initializeDatabase() {
        logger.info("Initializing database schema...")
        
        try {
            // Read schema.sql file content
            val resource = ClassPathResource("schema.sql")
            val schemaContent = resource.inputStream.readAllBytes().toString(StandardCharsets.UTF_8)
            
            // Split the schema into individual statements
            val statements = schemaContent.split(";")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
            
            // Execute each statement separately
            statements.forEachIndexed { index, statement ->
                logger.debug("Executing SQL statement #{}: {}", index + 1, statement)
                
                databaseClient.sql(statement)
                    .fetch()
                    .rowsUpdated()
                    .doOnError { error ->
                        logger.warn("Error executing statement #{}: {}", index + 1, error.message)
                    }
                    .onErrorResume { Mono.just(0L) }
                    .subscribe(
                        { count -> logger.debug("Statement #{} executed, affected rows: {}", index + 1, count) },
                        { error -> logger.error("Failed to execute statement #{}: {}", index + 1, error.message) }
                    )
            }
            
            logger.info("Database schema initialization completed")
        } catch (e: Exception) {
            logger.error("Error during database initialization: {}", e.message, e)
        }
    }
}
