package io.guilhermefasilva.demo.spring.batch.configuration;

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

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;
import io.guilhermefasilva.demo.spring.batch.service.AutoBootItemProcessor;
import io.guilhermefasilva.demo.spring.batch.service.JobCompletionNotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuiderFactory;
	@Autowired
	private DataSource datasource;
	
	
	
	public FlatFileItemReader<AutoBoot> reader(){
		FlatFileItemReader< AutoBoot> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("sample-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<AutoBoot>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
			
					{setNames(new String[] {"name","car"});}
		});
		setFieldSetMapper(new BeanWrapperFieldSetMapper<AutoBoot>() {
			{setTargetType(AutoBoot.class);}
		});
		
			}
	});
		return reader;
	}
	
	@Bean
	public AutoBootItemProcessor processor() {
		return new AutoBootItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<AutoBoot> writer(){
		JdbcBatchItemWriter<AutoBoot> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql("INSERT INTO tb_car (name, car) VALUES (:name, :car)");
		writer.setDataSource(this.datasource);
		return writer;
	}
	
	@Bean
	public Job importAutobootJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("importAutobootJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();
	}
	
	@Bean
	public Step step1() {
		return stepBuiderFactory.get("step1")
				.<AutoBoot, AutoBoot>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	
}
