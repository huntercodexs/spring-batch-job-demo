package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class SendFileToSftpWriter implements ItemWriter<EnrollmentValidationDto> {

    @Value("${txt.filepath}")
    String txtFilepath;

    @Value("${txt.filename}")
    String txtFilename;

    @Autowired
    SftpHandler sftpHandler;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) throws IOException {

        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(FORMATTER);

        try {
            sftpHandler.upload(fileExtractor(), txtFilename.split("\\.")[0] + "-" + dateTimeFormat + ".txt");
        } catch (RuntimeException re) {
            log.error("SendFileToSftpWriter say: (write) upload Exception: " + re.getMessage());
        }

        try {
            makeFileProcessed(txtFilename, dateTimeFormat);
        } catch (RuntimeException re) {
            log.error("SendFileToSftpWriter say: (write) makeFileProcessed Exception: " + re.getMessage());
        }

    }

    private InputStream fileExtractor() throws FileNotFoundException {
        String content = fileReader(sanitizePath(txtFilepath) + txtFilename);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        return inputStream;
    }

    private String fileReader(String filepath) {

        StringBuilder fileContent = new StringBuilder();

        File path = new File(txtFilepath);
        File file = new File(path, txtFilename);

        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            while ( ( line = bufferedReader.readLine() ) != null) {
                fileContent.append(line).append("\n");
            }

            return fileContent.toString();

        } catch (IOException e) {
            log.error("SendFileToSftpWriter say: (fileReader) Exception: " + e.getMessage());
            throw new RuntimeException("FILE READER EXCEPTION: " + e.getMessage());
        }

    }

    private void makeFileProcessed(String filename, String stamp) {

        String[] array = filename.split("\\.");
        String name = array[0];
        String extension = array[1];

        File oldName = new File(sanitizePath(txtFilepath) + filename);
        File newName = new File(sanitizePath(txtFilepath) + name +"-"+ stamp +"."+ extension +".processed");

        if (oldName.renameTo(newName)) {
            log.info("SendFileToSftpWriter say: (makeFileProcessed) File "+ oldName +" renamed successful to " + newName);
        } else {
            log.error("SendFileToSftpWriter say: (makeFileProcessed) File "+ oldName +" NOT renamed to " + newName);
        }

    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }
}
