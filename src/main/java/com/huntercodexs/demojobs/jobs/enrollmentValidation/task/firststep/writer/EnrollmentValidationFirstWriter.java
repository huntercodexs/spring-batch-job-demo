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
    BuilderFileFirstWriter builderFileFirstWriter;

    @Autowired
    SendFileToSftpWriter sendFileToSftpWriter;

    @Autowired
    ReportFirstWriter reportFirstWriter;

    @Bean("writerFirstStep")
    public ItemWriter<EnrollmentValidationDto> writerFirstStep() {

        System.out.println("[DEBUG] [WRITER-FIRST-STEP] >>> writerFirstStep");

        return new CompositeItemWriterBuilder<EnrollmentValidationDto>()
            .delegates(Arrays.asList(
                    builderFileFirstWriter,
                    sendFileToSftpWriter,
                    reportFirstWriter
            )).build();
    }
}
