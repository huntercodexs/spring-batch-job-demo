package codexstester.test.unitary;

import codexstester.setup.bridge.EnrollmentValidationBridgeTests;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.sftp.SftpHandler;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.processor.PreBuilderFileFirstProcessor;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer.ReportFirstWriter;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.task.secondstep.reader.EnrollmentValidationSecondReader;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.xml.XmlToJsonTemplate;
import org.junit.Test;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EnrollmentValidationUnitaryTests extends EnrollmentValidationBridgeTests {

    @Autowired
    XmlToJsonTemplate xmlToJsonTemplate;

    @Autowired
    SftpHandler sftpHandler;
    
    @Autowired
    ReportFirstWriter reportFirstWriter;

    @Autowired
    PreBuilderFileFirstProcessor preBuilderFileFirstProcessor;

    @Autowired
    EnrollmentValidationSecondReader enrollmentValidationSecondReader;

    @Test
    public void propsTest() {
        System.out.println(unitaryProps);
    }

    @Test
    public void xmlLoaderTest() throws ParserConfigurationException, IOException, SAXException {

        xmlToJsonTemplate.xmlLoader(null);
        System.out.println(xmlToJsonTemplate.jsonObject());
        System.out.println(xmlToJsonTemplate.jsonItem("price"));
        System.out.println(xmlToJsonTemplate.jsonKeys(null));
        System.out.println(xmlToJsonTemplate.format("price"));

    }

    @Test
    public void dynamicTest() throws ParserConfigurationException, IOException, SAXException {

        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();

        try {
            EnrollmentValidationDto.class.getDeclaredField("id").set(enrollmentValidationDto, 12345);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(EnrollmentValidationDto.class.getDeclaredField("id").get(enrollmentValidationDto));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        String record = "";
        List<String> dtoKeys = xmlToJsonTemplate.jsonKeys(null);

        for (String dtoKey : dtoKeys) {

            try {
                record += String.format(
                        xmlToJsonTemplate.format(dtoKey),
                        EnrollmentValidationDto.class.getDeclaredField(dtoKey).get(enrollmentValidationDto)) + " ";
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void generatorTest() throws ParserConfigurationException, IOException, SAXException {
        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();
        enrollmentValidationDto.setId(12345);
        enrollmentValidationDto.setName("Product Name");
        enrollmentValidationDto.setDescription("Description to product");
        enrollmentValidationDto.setPrice("100,00");

        String record = "";

        xmlToJsonTemplate.xmlLoader(null);

        System.out.println(xmlToJsonTemplate.jsonObject());

        record += String.format(xmlToJsonTemplate.format("id"), enrollmentValidationDto.getId()) + "|";
        record += String.format(xmlToJsonTemplate.format("name"), enrollmentValidationDto.getName()) + "|";
        record += String.format(xmlToJsonTemplate.format("description"), enrollmentValidationDto.getDescription()) + "|";
        record += String.format(xmlToJsonTemplate.format("price"), enrollmentValidationDto.getPrice()) + "|";
        record += xmlToJsonTemplate.filler("const1", "999") + "|";
        record += xmlToJsonTemplate.filler("const2", "F1F2F3F4F5F6F7F8F9F0") + "|";

        System.out.println("RESULT");
        System.out.println(record);
    }

    @Test
    public void sftpReadTest() throws IOException {
        sftpHandler.download("spring-batch-job-demo-data-20230606133100.txt.response");
    }

    @Test
    public void sftpListTest() throws IOException {
        System.out.println(Arrays.toString(sftpHandler.list("download/")));
    }

    @Test
    public void sftpFilesTest() throws IOException {
        System.out.println(sftpHandler.files("download/").toString());
    }

    @Test
    public void emailSendTest() {
        List<EnrollmentValidationDto> enrollmentValidationDtoList = new ArrayList<>();
        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();
        enrollmentValidationDto.setId(12345);
        enrollmentValidationDto.setName("Product Name");
        enrollmentValidationDto.setDescription("Description to product");
        enrollmentValidationDto.setPrice("100,00");
        enrollmentValidationDtoList.add(enrollmentValidationDto);
        reportFirstWriter.write(enrollmentValidationDtoList);
    }

    @Test
    public void processorBuilderFileTest() throws ParserConfigurationException, IOException, SAXException {
        EnrollmentValidationDto enrollmentValidationDto = new EnrollmentValidationDto();
        enrollmentValidationDto.setId(12345);
        enrollmentValidationDto.setName("Product Name");
        enrollmentValidationDto.setDescription("Description to product");
        enrollmentValidationDto.setPrice("100,00");
        preBuilderFileFirstProcessor.process(enrollmentValidationDto);
    }

    @Test
    public void readerSecondStepTest() throws Exception {
        ItemReader<String> r = enrollmentValidationSecondReader.readerSecondStep();
        System.out.println(r.read());
        System.out.println(r.read());
        System.out.println(Arrays.toString(Objects.requireNonNull(r.read()).lines().toArray()));
    }

}
