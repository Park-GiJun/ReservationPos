package com.gijun.reservationpos.infrastructure.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
class R2dbcConfig {

    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        
        val populator = CompositeDatabasePopulator()
        
        // 스크립트에서 오류가 발생해도 계속 진행하도록 설정
        val resourcePopulator = ResourceDatabasePopulator()
        resourcePopulator.addScript(ClassPathResource("schema.sql"))
        resourcePopulator.setContinueOnError(true)
        
        populator.addPopulators(resourcePopulator)
        initializer.setDatabasePopulator(populator)
        
        return initializer
    }
}