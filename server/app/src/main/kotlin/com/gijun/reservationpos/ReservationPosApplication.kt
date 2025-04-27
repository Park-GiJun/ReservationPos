package com.gijun.reservationpos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication(
    scanBasePackages = [
        "com.gijun.reservationpos.core",
        "com.gijun.reservationpos.api",
        "com.gijun.reservationpos.infrastructure"
    ]
)
@EnableR2dbcRepositories(basePackages = ["com.gijun.reservationpos.infrastructure.repository"])
class ReservationPosApplication

fun main(args: Array<String>) {
    runApplication<ReservationPosApplication>(*args)
}
