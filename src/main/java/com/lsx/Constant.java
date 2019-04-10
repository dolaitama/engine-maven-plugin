package com.lsx;

import java.io.File;

import org.apache.velocity.VelocityContext;

/**
 * 代码生成器常量
 *
 * @author sunlei
 * @version [版本号, 2016-3-6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Constant {
	
	public static final String vmPath="com/lsx/vm/";

	public static String baseDir = System.getProperty("user.dir")
			+ File.separator + "src" + File.separator + "main" + File.separator;
	public static String java = "java";
	
	public static String resources = "resources";
	
	public static String exceptionClass;
	
	public static String exceptionPath;

	/**
	 * 数据库链接地址
	 */
	public static String url="";
	
	/**
	 * 数据库名称
	 */
	public static String database="";

	/**
	 * 数据库用户名
	 */
	public static String username="";

	/**
	 * 数据库密码
	 */
	public static String password="";
	
	public static String dialect="mysql";

	/**
	 * 类头部作者
	 */
	public static String author = "administrator";

	/**
	 * 表的模块名称 例：金枫创投为jinfeng_
	 */
	public static String modelName = "";

	/**
	 * 项目名称 例：金枫创投
	 */
	public static String projectName="";

	/**
	 * 是否生成带ID的插入语句
	 */
	public static boolean isCreateId = true;
	
	/**
	 * 项目基础地址
	 */
	public static String path = "";
	
	/**
	 * 生成控制器文件所在的包
	 */
	public static String controllerPath = "controller";

	/**
	 * 生成实体类文件所在的包
	 */
	public static String entityPath = "entity";

	/**
	 * 生成接口文件所在的包
	 */
	public static String servicePath = "service";
	
	/**
	 * 返回实体所在的包 
	 */
	public static String responsePath = "response";

	/**
	 * 生成DAO文件所在的包
	 */
	public static String daoPath = "dao";
	
	/**
	 * 生成mapper.xml文件所在的包
	 */
	public static String xmlPath = "mapper";
	
	/**
	 * 是否需要导入包java.util.*
	 */
	public static boolean f_util = false;

	/**
	 * 是否需要导入包java.sql.*
	 */
	public static boolean f_sql = false;
	
	/**
	 * 列名数组
	 */
	public static String[] realnames;

	/**
	 * 列名数组
	 */
	public static String[] colnames;

	/**
	 * 列名类型数组
	 */
	public static String[] colTypes;

	/**
	 * 注释数组
	 */
	public static String[] strs;
	
	public static String annotation;
	
	public static String tableName;
	
	public static VelocityContext ctx = new VelocityContext();
	
}
