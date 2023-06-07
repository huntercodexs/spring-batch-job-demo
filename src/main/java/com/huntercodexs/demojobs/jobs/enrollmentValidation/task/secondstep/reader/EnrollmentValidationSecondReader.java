package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentValidationSecondReader {

    @Bean
    public ItemReader<String> readerSecondStep() {
        return new InMemorySecondReader();
    }

}






