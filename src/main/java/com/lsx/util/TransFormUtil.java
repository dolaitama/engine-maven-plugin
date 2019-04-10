package com.lsx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lsx.Constant;

public class TransFormUtil {
	
	/**
	 * 字符首字母小写
	 * 
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getToLowerStr(String str) {
		char[] chars = new char[1];
		chars[0] = str.charAt(0);
		String temp = new String(chars);
		if (chars[0] >= 'A' && chars[0] <= 'Z') {// 当为字母时，则转换为小写
			str = str.replaceFirst(temp, temp.toLowerCase());
		}
		return str;
	}
	
	/**
	 * 去前缀并把表的首字母改成大写
	 *
	 * @param str
	 * @return
	 */
	public static String initcap(String str, boolean upper) {
		String[] modelNameArr = Constant.modelName.split(",");
		for(String s : modelNameArr){
			if(str.contains(s)){
				str = str.replace(s, "");
				break;
			}
		}
		char[] ch = str.toCharArray();

		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		if(upper){
			return getUpperCamelStr(new String(ch));
		}
		return getLowerCamelStr(new String(ch));
	}
	
	/**
	 * 去掉属性中的下划线
	 * 
	 * @param s
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getUpperCamelStr(String str) {
		while (str.indexOf("_") > 0) {
			int index = str.indexOf("_");
			str = str.substring(0, index)
					+ str.substring(index + 1, index + 2).toUpperCase()
					+ str.substring(index + 2);
		}
		return str;
	}
	
	/**
	 * 去掉属性中的下划线
	 * 
	 * @param s
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getLowerCamelStr(String str) {
		str = str.toLowerCase();
		while (str.indexOf("_") > 0) {
			int index = str.indexOf("_");
			str = str.substring(0, index)
					+ str.substring(index + 1, index + 2).toUpperCase()
					+ str.substring(index + 2);
		}
		return str;
	}
	
	/**
	 * sql字段类型转为java类型
	 * 
	 * @param sqlType
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")
				|| sqlType.equalsIgnoreCase("integer")
				|| sqlType.equalsIgnoreCase("number")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		}
		if (sqlType.equalsIgnoreCase("image")) {
			return "Blob";
		} else if (sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("blob")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("Double")) {
			return "Double";
		} else {
			return "String";
		}
	}
	
	public static String nowStr(){
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

}
