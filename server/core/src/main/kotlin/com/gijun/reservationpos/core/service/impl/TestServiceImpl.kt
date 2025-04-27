package com.gijun.reservationpos.core.service.impl

import com.gijun.reservationpos.core.service.TestService
import com.gijun.reservationpos.domain.entity.Test
import com.gijun.reservationpos.domain.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class TestServiceImpl(
    private val testRepository: TestRepository
) : TestService {

    override suspend fun createTest(name: String): Test {
        return testRepository.save(Test(name = name))
    }

    override suspend fun getTest(id: Long): Test? {
        return testRepository.findById(id)
    }

    override fun getAllTests(): Flow<Test> {
        return testRepository.findAll()
    }

    override suspend fun deleteTest(id: Long) {
        testRepository.deleteById(id)
    }
}
