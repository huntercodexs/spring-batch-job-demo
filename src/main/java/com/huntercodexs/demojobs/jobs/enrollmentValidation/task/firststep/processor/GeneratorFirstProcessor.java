package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class GeneratorFirstProcessor implements ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> {

    public EnrollmentValidationDto process(EnrollmentValidationDto enrollmentValidationDto) {

        System.out.println("[DEBUG] [GENERATOR-PROCESSOR] >>> process");
        System.out.println(enrollmentValidationDto.toString());

        if (enrollmentValidationDto.getId() < 1) {
            System.out.println("[DEBUG] Missing item id: " + enrollmentValidationDto.getId());
            return null;
        }

        if (enrollmentValidationDto.getName().equals("")) {
            System.out.println("[DEBUG] Missing item name: " + enrollmentValidationDto.getName());
        }

        return enrollmentValidationDto;

    }

}
