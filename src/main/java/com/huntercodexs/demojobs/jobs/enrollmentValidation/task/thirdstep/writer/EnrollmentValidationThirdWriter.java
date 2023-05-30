package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EnrollmentValidationThirdWriter {

    @Autowired
    ValidationThirdWriter validationThirdWriter;

    @Autowired
    ReportThirdWriter reportThirdWriter;

    @Autowired
    ConsolidationThirdWriter consolidationThirdWriter;

    @Bean("writerThirdStep")
    public ItemWriter<EnrollmentValidationDto> writerThirdStep() {

        System.out.println("[WRITER-THIRD-STEP] >>> writerThirdStep");

        return new CompositeItemWriterBuilder<EnrollmentValidationDto>()
            .delegates(Arrays.asList(validationThirdWriter, reportThirdWriter, consolidationThirdWriter))
            .build();
    }
}
