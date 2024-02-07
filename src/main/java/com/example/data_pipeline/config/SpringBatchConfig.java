package com.example.data_pipeline.config;
import com.example.data_pipeline.entity.CovidDataDB;
import com.example.data_pipeline.entity.CountriesDataDB;
import com.example.data_pipeline.model.CountriesDataCSV;
import com.example.data_pipeline.repository.CountriesDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.net.MalformedURLException;
@Configuration
@RequiredArgsConstructor
public class SpringBatchConfig {
    private final DataSource dataSource;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    @Bean
    public JsonItemReader<CovidDataDB> readerCovid() throws MalformedURLException {
        return new JsonItemReaderBuilder<CovidDataDB>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(CovidDataDB.class))
                .resource(new UrlResource("https://opendata.ecdc.europa.eu/covid19/nationalcasedeath/json/"))
//                .resource(new FileSystemResource("src/main/resources/covid.json"))
                .name("readJSON")
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<CovidDataDB> writerCovid() {
        JdbcBatchItemWriter<CovidDataDB> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
//        writer.setSql("insert into covid_data_pipeline (country,country_code,continent,population,indicator,weekly_count,year_week,rate_14_day,cumulative_count,source,note) "+
//                "values (:country,:country_code,:continent,:population,:indicator,:weekly_count,:year_week,:rate_14_day,:cumulative_count,:source,:note);");
        writer.setSql("call insert_record(:country,:country_code,:continent,:population,:indicator,:weekly_count,:year_week,:rate_14_day,:cumulative_count,:source,:note);");
        writer.setDataSource(dataSource);
        writer.setAssertUpdates(false);
        return writer;
    }

    @Bean
    public CovidDataProcessor processorCovid() {
        return new CovidDataProcessor();
    }
    @Bean
    public Step stepCovid() throws Exception {
        return new StepBuilder("stepImportJSON", jobRepository)
                .<CovidDataDB, CovidDataDB>chunk(2000,platformTransactionManager)
                .reader(readerCovid())
                .processor(processorCovid())
                .writer(writerCovid())
//                .taskExecutor(taskExecutor())
                .build();
    }

//    @Bean
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(3);
//        return asyncTaskExecutor;
//    }
/*-----------------------------------------------------------------*/
    private final CountriesDataRepository repository;
    @Bean
    public FlatFileItemReader<CountriesDataCSV> readerCountries() throws Exception {
        FlatFileItemReader<CountriesDataCSV> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/countries of the world.csv"));
        reader.setName("readerCSV");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }
    public LineMapper<CountriesDataCSV> lineMapper() {
        DefaultLineMapper<CountriesDataCSV> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setQuoteCharacter('\"');
        tokenizer.setStrict(false);
        tokenizer.setNames("country","region","population","area","pop_density","coastline","net_migration","infant_mortality","gdp","literacy","phones","arable","crops","other","climate","birthrate","deathrate","agriculture","industry","service");

        BeanWrapperFieldSetMapper<CountriesDataCSV> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(CountriesDataCSV.class);


        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);

        return lineMapper;
    }
    @Bean
    public CountriesDataProcessor processorCountries() {
        return new CountriesDataProcessor();
    }
    @Bean
    /**/public RepositoryItemWriter<CountriesDataDB> writerCountries() {
        RepositoryItemWriter<CountriesDataDB> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }
    @Bean
    public Step stepCountries() throws Exception {
        return new StepBuilder("stepImportCSV", jobRepository)
                .<CountriesDataCSV, CountriesDataDB>chunk(50,platformTransactionManager)
                .reader(readerCountries())
                .processor(processorCountries())
                .writer(writerCountries())
                .taskExecutor(taskExecutor())
                .build();
    }
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(2);
        return asyncTaskExecutor;
    }
    /*-----------------------------------------------------------------*/
    @Bean
    public Job runJob() throws Exception {
        return new JobBuilder("jobImportJsonAndCsv", jobRepository)
                .start(stepCovid())
                .next(stepCountries())
                .build();
    }

}
