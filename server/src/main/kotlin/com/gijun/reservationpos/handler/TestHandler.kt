package com.gijun.reservationpos.handler

import com.gijun.reservationpos.adapter.HttpAdapter
import com.gijun.reservationpos.dto.TestRequest
import com.gijun.reservationpos.dto.TestResponse
import com.gijun.reservationpos.service.TestService
import kotlinx.coroutines.flow.map
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class TestHandler(private val testService: TestService) {

    suspend fun createTest(request: ServerRequest): ServerResponse {
        val testRequest = request.awaitBody<TestRequest>()
        val test = testService.createTest(testRequest.name)
        return HttpAdapter.created(TestResponse.from(test))
    }

    suspend fun getTest(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val test = testService.getTest(id) ?: return HttpAdapter.notFound()
        return HttpAdapter.ok(TestResponse.from(test))
    }

    suspend fun getAllTests(request: ServerRequest): ServerResponse {
        val testResponses = testService.getAllTests().map { TestResponse.from(it) }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(testResponses)
    }

    suspend fun deleteTest(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        testService.deleteTest(id)
        return HttpAdapter.noContent()
    }
}
