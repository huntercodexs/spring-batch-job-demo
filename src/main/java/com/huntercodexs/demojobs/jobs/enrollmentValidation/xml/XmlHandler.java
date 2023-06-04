package com.huntercodexs.demojobs.jobs.enrollmentValidation.xml;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlHandler {

    XmlHandlerDto xmlHandlerDto;

    public void xmlLoader() throws ParserConfigurationException, SAXException, IOException {

        File file = new File("src/main/resources/generator.xml");
        List<XmlHandlerFieldsDto> xmlList = new ArrayList<>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("field");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                XmlHandlerFieldsDto xmlHandlerFieldsDto = new XmlHandlerFieldsDto();

                xmlHandlerFieldsDto.setName(element.getElementsByTagName("name").item(0).getTextContent());
                xmlHandlerFieldsDto.setType(element.getElementsByTagName("type").item(0).getTextContent());
                xmlHandlerFieldsDto.setColumn(element.getElementsByTagName("column").item(0).getTextContent());
                xmlHandlerFieldsDto.setSize(element.getElementsByTagName("size").item(0).getTextContent());
                xmlHandlerFieldsDto.setFill(element.getElementsByTagName("fill").item(0).getTextContent());
                xmlHandlerFieldsDto.setAlign(element.getElementsByTagName("align").item(0).getTextContent());

                xmlList.add(xmlHandlerFieldsDto);
            }
        }

        this.xmlHandlerDto = new XmlHandlerDto();
        this.xmlHandlerDto.setXmlHandlerListDto(xmlList);

        System.out.println("[DEBUG] " + this.xmlHandlerDto);

    }

    public String name(int index) {
        return this.xmlHandlerDto.getXmlHandlerListDto().get(index).getName();
    }

    public String type(int index) {
        return this.xmlHandlerDto.getXmlHandlerListDto().get(index).getType();
    }

    public String column(int index) {
        return this.xmlHandlerDto.getXmlHandlerListDto().get(index).getColumn();
    }

    public String size(int index) {
        return this.xmlHandlerDto.getXmlHandlerListDto().get(index).getSize();
    }

    public String fill(int index) {
        return this.xmlHandlerDto.getXmlHandlerListDto().get(index).getFill();
    }

    public String align(int index) {
        return this.xmlHandlerDto.getXmlHandlerListDto().get(index).getAlign();
    }

    public String format(int index) {
        String type = type(index);
        String size = size(index);
        String fill = fill(index);
        String align = align(index);

        String direction = "";
        if (align.equals("right")) direction = "-";

        String charTo = "d";
        if (type.equals("string")) {
            charTo = "s";
            fill = "";
        }

        return "%"+direction+fill+size+charTo;
    }

}
