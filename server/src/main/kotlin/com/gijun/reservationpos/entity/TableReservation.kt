package com.gijun.reservationpos.entity

import com.gijun.reservationpos.entity.enums.ReservationStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(TableReservation.TABLE_NAME)
data class TableReservation(
    @Id
    @Column(RESERVATION_ID)
    val reservationId: Long? = null,

    @Column(TABLE_ID)
    val tableId: Long,

    @Column(WAITING_ID)
    val waitingId: Long,

    @Column(RESERVATION_TIME)
    val reservationTime: LocalDateTime = LocalDateTime.now(),

    @Column(EXPECTED_ENTRY_TIME)
    val expectedEntryTime: LocalDateTime? = null,

    @Column(ACTUAL_ENTRY_TIME)
    val actualEntryTime: LocalDateTime? = null,

    @Column(RESERVATION_STATUS)
    val reservationStatus: ReservationStatus = ReservationStatus.PENDING,
    ) {
    companion object {
        const val TABLE_NAME = "TABLE_RESERVATION"
        const val RESERVATION_ID = "RESERVATION_ID"
        const val TABLE_ID = "TABLE_ID"
        const val WAITING_ID = "WAITING_ID"
        const val RESERVATION_TIME = "RESERVATION_TIME"
        const val EXPECTED_ENTRY_TIME = "EXPECTED_ENTRY_TIME"
        const val ACTUAL_ENTRY_TIME = "ACTUAL_ENTRY_TIME"
        const val RESERVATION_STATUS = "RESERVATION_STATUS"
    }
}
