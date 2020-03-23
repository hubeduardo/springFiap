package com.fiap.user.configuration

import com.fiap.user.domain.User
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.data.MongoItemWriter
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
@EnableBatchProcessing
class BatchConfiguration {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory

    @Bean
    fun reader(): FlatFileItemReader<User>? {

        return FlatFileItemReaderBuilder<User>()
            .name("userRead")
            .resource(ClassPathResource("pessoa.csv"))
            .delimited()
            .names(*arrayOf("name", "doc"))
            .fieldSetMapper(object : BeanWrapperFieldSetMapper<User?>() {
                init {
                    setTargetType(User::class.java)
                }
            })
            .build()
    }

    @Bean
    fun mongoItemWriter(mongoTemplate: MongoTemplate): MongoItemWriter<User> {

        return MongoItemWriterBuilder<User>()
            .template(mongoTemplate!!)
            .collection("user")
            .build()
    }

    @Bean
    fun step(reader: FlatFileItemReader<User>, mongoItemWriter: MongoItemWriter<User>): Step {

        return stepBuilderFactory!!["step"]
            .chunk<User, User>(2)
            .reader(reader)
            .writer(mongoItemWriter)
            .build()
    }

    @Bean
    fun job(step: Step): Job {

        return jobBuilderFactory!!["job"]
            .start(step!!)
            .build()
    }
}
