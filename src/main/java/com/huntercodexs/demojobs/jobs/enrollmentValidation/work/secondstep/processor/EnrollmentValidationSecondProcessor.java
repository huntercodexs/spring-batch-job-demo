package com.huntercodexs.demojobs.jobs.enrollmentValidation.work.secondstep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentValidationSecondProcessor {

    @Bean("processorSecondStep")
    public ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> processorSecondStep() {

        System.out.println("[PROCESSOR-SECOND-STEP] >>> processorSecondStep");

        return new ValidationSecondProcessor();
    }

}
