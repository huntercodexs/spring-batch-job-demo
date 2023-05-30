package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemProcessor;

public class ValidationThirdProcessor implements ItemProcessor<EnrollmentValidationDto,EnrollmentValidationDto> {

    public EnrollmentValidationDto process(EnrollmentValidationDto enrollmentValidationDto) throws Exception {

        System.out.println("[VALIDATION-PROCESSOR] >>> process");

        if (enrollmentValidationDto.getId() < 1) {
            System.out.println("Missing item id: " + enrollmentValidationDto.getId());
            return null;
        }

        if (enrollmentValidationDto.getName().equals("")) {
            System.out.println("Missing item name: " + enrollmentValidationDto.getName());
        }

        return enrollmentValidationDto;
    }
}
