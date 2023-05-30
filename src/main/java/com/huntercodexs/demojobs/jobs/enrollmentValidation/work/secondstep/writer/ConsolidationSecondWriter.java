package com.huntercodexs.demojobs.jobs.enrollmentValidation.work.secondstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConsolidationSecondWriter implements ItemWriter<EnrollmentValidationDto> {

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) {

        enrollmentValidationDto.forEach(enrollmentItem -> {
            System.out.println("[WRITE] >>> ConsolidationWriter");
        });

    }

}
