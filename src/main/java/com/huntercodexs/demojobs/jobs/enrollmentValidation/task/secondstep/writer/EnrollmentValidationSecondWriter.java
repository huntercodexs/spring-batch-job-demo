package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class EnrollmentValidationSecondWriter {

    @Autowired
    DownloadFileFromSftpSecondWriter downloadFileFromSftpSecondWriter;

    @Autowired
    ReportSecondWriter reportSecondWriter;

    @Bean("writerSecondStep")
    public ItemWriter<String> writerSecondStep() {

        log.info("EnrollmentValidationSecondWriter say: (writerSecondStep) writerSecondStep is starting");

        return new CompositeItemWriterBuilder<String>()
            .delegates(Arrays.asList(downloadFileFromSftpSecondWriter, reportSecondWriter))
            .build();
    }
}
