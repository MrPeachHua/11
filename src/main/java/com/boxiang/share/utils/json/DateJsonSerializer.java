package com.boxiang.share.utils.json;

import java.io.IOException;
import java.util.Date;

import com.boxiang.share.utils.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


/**
 * 日期Json序列化器，此序列化器适用yyyy-MM-dd格式
 */
public class DateJsonSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date date, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		generator.writeString(DateUtil.date2str(date, DateUtil.DATE_FORMAT));
	}
}
