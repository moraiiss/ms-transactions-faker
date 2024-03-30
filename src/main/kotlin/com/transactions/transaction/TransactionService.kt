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

        //authorizerService.authorize(transaction)
        //notificationService.notify(transaction)

        return newTransaction;
    }

    fun validate(transaction: Transaction) {
        walletRepository.findById(transaction.payee)
            .map {
                walletRepository.findById(transaction.payer)
                    .map {
                        it.type == WalletEnum.PAYER.value &&
                            it.balance.compareTo(transaction.value) >= 0 &&
                                !it.id.equals(transaction.payee)
                    }.orElseThrow {
                        InvalidTransactionException("Invalid transaction: $transaction")
                    }
            }.orElseThrow {
                InvalidTransactionException("Invalid transaction: $transaction")
            }
    }
}