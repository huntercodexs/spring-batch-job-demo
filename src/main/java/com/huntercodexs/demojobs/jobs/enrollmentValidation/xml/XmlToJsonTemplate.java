package com.huntercodexs.demojobs.jobs.enrollmentValidation.xml;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
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
            log.error("XmlToJsonTemplate say: (format) use left or right to attribute [align]");
            throw new RuntimeException("Error: use left or right to attribute [align]");
        }

        if (align.equals("left")) direction = "-";

        String useChar = "d";

        if (!type.equals("string") && !type.equals("number")) {
            log.error("XmlToJsonTemplate say: (format) use number or string to attribute [type]");
            throw new RuntimeException("Error: use number or string to attribute [type]");
        }

        if (type.equals("string")) {
            useChar = "s";
            fill = "";
        }

        String result = "%"+direction+fill+size+useChar;

        log.info("XmlToJsonTemplate say: (format) result: " + result);

        return result;
    }

    public String filler(String jsonKey, String value) {
        JSONObject item = jsonItem(jsonKey);

        String type = item.getAsString("type");
        String size = item.getAsString("size");
        String fill = item.getAsString("fill");
        String align = item.getAsString("align");

        if (!align.equals("left") && !align.equals("right")) {
            log.error("XmlToJsonTemplate say: (filler) use left or right to attribute [align]");
            throw new RuntimeException("Error: use left or right to attribute [align]");
        }

        if (!type.equals("string") && !type.equals("number")) {
            log.error("XmlToJsonTemplate say: (filler) use number or string to attribute [type]");
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

        log.info("XmlToJsonTemplate say: (filler) result: " + formatted);

        return formatted;
    }

}
