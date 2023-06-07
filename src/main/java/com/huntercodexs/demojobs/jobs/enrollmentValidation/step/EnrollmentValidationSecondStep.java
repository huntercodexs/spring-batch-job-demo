package com.huntercodexs.demojobs.jobs.enrollmentValidation.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EnrollmentValidationSecondStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step secondStep(
        @Qualifier("readerSecondStep") ItemReader<String> secondStepItemReader,
        @Qualifier("processorSecondStep") ItemProcessor<String, String> secondStepItemProcessor,
        @Qualifier("writerSecondStep") ItemWriter<String> secondStepItemWriter
    ) {

        System.out.println("[SECOND-STEP] >>> secondStep");

        return stepBuilderFactory
            .get("secondStep")
            .<String, String>chunk(5_000)
            .reader(secondStepItemReader)
            .processor(secondStepItemProcessor)
            .writer(secondStepItemWriter)
            .build();
    }
}
