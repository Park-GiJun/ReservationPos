package com.gijun.reservationpos.config

import io.r2dbc.spi.ConnectionFactory
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient

/**
 * Database status checker class.
 * This class does not create tables anymore as it's handled by schema.sql and DatabaseConfig.
 */
@Configuration
class DatabaseInitializer {

    private val logger = LoggerFactory.getLogger(DatabaseInitializer::class.java)

    @Bean
    fun checkDatabaseTables(connectionFactory: ConnectionFactory): CommandLineRunner {
        return CommandLineRunner {
            val databaseClient = DatabaseClient.create(connectionFactory)
            
            try {
                logger.info("데이터베이스 테이블 상태 확인")
                
                // Check if test table exists
                databaseClient.sql("SHOW TABLES")
                    .fetch()
                    .all()
                    .doOnNext { row -> 
                        val tableName = row.values.firstOrNull()
                        logger.info("테이블 발견: $tableName")
                    }
                    .blockLast()
                
                logger.info("데이터베이스 확인 완료")
                
            } catch (e: Exception) {
                logger.error("데이터베이스 확인 중 오류 발생", e)
            }
        }
    }
}