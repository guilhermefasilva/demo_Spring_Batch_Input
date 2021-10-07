package io.guilhermefasilva.demo.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@EnableBatchProcessing
@SpringBootApplication
public class DemoSpringBatchApplication {
	
	

//	@Autowired
//	private JobBuilderFactory jobBuilderFactory;
//	
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBatchApplication.class, args);
	}

//		@Bean
//		public Step step() {
//			return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
//				@Override
//				public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//					log.info("Execusion step 1 with spring batch :: {}",stepBuilderFactory);
//					return RepeatStatus.FINISHED;
//				}
//			}).build();
//		}
//
//		@Bean
//		public Job job() {
//			return this.jobBuilderFactory.get("TesteJob").start(step()).build();
//		}
	}

