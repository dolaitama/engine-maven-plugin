package com.lsx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class SaxUtil {

	public static Document analyXmlPath(String path) throws Exception {
		File file = new File(path);
		InputStreamReader reader = new InputStreamReader(new FileInputStream(
				file));
		BufferedReader br = new BufferedReader(reader);
		String str = "";
		String s = br.readLine();
		while (s != null) {
			str += s;
			s = br.readLine();
		}
		br.close();
		return analyStrXml(str);
	}

	/**
	 * 解析字符串类型的xml
	 * 
	 * @param xml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Document analyStrXml(String xml) throws JDOMException,
			IOException {
		/** *创建一个新的字符串*** */
		StringReader xmlReader = new StringReader(xml);
		/** **创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入 */
		InputSource xmlSource = new InputSource(xmlReader);
		/** *创建一个SAXBuilder* */
		SAXBuilder builder = new SAXBuilder();
		/** *通过输入源SAX构造一个Document** */
		Document doc = builder.build(xmlSource);
		return doc;
	}

}
