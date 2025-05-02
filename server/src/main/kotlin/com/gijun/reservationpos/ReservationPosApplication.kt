package com.gijun.reservationpos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

/**
 * Main Spring Boot application class for the Reservation POS system.
 * Scans all packages under com.gijun.reservationpos.
 */
@SpringBootApplication
@EnableR2dbcRepositories(basePackages = ["com.gijun.reservationpos.repository"])
class ReservationPosApplication

fun main(args: Array<String>) {
    runApplication<ReservationPosApplication>(*args) {
        // Add any additional runtime configuration here if needed
    }
}
