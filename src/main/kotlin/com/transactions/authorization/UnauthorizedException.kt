package com.transactions.authorization

class UnauthorizedException (override val message: String) : RuntimeException()