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

        if (!align.equals("left") && !align.equals("right")) {
            throw new RuntimeException("Error: use left or right to attribute [align]");
        }

        if (align.equals("left")) direction = "-";

        String useChar = "d";

        if (!type.equals("string") && !type.equals("number")) {
            throw new RuntimeException("Error: use number or string to attribute [type]");
        }

        if (type.equals("string")) {
            useChar = "s";
            fill = "";
        }

        return "%"+direction+fill+size+useChar;
    }

    public String filler(String jsonKey, String value) {
        JSONObject item = jsonItem(jsonKey);

        String type = item.getAsString("type");
        String size = item.getAsString("size");
        String fill = item.getAsString("fill");
        String align = item.getAsString("align");

        if (!align.equals("left") && !align.equals("right")) {
            throw new RuntimeException("Error: use left or right to attribute [align]");
        }

        if (!type.equals("string") && !type.equals("number")) {
            throw new RuntimeException("Error: use number or string to attribute [type]");
        }

        String formatted = value;

        int lenValue = value.length();
        int lenFill = Integer.parseInt(size) - lenValue;
        String repeat = fill.repeat(lenFill);

        if (align.equals("left")) {
            formatted = value + repeat;
        } else {
            formatted = repeat + value;
        }

        return formatted;
    }

}
