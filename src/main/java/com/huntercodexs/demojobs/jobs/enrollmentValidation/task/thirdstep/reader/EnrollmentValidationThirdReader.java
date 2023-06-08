package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.thirdstep.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class EnrollmentValidationThirdReader {

    @Autowired
    FileHandlerThirdReader fileHandlerThirdReader;

    @Bean
    public ItemReader<String> readerThirdStep() throws IOException {

        System.out.println("[READER-THIRD-STEP] >>> readerThirdStep");

        return new InMemoryThirdReader(fileHandlerThirdReader);
    }

}
