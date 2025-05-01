package com.gijun.reservationpos.handler

import com.gijun.reservationpos.service.WaitingService
import org.springframework.stereotype.Component

@Component
class WaitingHandler(private val waitingService: WaitingService) {
}