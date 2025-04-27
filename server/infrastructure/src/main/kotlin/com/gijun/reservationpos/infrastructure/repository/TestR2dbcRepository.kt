package com.gijun.reservationpos.infrastructure.repository

import com.gijun.reservationpos.domain.entity.Test
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TestR2dbcRepository : CoroutineCrudRepository<Test, Long> {
    override fun findAll(): Flow<Test>
}
