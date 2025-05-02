package com.gijun.reservationpos.adapter

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

/**
 * HTTP 응답을 생성하기 위한 유틸리티 클래스
 */
object HttpAdapter {
    
    /**
     * 성공 응답을 생성합니다. (200 OK)
     * 단일 객체를 반환할 때 사용합니다.
     */
    suspend inline fun <reified T : Any> ok(body: T): ServerResponse {
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(body)
    }
    
    /**
     * 생성 완료 응답을 생성합니다. (201 Created)
     * URI를 지정하지 않고 201 상태 코드만 반환합니다.
     */
    suspend inline fun <reified T : Any> created(body: T): ServerResponse {
        return ServerResponse.status(201)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(body)
    }
    
    /**
     * 생성 완료 응답을 생성합니다. (201 Created)
     * 리소스 위치를 URI로 명시합니다.
     */
    suspend inline fun <reified T : Any> createdWithLocation(location: String, body: T): ServerResponse {
        return ServerResponse.created(java.net.URI.create(location))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(body)
    }
    
    /**
     * 없음 응답을 생성합니다. (404 Not Found)
     */
    suspend fun notFound(): ServerResponse {
        return ServerResponse.notFound().buildAndAwait()
    }
    
    /**
     * 내용 없음 응답을 생성합니다. (204 No Content)
     */
    suspend fun noContent(): ServerResponse {
        return ServerResponse.noContent().buildAndAwait()
    }
    
    /**
     * 잘못된 요청 응답을 생성합니다. (400 Bad Request)
     */
    suspend fun badRequest(message: String): ServerResponse {
        return ServerResponse.badRequest()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(mapOf("error" to message))
    }
    
    /**
     * 서버 오류 응답을 생성합니다. (500 Internal Server Error)
     */
    suspend fun serverError(message: String): ServerResponse {
        return ServerResponse.status(500)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(mapOf("error" to message))
    }
}
