package com.fiap.app.configuration

import com.fiap.app.domain.User
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.data.MongoItemWriter
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FixedLengthTokenizer
import org.springframework.batch.item.file.transform.LineTokenizer
import org.springframework.batch.item.file.transform.Range
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
    fun userLineTokenizer(): LineTokenizer {
        val tokenizer = FixedLengthTokenizer()
        tokenizer.setColumns(*arrayOf(Range(1, 41), Range(42, 49), Range(50, 55)))
        tokenizer.setNames(*arrayOf("name", "doc", "digit"))
        return tokenizer
    }

    @Bean
    fun reader(): FlatFileItemReader<User> {
        return FlatFileItemReaderBuilder<User>()
                .name("userRead")
                .resource(ClassPathResource("base.txt"))
                .recordSeparatorPolicy(BlankLineRecordSeparatorPolicy())
                .lineTokenizer(userLineTokenizer())
                .fieldSetMapper(userFieldSetMapper())
                .comments("-------")
                .build()
    }

    @Bean
    fun userFieldSetMapper(): FieldSetMapper<User> {
        return UserFieldSetMapper()
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
