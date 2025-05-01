package com.gijun.reservationpos.entity.enums

enum class ProcessStatus(val code: String) {
    WAITING("WAITING"),
    RESERVED("RESERVED"),
    CANCELLED("CANCELLED"),
    ENTERED("ENTERED"),
    NO_SHOW("NO_SHOW")
}
