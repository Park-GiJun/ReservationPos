package com.gijun.reservationpos.service

import com.gijun.reservationpos.entity.Test
import com.gijun.reservationpos.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class TestService(private val testRepository: TestRepository) {
    
    suspend fun createTest(name: String): Test {
        return testRepository.save(Test(name = name))
    }

    suspend fun getTest(id: Long): Test? {
        return testRepository.findById(id)
    }

    fun getAllTests(): Flow<Test> {
        return testRepository.findAll()
    }

    suspend fun deleteTest(id: Long) {
        testRepository.deleteById(id)
    }
}
