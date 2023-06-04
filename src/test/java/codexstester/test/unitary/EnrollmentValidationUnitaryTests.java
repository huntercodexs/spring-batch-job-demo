package codexstester.test.unitary;

import codexstester.setup.bridge.EnrollmentValidationBridgeTests;
import com.huntercodexs.demojobs.jobs.enrollmentValidation.xml.XmlHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class EnrollmentValidationUnitaryTests extends EnrollmentValidationBridgeTests {

    @Autowired
    XmlHandler xmlHandler;

    @Test
    public void propsTest() {
        System.out.println(unitaryProps);
    }

    @Test
    public void xmlLoaderTest() throws ParserConfigurationException, IOException, SAXException {

        xmlHandler.xmlLoader();

        System.out.println(xmlHandler.name(0));
        System.out.println(xmlHandler.type(0));
        System.out.println(xmlHandler.column(0));
        System.out.println(xmlHandler.size(0));
        System.out.println(xmlHandler.fill(0));
        System.out.println(xmlHandler.align(0));

        System.out.println(xmlHandler.name(1));
        System.out.println(xmlHandler.type(1));
        System.out.println(xmlHandler.column(1));
        System.out.println(xmlHandler.size(1));
        System.out.println(xmlHandler.fill(1));
        System.out.println(xmlHandler.align(1));

        System.out.println(xmlHandler.name(2));
        System.out.println(xmlHandler.type(2));
        System.out.println(xmlHandler.column(2));
        System.out.println(xmlHandler.size(2));
        System.out.println(xmlHandler.fill(2));
        System.out.println(xmlHandler.align(2));

        System.out.println(xmlHandler.name(3));
        System.out.println(xmlHandler.type(3));
        System.out.println(xmlHandler.column(3));
        System.out.println(xmlHandler.size(3));
        System.out.println(xmlHandler.fill(3));
        System.out.println(xmlHandler.align(3));

        System.out.println(xmlHandler.format(0));
        System.out.println(xmlHandler.format(1));
        System.out.println(xmlHandler.format(2));
        System.out.println(xmlHandler.format(3));

    }

}
