package com.huntercodexs.demojobs.jobs.enrollmentValidation.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class EnrollmentValidationConfigJob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job enrollmentValidationJob(
        @Qualifier("firstStep") Step step1,
        @Qualifier("secondStep") Step step2,
        @Qualifier("thirdStep") Step step3
    ) {

        System.out.println("[JOB-CONFIG] >>> enrollmentValidationJob");

        return jobBuilderFactory
                .get("enrollmentValidationJob")
                .start(step1)
                .next(step2)
                .next(step3)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
