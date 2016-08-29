package com.boxiang.share.utils.xml;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.boxiang.share.utils.DateUtil;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class DateTimeXStreamConverter extends AbstractSingleValueConverter {
	private static final DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(DateUtil.DATETIME_FORMAT);
	
	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return type.equals(Date.class); 
	}

	@Override
	public Object fromString(String str) { 
        // 这里将字符串转换成日期  
        try {  
            return DEFAULT_DATEFORMAT.parseObject(str);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        throw new ConversionException("Cannot parse date " + str);
	}
	
	@Override  
    public String toString(Object obj) {  
        // 这里将日期转换成字符串  
        return DEFAULT_DATEFORMAT.format((Date) obj);  
    }
}
