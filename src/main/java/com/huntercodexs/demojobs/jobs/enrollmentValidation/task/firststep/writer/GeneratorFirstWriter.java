package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.xml.XmlToJsonTemplate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

@Component
public class GeneratorFirstWriter implements ItemWriter<EnrollmentValidationDto> {

    @Value("${txt.filepath}")
    String txtFilepath;

    @Value("${txt.filename}")
    String txtFilename;

    @Autowired
    XmlToJsonTemplate xmlToJsonTemplate;

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) throws ParserConfigurationException, IOException, SAXException {

        xmlToJsonTemplate.xmlLoader(null);

        enrollmentValidationDto.forEach(enrollmentItem -> {
            System.out.println("[DEBUG] [WRITE] >>> GeneratorFirstWriter");
            System.out.println("[DEBUG] " + enrollmentItem.getName());

            try {
                fileGeneratorByStream(enrollmentItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void fileGeneratorByStream(EnrollmentValidationDto enrollmentValidationDto) throws IOException, ParserConfigurationException, SAXException {

        OutputStream os = new FileOutputStream(txtFilepath.replaceAll("/$", "") +"/"+ txtFilename, true);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);

        String record = "";

        record += String.format(xmlToJsonTemplate.format("id"), enrollmentValidationDto.getId()) + " ";
        record += String.format(xmlToJsonTemplate.format("name"), enrollmentValidationDto.getName()) + " ";
        record += String.format(xmlToJsonTemplate.format("description"), enrollmentValidationDto.getDescription()) + " ";
        record += String.format(xmlToJsonTemplate.format("price"), enrollmentValidationDto.getPrice());

        br.write(record);
        br.newLine();
        br.close();

    }

    private void fileGeneratorByIO(EnrollmentValidationDto enrollmentValidationDto) throws IOException {

        File path = new File(txtFilepath);
        File file = new File(path, txtFilename);

        String record = "";

        record += String.format(xmlToJsonTemplate.format("id"), enrollmentValidationDto.getId()) + " ";
        record += String.format(xmlToJsonTemplate.format("name"), enrollmentValidationDto.getName()) + " ";
        record += String.format(xmlToJsonTemplate.format("description"), enrollmentValidationDto.getDescription()) + " ";
        record += String.format(xmlToJsonTemplate.format("price"), enrollmentValidationDto.getPrice());

        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(record);
        printWriter.flush();
        printWriter.close();

    }

}
