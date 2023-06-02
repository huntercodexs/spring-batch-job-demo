package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class GeneratorFirstWriter implements ItemWriter<EnrollmentValidationDto> {

    @Value("${csv.filepath}")
    String csvFilepath;

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) {

        enrollmentValidationDto.forEach(enrollmentItem -> {
            System.out.println("[WRITE] >>> GeneratorFirstWriter");
            System.out.println(enrollmentItem.getName());

            try {
                fileGenerator(enrollmentItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void fileGenerator(EnrollmentValidationDto enrollmentItem) throws IOException {

        OutputStream os = new FileOutputStream(csvFilepath+"spring-batch-job-demo-data.txt", true);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);

        String record = enrollmentItem.getId()+
                ";"+enrollmentItem.getName()+
                ";"+enrollmentItem.getDescription()+
                ";"+enrollmentItem.getPrice();

        br.write(record);
        br.newLine();
        br.close();

    }

}
