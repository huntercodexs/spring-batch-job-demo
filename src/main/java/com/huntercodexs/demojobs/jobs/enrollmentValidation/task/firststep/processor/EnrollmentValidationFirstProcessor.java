package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentValidationFirstProcessor {

    @Bean("processorFirstStep")
    public ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> processorFirstStep() {

        System.out.println("[PROCESSOR-FIRST-STEP] >>> processorFirstStep");

        return new ValidationFirstProcessor();
    }

}
