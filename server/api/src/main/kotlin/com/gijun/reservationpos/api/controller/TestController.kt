package com.gijun.reservationpos.api.controller

import com.gijun.reservationpos.api.dto.TestRequest
import com.gijun.reservationpos.api.dto.TestResponse
import com.gijun.reservationpos.core.service.TestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tests")
class TestController(
    private val testService: TestService
) {

    @PostMapping
    suspend fun createTest(@RequestBody request: TestRequest): TestResponse {
        val test = testService.createTest(request.name)
        return TestResponse.from(test)
    }

    @GetMapping("/{id}")
    suspend fun getTest(@PathVariable id: Long): TestResponse? {
        return testService.getTest(id)?.let { TestResponse.from(it) }
    }

    @GetMapping
    fun getAllTests(): Flow<TestResponse> {
        return testService.getAllTests().map { TestResponse.from(it) }
    }

    @DeleteMapping("/{id}")
    suspend fun deleteTest(@PathVariable id: Long) {
        testService.deleteTest(id)
    }
}
