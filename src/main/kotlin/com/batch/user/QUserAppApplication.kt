package com.batch.user

import com.batch.user.batch.UserBatch
import com.batch.user.repository.MongoDBConfiguration
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.data.mongodb.core.MongoTemplate
import java.util.*


@SpringBootApplication
class AppApplication

fun main(args: Array<String>) {
    runApplication<AppApplication>(*args)

    val configurationClasses = arrayOf<Class<*>>(UserBatch::class.java, MongoDBConfiguration::class.java)
    val context: ApplicationContext = AnnotationConfigApplicationContext(*configurationClasses)
    val mongoTemplate = context.getBean(MongoTemplate::class.java)


    // run the job
    val jobLauncher = context.getBean(JobLauncher::class.java)
    val job: Job = context.getBean(Job::class.java)
    jobLauncher.run(job, JobParameters())


    
}




