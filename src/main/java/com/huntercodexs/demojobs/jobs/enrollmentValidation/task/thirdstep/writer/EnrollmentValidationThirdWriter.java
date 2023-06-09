package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class EnrollmentValidationThirdWriter {
    @Autowired
    ReportThirdWriter reportThirdWriter;

    @Autowired
    FinisherThirdWriter finisherThirdWriter;

    @Bean("writerThirdStep")
    public ItemWriter<String> writerThirdStep() {

        log.info("EnrollmentValidationThirdWriter say: (writerThirdStep) writerThirdStep is starting");

        return new CompositeItemWriterBuilder<String>()
            .delegates(Arrays.asList(reportThirdWriter, finisherThirdWriter))
            .build();
    }
}
