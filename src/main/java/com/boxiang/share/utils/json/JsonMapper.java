package com.boxiang.share.utils.json;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Enumeration;

public class JsonMapper {

    private static ObjectMapper m = new ObjectMapper();
    private static JsonFactory jf = new JsonFactory();

    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass)
            throws JsonMappingException, JsonParseException, IOException {
        return m.readValue(jsonAsString, pojoClass);
    }

    public static <T> Object fromJson(FileReader fr, Class<T> pojoClass)
            throws JsonParseException, IOException {
        return m.readValue(fr, pojoClass);
    }


    public static String toJson(Object pojo, boolean prettyPrint)
            throws JsonMappingException, JsonGenerationException, IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = jf.createJsonGenerator(sw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        m.writeValue(jg, pojo);
        return sw.toString();
    }

    public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint)
            throws JsonMappingException, JsonGenerationException, IOException {
        JsonGenerator jg = jf.createJsonGenerator(fw);
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        m.writeValue(jg, pojo);
    }

    public static String getJsonString(HttpServletRequest request) throws IOException {
        String jsonString = null;
        jsonString = getJsonByRequestParameter(request);
        if (jsonString == null || jsonString.trim().length() == 0) {
            jsonString = getJsonByInputStream(request);
        }
        return jsonString;
    }

    public static String getJsonByInputStream(HttpServletRequest request) throws IOException {
        String jsonString = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        InputStream is = request.getInputStream();
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        baos.flush();
        jsonString = baos.toString("UTF-8");
        baos.close();
        is.close();
        return jsonString;
    }

    public static String getJsonByRequestParameter(HttpServletRequest request) {
        String jsonString = null;
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            jsonString = enumeration.nextElement();
        }
        return jsonString;
    }
}
