package com.tales.apicidades.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.tales.apicidades.batch.listener.JobCompletionNotificationListener;
import com.tales.apicidades.batch.model.CityIO;
import com.tales.apicidades.processor.CityItemProcessor;
import com.tales.apicidades.service.impl.CityService;

/**
 * 
 * @author Tales
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public CityService cityService;
	
	@Bean
    public FlatFileItemReader<CityIO> reader() {
        FlatFileItemReader<CityIO> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("Desafio Cidades - Back End.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<CityIO>() {
            {
            setLineTokenizer(new DelimitedLineTokenizer() {
                {
                	setNames(new String[]{"ibge_id", "uf", "name", "capital", "lon", "lat"
                			, "no_accents", "alternative_names", "microregion", "mesoregion"});
                }
            });
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CityIO>() {
                {
                	setTargetType(CityIO.class);
                }
            });
            }
        });
        return reader;
    }
	
	@Bean
    public CityItemProcessor processor() {
        return new CityItemProcessor();
    }
	
	@Bean
    public JdbcBatchItemWriter<CityIO> writer() {		
        JdbcBatchItemWriter<CityIO> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO city (ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion) "
        		+ "VALUES "
        		+ "(:ibge_id, :uf, :name, :capital, :lon, :lat, :no_accents, :alternative_names, :microregion, :mesoregion)");
        writer.setDataSource(this.dataSource);
        return writer;
    }
	
	@Bean
    public Job importCityJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importCityJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }
	
	@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<CityIO, CityIO>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
	
}
