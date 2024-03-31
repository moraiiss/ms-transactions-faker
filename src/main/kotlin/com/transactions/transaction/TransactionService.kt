package com.transactions.transaction

import com.transactions.authorization.AuthorizerService
import com.transactions.notification.NotificationService
import com.transactions.wallet.WalletEnum
import com.transactions.wallet.WalletRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class TransactionService (
    val transactionRepository: TransactionRepository,
    val walletRepository: WalletRepository,
    val authorizerService: AuthorizerService,
    val notificationService: NotificationService
) {
    fun list(): MutableList<Transaction> {
        return transactionRepository.findAll()
    }
    @Transactional
    fun create(transaction: Transaction): Transaction {

        validate(transaction)

        val newTransaction = transactionRepository.save(transaction)

        val walletPayer = walletRepository.findById(transaction.payer).get()
        val walletPayee = walletRepository.findById(transaction.payee).get()

        walletRepository.save(walletPayer.debit(transaction.value))
        walletRepository.save(walletPayee.credit(transaction.value))

        authorizerService.authorize(transaction)
        notificationService.notify(transaction)

        return newTransaction;
    }


    fun validate(transaction: Transaction) {

        val payer = walletRepository.findById(transaction.payer)

        payer.map {
            if (it.type == WalletEnum.SELLER.value) throw InvalidTransactionException("Payer can`t be a seller")
            if (it.id == transaction.payee) throw InvalidTransactionException("Receiver cannot be the same as the payer")
            if (it.balance < transaction.value) throw InvalidTransactionException("Wallet not enough funds")
        }
    }
}