package com.huntercodexs.demojobs.jobs.enrollmentValidation.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
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

        log.info("Second Step say: steps has been configured");
        log.info("Second Step say: The steps will be running: " + secondStepItemReader.toString() +", "+ secondStepItemProcessor.toString() + ", "+ secondStepItemWriter.toString());

        return stepBuilderFactory
            .get("secondStep")
            .<String, String>chunk(5_000)
            .reader(secondStepItemReader)
            .processor(secondStepItemProcessor)
            .writer(secondStepItemWriter)
            .build();
    }
}
