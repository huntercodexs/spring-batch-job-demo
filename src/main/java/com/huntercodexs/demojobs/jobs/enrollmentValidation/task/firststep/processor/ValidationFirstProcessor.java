package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidationFirstProcessor implements ItemProcessor<EnrollmentValidationDto,EnrollmentValidationDto> {

    public EnrollmentValidationDto process(EnrollmentValidationDto enrollmentValidationDto) throws Exception {

        if (enrollmentValidationDto.getId() < 1) {
            log.error("ValidationFirstProcessor say: (process) Missing item id: " + enrollmentValidationDto.getId());
            return null;
        }

        if (enrollmentValidationDto.getName().equals("")) {
            log.error("ValidationFirstProcessor say: (process) Missing item name: " + enrollmentValidationDto.getName());
            return null;
        }

        if (enrollmentValidationDto.getDescription().equals("")) {
            log.error("ValidationFirstProcessor say: (process) Missing item description: " + enrollmentValidationDto.getDescription());
            return null;
        }

        if (enrollmentValidationDto.getPrice().equals("")) {
            log.error("ValidationFirstProcessor say: (process) Missing item price: " + enrollmentValidationDto.getPrice());
            return null;
        }

        return enrollmentValidationDto;
    }
}
