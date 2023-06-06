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
public class EnrollmentValidationFirstStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step firstStep(
        @Qualifier("readerFirstStep") ItemReader<EnrollmentValidationDto> firstStepItemReader,
        @Qualifier("processorFirstStep") ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> firstStepItemProcessor,
        @Qualifier("writerFirstStep") ItemWriter<EnrollmentValidationDto> firstStepItemWriter
    ) {
        return stepBuilderFactory
            .get("firstStep")
            .<EnrollmentValidationDto, EnrollmentValidationDto>chunk(5_000)
            .reader(firstStepItemReader)
            .processor(firstStepItemProcessor)
            .writer(firstStepItemWriter)
            .build();
    }
}
