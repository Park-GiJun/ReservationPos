package com.gijun.reservationpos.dto

import com.gijun.reservationpos.entity.Test

data class TestRequest(
    val name: String
)

data class TestResponse(
    val id: Long?,
    val name: String
) {
    companion object {
        fun from(test: Test): TestResponse {
            return TestResponse(
                id = test.id,
                name = test.name
            )
        }
    }
}
