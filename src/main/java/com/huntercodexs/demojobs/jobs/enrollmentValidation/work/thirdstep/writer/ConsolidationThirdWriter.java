package com.huntercodexs.demojobs.jobs.enrollmentValidation.work.thirdstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConsolidationThirdWriter implements ItemWriter<EnrollmentValidationDto> {

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) {

        enrollmentValidationDto.forEach(enrollmentItem -> {
            System.out.println("[WRITE] >>> ConsolidationWriter");
        });

    }

}
