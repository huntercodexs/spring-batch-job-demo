package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
                fileGeneratorByStream(enrollmentItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void fileGeneratorByStream(EnrollmentValidationDto enrollmentItem) throws IOException {

        OutputStream os = new FileOutputStream(csvFilepath+"spring-batch-job-demo-data.txt", true);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);

        String record = String.format("%06d", enrollmentItem.getId())+
                " "+String.format("%30s", enrollmentItem.getName())+
                " "+String.format("%-80s", enrollmentItem.getDescription())+
                " "+String.format("%10s", enrollmentItem.getPrice());

        br.write(record);
        br.newLine();
        br.close();

    }

    private void fileGeneratorByIO(EnrollmentValidationDto enrollmentItem) throws IOException {

        File path = new File(csvFilepath);
        File file = new File(path, "spring-batch-job-demo-data.txt");

        String record = enrollmentItem.getId()+
                " "+enrollmentItem.getName()+
                " "+enrollmentItem.getDescription()+
                " "+enrollmentItem.getPrice();

        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(record);
        printWriter.flush();
        printWriter.close();

    }

}
