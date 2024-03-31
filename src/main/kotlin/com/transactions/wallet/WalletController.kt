package com.transactions.wallet

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/wallets")
@Tag(name = "wallets", description = "Endpoints for managing wallets")
class WalletController (
    val repository: WalletRepository
) {
    @GetMapping
    @Operation(summary = "List all wallets", description = "Returns a list of all wallets")
    fun listWallets (): MutableIterable<Wallet> {
        return repository.findAll()
    }
}