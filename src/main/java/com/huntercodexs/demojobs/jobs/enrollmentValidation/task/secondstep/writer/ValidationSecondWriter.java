package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ValidationSecondWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> enrollmentValidationDto) {

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx");
        System.out.println(enrollmentValidationDto);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxxx");

        enrollmentValidationDto.forEach(enrollmentItem -> {
            System.out.println("[WRITE] >>> ValidationWriter");
        });

    }

}
