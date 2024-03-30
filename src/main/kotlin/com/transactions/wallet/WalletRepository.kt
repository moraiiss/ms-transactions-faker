package com.transactions.wallet

import org.springframework.data.repository.CrudRepository

interface WalletRepository : CrudRepository<Wallet, Long>