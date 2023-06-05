package codexstester.test.unitary;

import codexstester.setup.bridge.EnrollmentValidationBridgeTests;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.xml.XmlToJsonTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class EnrollmentValidationUnitaryTests extends EnrollmentValidationBridgeTests {

    @Autowired
    XmlToJsonTemplate xmlToJsonTemplate;

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

}
