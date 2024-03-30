package com.transactions.notification

import com.transactions.transaction.Transaction
import org.springframework.stereotype.Service

@Service
class NotificationService (val notificationProducer: NotificationProducer) {
    fun notify(transaction: Transaction) {
        notificationProducer.sendNotification(transaction)
    }
}