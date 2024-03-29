package com.transactions.transaction

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("transactions")
data class Transaction(
    @Id val id: Long,
    val payee: Long,
    val payer: Long,
    var value: BigDecimal,
    @CreatedDate val createdAt: LocalDateTime
) {
    init {
        value = value.setScale(2)
    }
}
