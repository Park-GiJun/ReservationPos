package com.gijun.reservationpos.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("test")
data class Test(
    @Id
    val id: Long? = null,
    val name: String
)
