package com.transactions.wallet

import com.sun.tools.javac.tree.TreeInfo.fullName
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal


@Table("wallets")
data class Wallet(
    @Id val id: Long,
    val name: String,
    val document: Long,
    val email: String,
    val password: String,
    val type: Int,
    val balance: BigDecimal
) {
    fun debit (value: BigDecimal): Wallet {
        return Wallet(
            id, name, document, email, password, type, balance.subtract(value)
        )
    }
}
