package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class EnrollmentValidationSecondWriter {

    @Autowired
    ValidationSecondWriter validationSecondWriter;

    @Autowired
    ReportSecondWriter reportSecondWriter;

    @Autowired
    ConsolidationSecondWriter consolidationSecondWriter;

    @Bean("writerSecondStep")
    public ItemWriter<String> writerSecondStep() {

        System.out.println("[WRITER-SECOND-STEP] >>> writerSecondStep");

        return new CompositeItemWriterBuilder<String>()
            .delegates(Arrays.asList(validationSecondWriter, reportSecondWriter, consolidationSecondWriter))
            .build();
    }
}
