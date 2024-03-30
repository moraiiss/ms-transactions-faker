package com.transactions.transaction

import com.transactions.authorization.AuthorizerService
import com.transactions.wallet.WalletEnum
import com.transactions.wallet.WalletRepository
import jakarta.transaction.InvalidTransactionException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class TransactionService (
    val transactionRepository: TransactionRepository,
    val walletRepository: WalletRepository,
    val authorizerService: AuthorizerService
) {
    @Transactional
    fun create(transaction: Transaction): Transaction {

        // validar
        validate(transaction)

        // cria a nova transacao
        val newTransaction = transactionRepository.save(transaction)

        // debita da carteira do pagador
        val wallet = walletRepository.findById(transaction.payer).get()
        walletRepository.save(wallet.debit(transaction.value))

        // autorizacao externa
        authorizerService.authorize(transaction)

        // notificacao da transacao


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