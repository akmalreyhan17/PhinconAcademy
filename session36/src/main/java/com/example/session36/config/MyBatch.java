package com.example.session36.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.session36.components.CustomDecider;
import com.example.session36.components.CustomListener;
import com.example.session36.components.MyMapper;
import com.example.session36.components.MyProcessor;
import com.example.session36.components.MyReader;
import com.example.session36.components.MyRowMapper;
import com.example.session36.components.MyTasklet;
import com.example.session36.components.MyWriter;
import com.example.session36.model.MyEntity;

@Configuration
@EnableBatchProcessing
public class MyBatch {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public ItemReader<String> reader() {
        return new MyReader();
    }

    @Bean
    public ItemProcessor<MyEntity, MyEntity> processor() {
        return new MyProcessor();
    }

    @Bean
    public ItemWriter<MyEntity> writer() {
        return new MyWriter();
    }

    Resource resource = new ClassPathResource("data/input.csv");
    FieldSetMapper<MyEntity> mapper = new MyMapper();
    String[] columns = new String[] { "id", "name" };

    @Bean
    public FlatFileItemReader<MyEntity> flatFileItemReader() {
        return new FlatFileItemReaderBuilder<MyEntity>()
                .name("myFileReader")
                .resource(resource)
                .delimited()
                .names(columns)
                .fieldSetMapper(mapper)
                .build();
    }

    FileSystemResource resource2 = new FileSystemResource("data/output.csv");

    @Bean
    public FlatFileItemWriter<MyEntity> flatFileItemWriter() {
        return new FlatFileItemWriterBuilder<MyEntity>()
                .name("myFileWriter")
                .resource(resource2)
                .delimited()
                .delimiter(",")
                .names(new String[] { "id", "name" })
                .build();
    }

    RowMapper<MyEntity> rowMapper = new MyRowMapper();

    @Bean
    public JdbcCursorItemReader<MyEntity> jdbcCursorItemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<MyEntity>()
                .dataSource(dataSource)
                .sql("SELECT id, name FROM my_table")
                .rowMapper(rowMapper)
                .name("jdbcReader")
                .build();
    }

    @Bean
    public ItemProcessor<MyEntity, MyEntity> compositeProcessor() {
        return new CompositeItemProcessorBuilder<MyEntity, MyEntity>()
                .delegates(firstProcessor(), secondProcessor()) // Chaining the processors
                .build();
    }

    @Bean
    public ItemWriter<MyEntity> compositeWriter() {
        return new CompositeItemWriterBuilder<MyEntity>()
                .delegates(databaseWriter(), fileWriter()) // Writing to both database and file
                .build();
    }

    @Bean
    public ItemProcessor<MyEntity, MyEntity> processorClassifier() {
        return new ClassifierCompositeItemProcessorBuilder<MyEntity, MyEntity>()
                .classifier(data -> {
                    if (data.getActive() == true) {
                        return firstProcessor();
                    } else {
                        return secondProcessor();
                    }
                })
                .build();
    }

    private ItemWriter<? super MyEntity> fileWriter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fileWriter'");
    }

    private ItemWriter<? super MyEntity> databaseWriter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'databaseWriter'");
    }

    private ItemProcessor<?, ? extends MyEntity> secondProcessor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'secondProcessor'");
    }

    private ItemProcessor<?, ? extends MyEntity> firstProcessor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'firstProcessor'");
    }

    @Bean
    public JdbcBatchItemWriter<MyEntity> jdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<MyEntity>()
                .dataSource(dataSource)
                .sql("INSERT INTO my_table (id, name) VALUES (:id, :name)")
                .beanMapped()
                .build();
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTransactionManager(transactionManager());
        factory.afterPropertiesSet(); // Initialize the factory
        return factory.getObject();
    }

    @Bean
    public Step step1(
            ItemReader<String> reader,
            ItemProcessor<String, String> processor,
            ItemWriter<String> writer) throws Exception {
        return new StepBuilder("step1", jobRepository())
                .<String, String>chunk(10, transactionManager())
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class) // Retry 3 times on exceptions
                .skipLimit(5)
                .skip(Exception.class) // Skip up to 5 records on exceptions
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return new MyTasklet("/path/to/file.txt");
    }

    @Bean
    public Step step2() throws Exception {
        return new StepBuilder("step2", jobRepository())
                .tasklet(tasklet(), transactionManager())
                .build();
    }

    @Bean
    public Job job(Step step1, Step step2) throws Exception {
        return new JobBuilder("myJob", jobRepository())
                .start(step1)
                .next(step2)
                .build();
    }

    @Autowired
    private CustomDecider customDecider;

    @Bean
    public Job conditionalJob(Step step1, Step step2, Step step3) throws Exception {
        return new JobBuilder("myJob", jobRepository())
                .start(step1)
                .next(customDecider) // Use the decider to control flow
                .on("PASS").to(step2)
                .on("FAIL").to(step3)
                .end()
                .build();
    }

    @Autowired
    private CustomListener customListener;

    @Bean
    public Job myJob(Step step1) throws Exception {
        return new JobBuilder("myJob", jobRepository())
                .start(step1)
                .listener(customListener) // Attach the listener to the job
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4); // Number of threads to use
        taskExecutor.setMaxPoolSize(8);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

    // @Bean
    // public Step multiThreadedStep() {
    //     return new StepBuilder("multiThreadedStep", jobRepository())
    //             .<MyEntity, My>chunk(100, transactionManager())
    //             .reader(reader())
    //             .processor(processor())
    //             .writer(writer())
    //             .taskExecutor(taskExecutor()) // Enable multi-threading
    //             .throttleLimit(4) // Limits the number of concurrent threads
    //             .build();
    // }
}
