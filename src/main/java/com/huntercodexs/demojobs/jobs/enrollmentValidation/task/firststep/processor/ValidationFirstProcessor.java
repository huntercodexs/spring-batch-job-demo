package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationFirstProcessor implements ItemProcessor<EnrollmentValidationDto,EnrollmentValidationDto> {

    public EnrollmentValidationDto process(EnrollmentValidationDto enrollmentValidationDto) throws Exception {

        if (enrollmentValidationDto.getId() < 1) {
            System.out.println("Missing item id: " + enrollmentValidationDto.getId());
            return null;
        }

        if (enrollmentValidationDto.getName().equals("")) {
            System.out.println("Missing item name: " + enrollmentValidationDto.getName());
            return null;
        }

        if (enrollmentValidationDto.getDescription().equals("")) {
            System.out.println("Missing item description: " + enrollmentValidationDto.getDescription());
            return null;
        }

        if (enrollmentValidationDto.getPrice().equals("")) {
            System.out.println("Missing item price: " + enrollmentValidationDto.getPrice());
            return null;
        }

        return enrollmentValidationDto;
    }
}
