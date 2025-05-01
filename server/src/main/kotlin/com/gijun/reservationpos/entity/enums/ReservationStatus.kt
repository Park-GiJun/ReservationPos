package com.gijun.reservationpos.entity.enums

enum class ReservationStatus(val code: String) {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED")
}
