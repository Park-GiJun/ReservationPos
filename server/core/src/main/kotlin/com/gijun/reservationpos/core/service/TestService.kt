package com.gijun.reservationpos.core.service

import com.gijun.reservationpos.domain.entity.Test
import kotlinx.coroutines.flow.Flow

interface TestService {
    suspend fun createTest(name: String): Test
    suspend fun getTest(id: Long): Test?
    fun getAllTests(): Flow<Test>
    suspend fun deleteTest(id: Long)
}
