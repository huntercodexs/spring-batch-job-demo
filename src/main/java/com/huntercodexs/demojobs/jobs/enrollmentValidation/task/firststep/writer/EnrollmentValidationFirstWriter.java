package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EnrollmentValidationFirstWriter {

    @Autowired
    ValidationFirstWriter validationFirstWriter;

    @Autowired
    ReportFirstWriter reportFirstWriter;

    @Autowired
    ConsolidationFirstWriter consolidationFirstWriter;

    @Autowired
    GeneratorFirstWriter generatorFirstWriter;

    @Bean("writerFirstStep")
    public ItemWriter<EnrollmentValidationDto> writerFirstStep() {

        System.out.println("[WRITER-FIRST-STEP] >>> writerFirstStep");

        return new CompositeItemWriterBuilder<EnrollmentValidationDto>()
            .delegates(Arrays.asList(
                    validationFirstWriter,
                    reportFirstWriter,
                    consolidationFirstWriter,
                    generatorFirstWriter
            )).build();
    }
}
