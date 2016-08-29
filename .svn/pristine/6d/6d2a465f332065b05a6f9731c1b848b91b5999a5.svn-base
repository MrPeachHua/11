package com.boxiang.share.utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 全局Json对象映射
 */
public class GlobalObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	public GlobalObjectMapper() {
		//添加空值全局Json序列化器
		NullValueJsonSerializer nullValueJsonSerializer = new NullValueJsonSerializer();
		this.getSerializerProvider().setNullValueSerializer(nullValueJsonSerializer);	
		//this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
}
