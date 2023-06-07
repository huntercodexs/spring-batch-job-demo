package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentValidationSecondProcessor {

    @Bean("processorSecondStep")
    public ItemProcessor<String, String> processorSecondStep() {

        System.out.println("[PROCESSOR-SECOND-STEP] >>> processorSecondStep");

        return new ValidationSecondProcessor();
    }

}
