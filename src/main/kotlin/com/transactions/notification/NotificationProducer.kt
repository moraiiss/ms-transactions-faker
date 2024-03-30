package com.transactions.notification

import com.transactions.transaction.Transaction
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NotificationProducer (
    val kafkaTemplate: KafkaTemplate<String, Transaction>
) {
    fun sendNotification(transaction: Transaction) {
        kafkaTemplate.send("transaction-notification", transaction)
    }
}