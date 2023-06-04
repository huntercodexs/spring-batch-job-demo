package com.huntercodexs.demojobs.jobs.enrollmentValidation.xml;

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

public class XmlHandler {

    public XmlHandlerDto xmlLoader() throws ParserConfigurationException, SAXException, IOException {

        XmlHandlerDto xmlHandlerDto = new XmlHandlerDto();

        File file = new File("src/main/resources/generator.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("field");

        System.out.println("[DEBUG] DOCUMENT " + document);
        System.out.println("[DEBUG] Root Element :" + document.getDocumentElement().getNodeName());
        System.out.println("[DEBUG] NODE LIST " + nodeList);

        Element element;

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            System.out.println("[DEBUG] Current Element :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                element = (Element) node;
                System.out.println("[DEBUG] NAME : " + element.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("[DEBUG] TYPE : " + element.getElementsByTagName("type").item(0).getTextContent());
                System.out.println("[DEBUG] COLUMN : " + element.getElementsByTagName("column").item(0).getTextContent());
                System.out.println("[DEBUG] SIZE : " + element.getElementsByTagName("size").item(0).getTextContent());
                System.out.println("[DEBUG] FILL : " + element.getElementsByTagName("fill").item(0).getTextContent());
                System.out.println("[DEBUG] ALIGN : " + element.getElementsByTagName("align").item(0).getTextContent());

                return xmlHandlerDto;
            }
        }

        return null;

    }

}
