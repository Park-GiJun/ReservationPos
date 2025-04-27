package com.gijun.reservationpos.api.dto

import com.gijun.reservationpos.domain.entity.Test

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
