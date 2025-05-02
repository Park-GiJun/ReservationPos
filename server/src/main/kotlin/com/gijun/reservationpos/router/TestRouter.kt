package com.gijun.reservationpos.router

import com.gijun.reservationpos.handler.TestHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TestRouter(private val testHandler: TestHandler) {

    @Bean
    fun testRoutes(): RouterFunction<ServerResponse> = coRouter {
        "/api/tests".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("", testHandler::getAllTests)
                GET("/{id}", testHandler::getTest)
                POST("", testHandler::createTest)
                DELETE("/{id}", testHandler::deleteTest)
            }
        }
    }
}
