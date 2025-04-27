package com.gijun.reservationpos.infrastructure.adapter

import com.gijun.reservationpos.domain.entity.Test
import com.gijun.reservationpos.domain.repository.TestRepository
import com.gijun.reservationpos.infrastructure.repository.TestR2dbcRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository

@Repository
class TestRepositoryImpl(
    private val testR2dbcRepository: TestR2dbcRepository
) : TestRepository {

    override suspend fun save(test: Test): Test {
        return testR2dbcRepository.save(test)
    }

    override suspend fun findById(id: Long): Test? {
        return testR2dbcRepository.findById(id)
    }

    override fun findAll(): Flow<Test> {
        return testR2dbcRepository.findAll()
    }

    override suspend fun deleteById(id: Long) {
        testR2dbcRepository.deleteById(id)
    }
}
