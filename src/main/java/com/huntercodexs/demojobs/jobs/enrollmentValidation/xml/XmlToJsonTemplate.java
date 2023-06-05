package com.huntercodexs.demojobs.jobs.enrollmentValidation.xml;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class XmlToJsonTemplate extends XmlToJson {

    public String format(String jsonKey) {
        JSONObject item = jsonItem(jsonKey);
        String type = item.getAsString("type");
        String size = item.getAsString("size");
        String fill = item.getAsString("fill");
        String align = item.getAsString("align");

        String direction = "";
        if (align.equals("right")) direction = "-";

        String useChar = "d";
        if (type.equals("string")) {
            useChar = "s";
            fill = "";
        }

        return "%"+direction+fill+size+useChar;
    }

}
