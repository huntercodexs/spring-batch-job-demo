package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class EnrollmentValidationFirstProcessor {

    @Autowired
    ValidationFirstProcessor validationFirstProcessor;

    @Autowired
    PreBuilderFileFirstProcessor preBuilderFileFirstProcessor;

    @Bean("processorFirstStep")
    public ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> processorFirstStep() {

        log.info("EnrollmentValidationFirstProcessor say: (processorFirstStep) processorFirstStep is starting");

        return new CompositeItemProcessorBuilder<EnrollmentValidationDto, EnrollmentValidationDto>()
                .delegates(Arrays.asList(validationFirstProcessor, preBuilderFileFirstProcessor))
                .build();
    }

}
