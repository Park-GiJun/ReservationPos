package com.gijun.reservationpos.repository

import com.gijun.reservationpos.entity.WaitingTicket
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WaitingTicketRepository : CoroutineCrudRepository<WaitingTicket, Long> {
    override fun findAll(): Flow<WaitingTicket>
}