package com.transactions

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder

@SpringBootApplication
class MsTransactionsFakerApplication

fun main(args: Array<String>) {
    runApplication<MsTransactionsFakerApplication>(*args)
}

@Bean
fun notificationTopic(): NewTopic {
    return TopicBuilder
        .name("transaction-notification")
        .build();
}