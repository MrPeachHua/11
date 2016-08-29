package com.boxiang.share.utils.xml;

import java.io.Serializable;

import com.boxiang.framework.base.Constants;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlStreamFactory {
	private XmlStreamFactory(){}
	
	public static XmlStreamFactory getInstance(){
		return instance;
	}
	
	private static final XmlStreamFactory instance = new XmlStreamFactory();
	
	/**
	 * @param object
	 * @param encoding 编码方式
	 * @return 对象转xml
	 */
	public String toXml(Object object,String encoding){
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		String xml = xStream.toXML(object);
		xml = "<?xml version=\"1.0\" encoding=\""
				+ encoding + "\" ?>\n" + xml;
		return xml;
	}

	/**
	 * @param object
	 * @return 对象转xml,默认UTF-8编码
	 */
	public String toXml(Object object){
		return toXml(object,Constants.CHARACTER_ENCODING_UTF8);
	}
	
	/**
	 * @param type
	 * @param xml
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public<T extends Serializable> T fromXml(final Class<?> type,String xml,String encoding){
		XStream xStream = new XStream(new DomDriver(encoding));
		xStream.processAnnotations(type);
		xStream.autodetectAnnotations(true);
		return (T) xStream.fromXML(xml);		
	}
	/**
	 * @param type
	 * @param xml
	 * @return 对象转xml,默认UTF-8编码
	 */
	public<T extends Serializable> T fromXml(final Class<?> type,String xml){
		XStream xStream = new XStream(new DomDriver("UTF-8"));
		xStream.processAnnotations(type);
		xStream.autodetectAnnotations(true);
		return fromXml(type,xml,Constants.CHARACTER_ENCODING_UTF8);		
	}
}
