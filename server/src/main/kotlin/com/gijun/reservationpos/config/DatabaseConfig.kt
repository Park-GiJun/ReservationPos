package com.gijun.reservationpos.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.r2dbc.core.DatabaseClient

@Configuration
class DatabaseConfig {
    /**
     * Provides a DatabaseClient bean for interacting with the database reactively.
     * Schema initialization is handled by DatabaseSetup component.
     */
    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory): DatabaseClient {
        return DatabaseClient.create(connectionFactory)
    }
}
