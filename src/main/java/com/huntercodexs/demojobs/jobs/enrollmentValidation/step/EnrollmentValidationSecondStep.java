package com.huntercodexs.demojobs.jobs.enrollmentValidation.step;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
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
public class EnrollmentValidationSecondStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step secondStep(
        @Qualifier("readerSecondStep") ItemReader<EnrollmentValidationDto> secondStepItemReader,
        @Qualifier("processorSecondStep") ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> secondStepItemProcessor,
        @Qualifier("writerSecondStep") ItemWriter<EnrollmentValidationDto> secondStepItemWriter
    ) {

        System.out.println("[SECOND-STEP] >>> secondStep");

        return stepBuilderFactory
            .get("secondStep")
            .<EnrollmentValidationDto, EnrollmentValidationDto>chunk(5_000)
            .reader(secondStepItemReader)
            .processor(secondStepItemProcessor)
            .writer(secondStepItemWriter)
            .build();
    }
}
