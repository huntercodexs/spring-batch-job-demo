package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class EnrollmentValidationSecondReader {

    @Autowired
    SftpHandler sftpHandler;

    @Bean
    public ItemReader<String> readerSecondStep() throws IOException {
        return new InMemorySecondReader(sftpHandler);
    }

}






