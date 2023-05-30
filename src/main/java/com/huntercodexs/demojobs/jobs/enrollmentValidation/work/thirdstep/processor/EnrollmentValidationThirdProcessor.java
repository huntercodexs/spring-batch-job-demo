package com.huntercodexs.demojobs.jobs.enrollmentValidation.work.thirdstep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentValidationThirdProcessor {

    @Bean("processorThirdStep")
    public ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> processorThirdStep() {

        System.out.println("[PROCESSOR-THIRD-STEP] >>> processorThirdStep");

        return new ValidationThirdProcessor();
    }

}
