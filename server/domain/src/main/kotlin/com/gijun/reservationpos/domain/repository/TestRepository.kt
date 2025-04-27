package com.gijun.reservationpos.domain.repository

import com.gijun.reservationpos.domain.entity.Test
import kotlinx.coroutines.flow.Flow

interface TestRepository {
    suspend fun save(test: Test): Test
    suspend fun findById(id: Long): Test?
    fun findAll(): Flow<Test>
    suspend fun deleteById(id: Long)
}