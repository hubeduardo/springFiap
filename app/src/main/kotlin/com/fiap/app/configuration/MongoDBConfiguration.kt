package com.fiap.app.configuration

import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoDBConfiguration(
    @Value("\${mongodb.host}") val mongodbHost: String,
    @Value("\${mongodb.port}") val mongodbPort: Long,
    @Value("\${mongodb.database}") val mongodbDatabase: String
) {
    @Bean
    fun mongoTemplate(): MongoTemplate {
        val connectionString = "mongodb://$mongodbHost$mongodbDatabase:$mongodbPort"
        val mongoClient = MongoClients.create(connectionString)
        return MongoTemplate(mongoClient, mongodbDatabase)
    }
}
