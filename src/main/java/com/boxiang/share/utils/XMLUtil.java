package com.boxiang.share.utils;

import com.boxiang.share.utils.json.JsonMapper;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by dfl on 2016/8/9.
 */
public class XMLUtil {

    private final static XMLSerializer serializer = new XMLSerializer();

    public static String xmlToJson(String xml) {
        return serializer.read(xml).toString();
    }

    public static <T> T xmlToObject(String xml, Class<T> clazz) throws IOException {
        return (T) JsonMapper.fromJson(xmlToJson(xml), clazz);
    }

    public static Map xmlToMap(String xml) throws IOException {
        return xmlToObject(xml, Map.class);
    }

    public static String objectToXml(Object object, String encoding) {
        return serializer.write(JSONSerializer.toJSON(object), encoding);
    }

    public static String objectToXml(Object object) {
        return objectToXml(object, "UTF-8");
    }
}
