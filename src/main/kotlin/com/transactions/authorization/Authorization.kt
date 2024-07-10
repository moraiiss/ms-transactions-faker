package com.transactions.authorization

data class Authorization (
    val message: String
) {
    fun isAuthorized(): Boolean {
        return message == "Authorized"
    }
}