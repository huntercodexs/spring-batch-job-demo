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

@Configuration
public class EnrollmentValidationThirdStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step thirdStep(
        @Qualifier("readerThirdStep") ItemReader<String> thirdStepItemReader,
        @Qualifier("processorThirdStep") ItemProcessor<String, String> thirdStepItemProcessor,
        @Qualifier("writerThirdStep") ItemWriter<String> thirdStepItemWriter
    ) {

        System.out.println("[THIRD-STEP] >>> thirdStep");

        return stepBuilderFactory
            .get("thirdStep")
            .<String, String>chunk(5_000)
            .reader(thirdStepItemReader)
            .processor(thirdStepItemProcessor)
            .writer(thirdStepItemWriter)
            .build();
    }
}
