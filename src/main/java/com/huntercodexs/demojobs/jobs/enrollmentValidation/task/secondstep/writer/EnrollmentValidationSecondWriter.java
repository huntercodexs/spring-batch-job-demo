package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EnrollmentValidationSecondWriter {

    @Autowired
    ValidationSecondWriter validationSecondWriter;

    @Autowired
    ReportSecondWriter reportSecondWriter;

    @Autowired
    ConsolidationSecondWriter consolidationSecondWriter;

    @Bean("writerSecondStep")
    public ItemWriter<EnrollmentValidationDto> writerSecondStep() {

        System.out.println("[WRITER-SECOND-STEP] >>> writerSecondStep");

        return new CompositeItemWriterBuilder<EnrollmentValidationDto>()
            .delegates(Arrays.asList(validationSecondWriter, reportSecondWriter, consolidationSecondWriter))
            .build();
    }
}
