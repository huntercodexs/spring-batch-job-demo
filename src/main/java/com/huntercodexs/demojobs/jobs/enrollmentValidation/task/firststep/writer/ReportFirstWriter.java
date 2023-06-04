package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportFirstWriter implements ItemWriter<EnrollmentValidationDto> {

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) {

        enrollmentValidationDto.forEach(enrollmentItem -> {
            System.out.println("[DEBUG] [WRITE] >>> ReportWriter");
            System.out.println("[DEBUG]" + enrollmentItem.toString());
        });

    }
}
