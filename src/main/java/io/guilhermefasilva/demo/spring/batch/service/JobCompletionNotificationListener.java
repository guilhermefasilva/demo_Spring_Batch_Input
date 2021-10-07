package io.guilhermefasilva.demo.spring.batch.service;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<AutoBoot> results = this.jdbcTemplate.query("SELECT name, car FROM tb_car",
                    (rs, row) -> new AutoBoot(rs.getString(1), rs.getString(2)));

            for (AutoBoot autoboot : results) {
                log.info("Found <" + autoboot.toString() + "> in the database.");
            }

        }
    }
	

	
}
