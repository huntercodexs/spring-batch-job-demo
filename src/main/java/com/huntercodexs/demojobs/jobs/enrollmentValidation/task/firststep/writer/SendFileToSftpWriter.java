package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class SendFileToSftpWriter implements ItemWriter<EnrollmentValidationDto> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");

    @Value("${csv.filepath}")
    String csvFilepath;

    @Autowired
    SftpHandler sftpHandler;

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) throws IOException {

        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(FORMATTER);

        System.out.println("[DEBUG] >>> SFTP FILE");
        sftpHandler.uploadFile(fileExtractor(), "spring-batch-job-demo-data-"+dateTimeFormat+".txt");
        makeFileProcessed("spring-batch-job-demo-data.txt", dateTimeFormat);

    }

    private InputStream fileExtractor() throws FileNotFoundException {
        String content = fileReader(csvFilepath + "spring-batch-job-demo-data.txt");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        return inputStream;
    }

    private String fileReader(String filepath) {

        StringBuilder fileContent = new StringBuilder();

        File path = new File(csvFilepath);
        File file = new File(path, "spring-batch-job-demo-data.txt");

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ( ( line = bufferedReader.readLine() ) != null) {
                System.out.println("LINE IN THE FILE IS");
                System.out.println(line);
                fileContent.append(line).append("\n");
            }

            return fileContent.toString();

        } catch (IOException e) {
            throw new RuntimeException("FILE READER EXCEPTION: " + e.getMessage());
        }

    }

    private void makeFileProcessed(String filename, String stamp) {

        String[] array = filename.split("\\.");
        String name = array[0];
        String extension = array[1];

        File oldName = new File(csvFilepath+filename);
        File newName = new File(csvFilepath+name+"-"+stamp+"."+extension+".processed");

        if (oldName.renameTo(newName)) {
            System.out.println("[DEBUG] File renamed successful");
        } else {
            System.out.println("[DEBUG] File NOT renamed");
        }

    }
}
