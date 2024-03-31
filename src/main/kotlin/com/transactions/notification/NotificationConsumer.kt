package com.transactions.notification

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class NotificationConsumer (
    builder: RestClient.Builder
) {
    val restClient: RestClient = builder
        .baseUrl("https://apimocha.com/transactionsfaker/notification")
        .build()

    @KafkaListener(topics = ["transaction-notification"], groupId = "ms-transactions-faker")
    fun receiverNotification () {
        val response = restClient
            .get()
            .retrieve()
            .toEntity(Notification::class.java)

        if (response.statusCode.isError || !response.body?.message!!) {
            throw NotificationException("Notification not send")
        }
    }
}