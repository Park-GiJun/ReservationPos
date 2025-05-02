package com.gijun.reservationpos.repository

import com.gijun.reservationpos.entity.Test
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TestRepository : CoroutineCrudRepository<Test, Long> {
    override fun findAll(): Flow<Test>
}
