package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class EnrollmentValidationThirdProcessor {

    @Autowired
    ValidationThirdProcessor validationThirdProcessor;

    @Bean("processorThirdStep")
    public ItemProcessor<String, String> processorThirdStep() {

        log.info("EnrollmentValidationThirdProcessor say: (processorThirdStep) processorThirdStep is starting");

        return new CompositeItemProcessorBuilder<String, String>()
                .delegates(Arrays.asList(validationThirdProcessor))
                .build();
    }

}
