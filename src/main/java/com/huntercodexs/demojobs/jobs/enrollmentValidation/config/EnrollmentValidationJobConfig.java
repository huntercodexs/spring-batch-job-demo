package com.huntercodexs.demojobs.jobs.enrollmentValidation.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class EnrollmentValidationJobConfig {

    @Value("${job.enrollment-validation.enabled:true}")
    boolean jobEnabled;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job enrollmentValidationJob(
        @Qualifier("firstStep") Step step1,
        @Qualifier("secondStep") Step step2,
        @Qualifier("thirdStep") Step step3
    ) {
        if (!jobEnabled) return null;

        System.out.println("[DEBUG] [JOB-CONFIG] >>> enrollmentValidationJob");

        return jobBuilderFactory
                .get("enrollmentValidationJob")
                .start(step1)
                .next(step2)
                .next(step3)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
