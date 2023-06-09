package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class EnrollmentValidationThirdReader {

    @Autowired
    FileHandlerThirdReader fileHandlerThirdReader;

    @Bean
    public ItemReader<String> readerThirdStep() throws IOException {

        log.info("EnrollmentValidationThirdReader say: (readerThirdStep) readerThirdStep is starting");

        return new LoaderFileHandlerThirdReader(fileHandlerThirdReader);
    }

}
