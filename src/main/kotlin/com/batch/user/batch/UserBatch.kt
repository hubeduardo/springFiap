package com.batch.user.batch


import com.batch.user.domain.User
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.data.MongoItemWriter
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.data.mongodb.core.MongoTemplate


@SpringBootApplication
@EnableBatchProcessing
class UserBatch {

    @Autowired
    private lateinit var jobBuilderFactory: JobBuilderFactory
    @Autowired
    private lateinit var stepBuilderFactory: StepBuilderFactory


    fun JobConfiguration(jobBuilderFactory: JobBuilderFactory, stepBuilderFactory: StepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory
        this.stepBuilderFactory = stepBuilderFactory
    }

    @Bean
    fun reader(): ItemReader<User> {
        val reader: FlatFileItemReader<User> = FlatFileItemReader<User>()
        reader.setResource(ClassPathResource("pessoa.csv"))
        reader.setLinesToSkip(1)

        val lineMapper: DefaultLineMapper<User> = DefaultLineMapper<User>()
        val tokenizer = DelimitedLineTokenizer()
        tokenizer.setNames("nome", "cpf")

        val fieldSetMapper: BeanWrapperFieldSetMapper<User> = BeanWrapperFieldSetMapper<User>()
        fieldSetMapper.setTargetType(User::class.java)

        lineMapper.setFieldSetMapper(fieldSetMapper)
        lineMapper.setLineTokenizer(tokenizer)
        reader.setLineMapper(lineMapper)
        return reader
    }

    @Bean
    fun mongoItemWriter(mongoTemplate: MongoTemplate): MongoItemWriter<User> {
        return MongoItemWriterBuilder<User>()
                .template(mongoTemplate!!)
                .collection("person_out")
                .build()
    }

    @Bean
    fun step(reader: ItemReader<User>, mongoItemWriter: MongoItemWriter<User>): Step {
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


