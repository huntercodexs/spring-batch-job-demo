package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class EnrollmentValidationSecondWriter {

    @Autowired
    DownloadFileFromSftpSecondWriter downloadFileFromSftpSecondWriter;

    @Autowired
    ReportSecondWriter reportSecondWriter;

    @Bean("writerSecondStep")
    public ItemWriter<String> writerSecondStep() {

        System.out.println("[WRITER-SECOND-STEP] >>> writerSecondStep");

        return new CompositeItemWriterBuilder<String>()
            .delegates(Arrays.asList(downloadFileFromSftpSecondWriter, reportSecondWriter))
            .build();
    }
}
