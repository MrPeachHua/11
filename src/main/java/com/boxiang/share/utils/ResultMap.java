package com.boxiang.share.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by dfl on 2016/5/11.
 */
public class ResultMap<K extends String, V extends Object> extends HashMap<String, Object> {

    public ResultMap() {
        super();
    }

    public ResultMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 通过Map构造
     *
     * @param m
     */
    public ResultMap(Map<? extends String, ?> m) {
        super(m.size());
        for (Map.Entry<? extends String, ?> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 通过实体类构造Map
     *
     * @param entity
     * @throws IllegalAccessException
     */
    public ResultMap(Serializable entity) throws IllegalAccessException {
        this(entity, false);
    }

    /**
     * 通过实体类构造Map
     *
     * @param entity                   实体对象
     * @param mapUnderscoreToCamelCase Key是否需要下划线转驼峰
     * @throws IllegalAccessException
     */
    public ResultMap(Serializable entity, boolean mapUnderscoreToCamelCase) throws IllegalAccessException {
        super(entity.getClass().getDeclaredFields().length);
        Field[] fields = entity.getClass().getDeclaredFields();
        if (mapUnderscoreToCamelCase) {
            for (Field f : fields) {
                f.setAccessible(true);
                String k = f.getName();
                Object v = f.get(entity);
                this.put(k, v);
            }
        } else {
            for (Field f : fields) {
                f.setAccessible(true);
                String k = f.getName();
                Object v = valueConvert(f.get(entity));
                super.put(k, v);
            }
        }
    }

    @Override
    public Object put(String key, Object value) {
        String k = mapUnderscoreToCamelCase(key);
        String v = valueConvert(value);
        return super.put(k, v);
    }

    /**
     * 把值转为常用的String格式
     *
     * @param value
     * @return
     */
    public static String valueConvert(Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Integer) {
            return Integer.toString((int) value);
        } else if (value instanceof Timestamp || value instanceof Date) {
            return DateUtil.date2str((Date) value, DateUtil.DATETIME_FORMAT);
        } else {
            return value.toString();
        }
    }

    /**
     * 下划线转驼峰
     *
     * @param key
     * @return
     */
    public static String mapUnderscoreToCamelCase(String key) {
        if (key.contains("_")) {
            String[] names = key.toLowerCase().split("_");
            StringBuilder sb = new StringBuilder(names[0]);
            for (int i = 1; i < names.length; i++) {
                sb.append(names[i].substring(0, 1).toUpperCase()).append(names[i].substring(1));
            }
            return sb.toString();
        }
        if (Character.isUpperCase(key.charAt(key.length() - 1))) {
            return key.toLowerCase();
        }
        if (Character.isUpperCase(key.charAt(0))) {
            return key.substring(0, 1).toLowerCase() + key.substring(1);
        }
        return key;
    }

    public static List<Map<String, Object>> resultMapWithList(List<Map<String, Object>> list) {
        List newList = new ArrayList(list.size());
        for (Map<String, Object> map : list) {
            newList.add(new ResultMap(map));
        }
        return newList;
    }

    public static List<Map<String, Object>> resultMapWithList(List<?> list, String[] excludeFields) throws IllegalAccessException {
        List newList = new ArrayList(list.size());
        for (Object entity : list) {
            Map map = new ResultMap((Serializable) entity, false);
            for (String key : excludeFields) {
                map.remove(key);
            }
            newList.add(map);
        }
        return newList;
    }

}
