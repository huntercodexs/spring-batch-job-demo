package com.huntercodexs.demojobs.jobs.enrollmentValidation.xml;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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
public class XmlToJson {

    @Value("${xml.handler.filename:template-sample.xml}")
    String xmlFilename;

    @Value("${xml.handler.field.sequence-name:sequence}")
    String fieldsSequence;

    @Value("${xml.handler.main-field:jsonKey}")
    String mainField;

    @Value("${xml.handler.fields-identify:field}")
    String fieldsIdentify;

    JSONObject jsonObject;

    public Document xmlParser(String xmlOverwrite) throws ParserConfigurationException, IOException, SAXException {

        if (xmlOverwrite != null) xmlFilename = xmlOverwrite;

        File file = new File("src/main/resources/"+xmlFilename);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        document.getDocumentElement().normalize();

        return document;
    }

    public String[] xmlFields(String xmlOverwrite) throws ParserConfigurationException, IOException, SAXException {

        NodeList sequence = xmlParser(xmlOverwrite).getElementsByTagName(fieldsSequence);

        return sequence
                .item(0)
                .getTextContent()
                .replaceAll("[\n \r\t]", "")
                .split(";");

    }

    public void xmlLoader(String xmlOverwrite) throws ParserConfigurationException, SAXException, IOException {

        Document document = xmlParser(xmlOverwrite);
        String[] fields = xmlFields(xmlOverwrite);

        String jsonKey = document.getElementsByTagName(mainField).item(0).getTextContent();

        NodeList fieldList = document.getElementsByTagName(fieldsIdentify);

        this.jsonObject = new JSONObject();

        for (int i = 0; i < fieldList.getLength(); i++) {

            Node node = fieldList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                JSONObject jsonObjectItems = new JSONObject();
                for (String field : fields) {
                    jsonObjectItems.appendField(field, element.getAttribute(field));
                }
                this.jsonObject.appendField(element.getAttribute(jsonKey), jsonObjectItems);
            }
        }
    }

    public JSONObject jsonObject() {
        return this.jsonObject;
    }

    public JSONObject jsonItem(String jsonKey) {
        if (this.jsonObject.containsKey(jsonKey)) {
            return (JSONObject) this.jsonObject.get(jsonKey);
        }
        throw new RuntimeException("Invalid jsonKey " + jsonKey);
    }

    public List<String> jsonKeys(String xmlOverwrite) throws ParserConfigurationException, SAXException, IOException {

        Document document = xmlParser(xmlOverwrite);
        String[] fields = xmlFields(xmlOverwrite);
        List<String> jsonKeysList = new ArrayList<>();

        String jsonKey = document.getElementsByTagName(mainField).item(0).getTextContent();

        NodeList fieldList = document.getElementsByTagName(fieldsIdentify);

        for (int i = 0; i < fieldList.getLength(); i++) {

            Node node = fieldList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                for (String field : fields) {
                    if (field.equals(jsonKey)) {
                        jsonKeysList.add(element.getAttribute(jsonKey));
                    }
                }
            }
        }

        return jsonKeysList;
    }

}
