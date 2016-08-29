package com.boxiang.share.utils.synwhite;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhiteListRunTimeUtils {

	private static Logger logger = LoggerFactory.getLogger(WhiteListRunTimeUtils.class);
	// 定义一个私有的静态全局变量来保存该类的唯一实例

	private static WhiteListRunTimeUtils whiteListRunTimeUtils;

	private String filepath = null;

	/// 构造函数必须是私有的

	/// 这样在外部便无法使用 new 来创建该类的实例

	private WhiteListRunTimeUtils()

	{
		filepath = WhiteListRunTimeUtils.class.getClassLoader().getResource("spring/share/whiteListRunTime.xml").getPath();
	}

	/// 定义一个全局访问点

	/// 设置为静态方法

	/// 则在类的外部便无需实例化就可以调用该方法

	public static WhiteListRunTimeUtils getInstance()

	{

		// 这里可以保证只实例化一次

		// 即在第一次调用时实例化

		// 以后调用便不会再实例化

		if (whiteListRunTimeUtils == null)

		{

			whiteListRunTimeUtils = new WhiteListRunTimeUtils();

		}

		return whiteListRunTimeUtils;

	}

	public Integer getWhiteListRunTime() {

		Integer whiteListRunTime = null;

		// 读取XML文件中的数据

		logger.debug("xml filepath:{}", filepath);

		try {

			Document document = new SAXReader().read(filepath);

			// 将解析结果存储在HashMap中

			Map<String, String> map = new HashMap<String, String>();

			// 得到xml根元素

			Element root = document.getRootElement();

			// 得到根元素的全部子节点

			List<Element> elementList = root.elements();

			// 遍历全部子节点

			for (Element e : elementList) {

				map.put(e.getName(), e.getText());

			}

			whiteListRunTime = Integer.valueOf(map.get("WhiteListRunTime"));

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return whiteListRunTime;

	}

	public void setWhiteListRunTime(String whiteListRunTime) {

		logger.debug("xml filepath:{}", filepath);

		try {
			Document document = new SAXReader().read(filepath);

			// 得到xml根元素

			Element root = document.getRootElement();

			root.selectSingleNode("WhiteListRunTime").setText(whiteListRunTime);

			XMLWriter writer = new XMLWriter(new FileWriter(new File(filepath)));

			writer.write(document);

			writer.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

}
