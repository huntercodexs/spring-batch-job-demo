package com.huntercodexs.demojobs.jobs.enrollmentValidation.step;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
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
public class EnrollmentValidationFirstStep {

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step firstStep(
        @Qualifier("readerFirstStep") ItemReader<EnrollmentValidationDto> firstStepItemReader,
        @Qualifier("processorFirstStep") ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> firstStepItemProcessor,
        @Qualifier("writerFirstStep") ItemWriter<EnrollmentValidationDto> firstStepItemWriter
    ) {

        log.info("First Step say: steps has been configured");
        log.info("First Step say: The steps will be running: " + firstStepItemReader.toString() +", "+ firstStepItemProcessor.toString() + ", "+ firstStepItemWriter.toString());

        return stepBuilderFactory
            .get("firstStep")
            .<EnrollmentValidationDto, EnrollmentValidationDto>chunk(5_000)
            .reader(firstStepItemReader)
            .processor(firstStepItemProcessor)
            .writer(firstStepItemWriter)
            .build();
    }
}
