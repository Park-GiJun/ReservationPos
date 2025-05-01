package com.gijun.reservationpos.service

import com.gijun.reservationpos.repository.WaitingTicketRepository
import org.springframework.stereotype.Service

@Service
class WaitingService(private val waitingTicketRepository: WaitingTicketRepository) {
}