package com.gijun.reservationpos.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table(Store.TABLE_NAME)
data class Store (
    @Id
    @Column(MS_NO)
    val msNo: Long? = null,

    @Column(MS_NM)
    val msNm: String,

    @Column(OPEN_DATE)
    val openDate: LocalDate,

    @Column(CLOSE_DATE)
    val closeDate: LocalDate,

    @Column(IS_WAITING_ON)
    val isWaitingOn: Boolean = true,

    @Column(IS_TABLE_STORE)
    val isTableStore: Boolean = true,

    @Column(LAST_ENTERED_CUSTOMER_NUMBER)
    val lastEnteredCustomerNumber: Int = 0,

    @Column(MAX_WAITING_NUMBER)
    val maxWaitingNumber: Int = 0
) {
    companion object {
        const val TABLE_NAME = "STORE"

        const val MS_NO = "MS_NO"
        const val MS_NM = "MS_NM"
        const val OPEN_DATE = "OPEN_DATE"
        const val CLOSE_DATE = "CLOSE_DATE"
        const val IS_WAITING_ON = "IS_WAITING_ON"
        const val IS_TABLE_STORE = "IS_TABLE_STORE"
        const val LAST_ENTERED_CUSTOMER_NUMBER = "LAST_ENTERED_CUSTOMER_NUMBER"
        const val MAX_WAITING_NUMBER = "MAX_WAITING_NUMBER"
    }
}
