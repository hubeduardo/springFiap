package com.batch.user.repository

import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
@PropertySource("classpath:/mongodb.properties")
class MongoDBConfiguration {
    @Value("\${mongodb.host}")
    private val mongodbHost: String? = null
    @Value("\${mongodb.port}")
    private val mongodbPort = 0
    @Value("\${mongodb.database}")
    private val mongodbDatabase: String? = null

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val connectionString = "mongodb://" +
                mongodbHost + ":" + mongodbPort + "/" + mongodbDatabase
        val mongoClient = MongoClients.create(connectionString)
        return MongoTemplate(mongoClient, "test")
    }
}
