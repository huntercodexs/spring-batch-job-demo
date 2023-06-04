package com.huntercodexs.demojobs.jobs.enrollmentValidation.task.firststep.writer;

import com.huntercodexs.demojobs.jobs.enrollmentValidation.dto.EnrollmentValidationDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

@Component
public class GeneratorFirstWriter implements ItemWriter<EnrollmentValidationDto> {

    @Value("${csv.filepath}")
    String csvFilepath;

    @Override
    public void write(List<? extends EnrollmentValidationDto> enrollmentValidationDto) {

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

    /*public Element xmlGeneratorReader(int index, String column) throws ParserConfigurationException, SAXException, IOException {

        System.out.println("[DEBUG] XML READER " + index);

        File file = new File("src/main/resources/generator.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();
        System.out.println("[DEBUG] DOCUMENT " + document);
        System.out.println("[DEBUG] Root Element :" + document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("field");
        System.out.println("[DEBUG] NODE LIST " + nodeList);

        Node node = nodeList.item(index);
        System.out.println("[DEBUG] Current Element :" + node.getNodeName());

        Element element;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            element = (Element) node;
            System.out.println("[DEBUG] NAME : " + element.getElementsByTagName("name").item(0).getTextContent());
            System.out.println("[DEBUG] TYPE : " + element.getElementsByTagName("type").item(0).getTextContent());
            System.out.println("[DEBUG] COLUMN : " + element.getElementsByTagName("column").item(0).getTextContent());
            System.out.println("[DEBUG] SIZE : " + element.getElementsByTagName("size").item(0).getTextContent());
            System.out.println("[DEBUG] FILL : " + element.getElementsByTagName("fill").item(0).getTextContent());
            System.out.println("[DEBUG] ALIGN : " + element.getElementsByTagName("align").item(0).getTextContent());

            if (element.getElementsByTagName("column").item(0).getTextContent().equals(column)) {

                System.out.println("[DEBUG] >>>>>>>>>>>>>>>>>>");
                System.out.println("[DEBUG] " + element.getElementsByTagName("column").item(0).getTextContent());

                return element;
            }
        }

        return null;
    }*/

    private void fileGeneratorByStream(EnrollmentValidationDto enrollmentItem) throws IOException, ParserConfigurationException, SAXException {

        OutputStream os = new FileOutputStream(csvFilepath+"spring-batch-job-demo-data.txt", true);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);

        String record = "";

        /*System.out.println("[DEBUG] ========================================================");
        System.out.println("[DEBUG] " + xmlGeneratorReader(0, "id"));
        System.out.println("[DEBUG] " + xmlGeneratorReader(1, "name"));
        System.out.println("[DEBUG] " + xmlGeneratorReader(2, "description"));
        System.out.println("[DEBUG] " + xmlGeneratorReader(3, "price"));
        System.out.println("[DEBUG] ========================================================");*/

        record += String.format("%06d", enrollmentItem.getId());
        record += String.format("%30s", enrollmentItem.getName());
        record += String.format("%-80s", enrollmentItem.getDescription());
        record += String.format("%10s", enrollmentItem.getPrice());

        br.write(record);
        br.newLine();
        br.close();

    }

    private void fileGeneratorByIO(EnrollmentValidationDto enrollmentItem) throws IOException {

        File path = new File(csvFilepath);
        File file = new File(path, "spring-batch-job-demo-data.txt");

        String record = "";

        record += String.format("%06d", enrollmentItem.getId());
        record += String.format("%30s", enrollmentItem.getName());
        record += String.format("%-80s", enrollmentItem.getDescription());
        record += String.format("%10s", enrollmentItem.getPrice());

        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(record);
        printWriter.flush();
        printWriter.close();

    }

}
