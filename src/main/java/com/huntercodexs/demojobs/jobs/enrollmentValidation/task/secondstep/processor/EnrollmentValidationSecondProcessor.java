package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class EnrollmentValidationSecondProcessor {

    @Autowired
    ValidationSecondProcessor validationSecondProcessor;

    @Bean("processorSecondStep")
    public ItemProcessor<String, String> processorSecondStep() {

        log.info("EnrollmentValidationSecondProcessor say: (processorSecondStep) processorSecondStep is starting");

        return new CompositeItemProcessorBuilder<String, String>()
                .delegates(Arrays.asList(validationSecondProcessor))
                .build();

    }

}
