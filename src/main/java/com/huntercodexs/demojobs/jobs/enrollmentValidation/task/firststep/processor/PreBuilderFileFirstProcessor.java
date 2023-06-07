package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer.BuilderFileFirstWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PreBuilderFileFirstProcessor implements ItemProcessor<EnrollmentValidationDto, EnrollmentValidationDto> {

    @Value("${txt.filepath}")
    String txtFilepath;

    @Value("${txt.filename}")
    String txtFilename;

    @Autowired
    BuilderFileFirstWriter builderFileFirstWriter;

    public EnrollmentValidationDto process(EnrollmentValidationDto enrollmentValidationDto) throws ParserConfigurationException, IOException, SAXException {

        List<EnrollmentValidationDto> enrollmentValidationDtoList = new ArrayList<>();
        enrollmentValidationDtoList.add(enrollmentValidationDto);

        builderFileFirstWriter.write(enrollmentValidationDtoList);

        if (!checkFileBuilder().exists()) {
            throw new RuntimeException("Error in process: File was not created !");
        }

        boolean deleted = checkFileBuilder().delete();

        if (!deleted) throw new RuntimeException("Error in process: File was not deleted !");

        return enrollmentValidationDto;

    }

    private File checkFileBuilder() {
        return new File(sanitizePath(txtFilepath) + txtFilename);
    }

    private String sanitizePath(String path) {
        return path.replaceAll("/$", "") + "/";
    }

}
