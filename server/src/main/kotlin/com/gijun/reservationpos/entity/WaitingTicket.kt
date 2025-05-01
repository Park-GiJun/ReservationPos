package com.gijun.reservationpos.entity

import com.gijun.reservationpos.entity.enums.ProcessStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(WaitingTicket.TABLE_NAME)
data class WaitingTicket(
    @Id
    @Column(WAITING_ID)
    val waitingId: Long? = null,

    @Column(MS_NO)
    val msNo: Long,

    @Column(TABLE_ID)
    val tableId: Long? = null,

    @Column(CUSTOMER_NAME)
    val customerName: String,

    @Column(CUSTOMER_PHONE)
    val customerPhone: String? = null,

    @Column(WAITING_CNT)
    val waitingCnt: Int,

    @Column(REQUEST_MEMO)
    val requestMemo: String? = null,

    @Column(WAITING_START)
    val waitingStart: LocalDateTime = LocalDateTime.now(),

    @Column(CANCEL_DTIME)
    val cancelDtime: LocalDateTime? = null,

    @Column(ENTER_DTIME)
    val enterDtime: LocalDateTime? = null,

    @Column(UPDATE_DTIME)
    val updateDtime: LocalDateTime = LocalDateTime.now(),

    @Column(PROCESS_FG)
    val processFg: ProcessStatus = ProcessStatus.WAITING,

    @Column(WAITING_ORDER)
    val waitingOrder: Int,

    @Column(IS_DELETED)
    val isDeleted: Boolean = false,

    @Column(IS_TABLE_RESERVED)
    val isTableReserved: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "WAITING_TICKET"
        const val WAITING_ID = "WAITING_ID"
        const val MS_NO = "MS_NO"
        const val TABLE_ID = "TABLE_ID"
        const val CUSTOMER_NAME = "CUSTOMER_NAME"
        const val CUSTOMER_PHONE = "CUSTOMER_PHONE"
        const val WAITING_CNT = "WAITING_CNT"
        const val REQUEST_MEMO = "REQUEST_MEMO"
        const val WAITING_START = "WAITING_START"
        const val CANCEL_DTIME = "CANCEL_DTIME"
        const val ENTER_DTIME = "ENTER_DTIME"
        const val UPDATE_DTIME = "UPDATE_DTIME"
        const val PROCESS_FG = "PROCESS_FG"
        const val WAITING_ORDER = "WAITING_ORDER"
        const val IS_DELETED = "IS_DELETED"
        const val IS_TABLE_RESERVED = "IS_TABLE_RESERVED"
    }
}
