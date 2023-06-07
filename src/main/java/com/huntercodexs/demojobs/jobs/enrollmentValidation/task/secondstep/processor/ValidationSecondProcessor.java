package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.processor;

import org.springframework.batch.item.ItemProcessor;

public class ValidationSecondProcessor implements ItemProcessor<String, String> {

    public String process(String enrollmentValidationDto) throws Exception {

        System.out.println("[VALIDATION-PROCESSOR] >>> process");

        return enrollmentValidationDto;
    }
}
