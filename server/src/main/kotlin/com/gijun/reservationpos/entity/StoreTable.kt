package com.gijun.reservationpos.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(StoreTable.TABLE_NAME)
data class StoreTable(
    @Id
    @Column(TABLE_ID)
    val tableId: Long? = null,

    @Column(MS_NO)
    val msNo: Long,

    @Column(TABLE_NAME)
    val tableName: String,

    @Column(CAPACITY)
    val capacity: Int,

    @Column(TABLE_STATUS)
    val tableStatus: String = "AVAILABLE",

    @Column(TABLE_LOCATION)
    val tableLocation: String? = null,

    @Column(IS_ACTIVE)
    val isActive: Boolean = true,

    @Column(LAST_OCCUPIED_TIME)
    val lastOccupiedTime: LocalDateTime? = null,

    @Column(LAST_RELEASED_TIME)
    val lastReleasedTime: LocalDateTime? = null
) {
    companion object {
        const val TABLE_NAME = "STORE_TABLE"
        const val TABLE_ID = "TABLE_ID"
        const val MS_NO = "MS_NO"
        const val CAPACITY = "CAPACITY"
        const val TABLE_STATUS = "TABLE_STATUS"
        const val TABLE_LOCATION = "TABLE_LOCATION"
        const val IS_ACTIVE = "IS_ACTIVE"
        const val LAST_OCCUPIED_TIME = "LAST_OCCUPIED_TIME"
        const val LAST_RELEASED_TIME = "LAST_RELEASED_TIME"
    }
}
