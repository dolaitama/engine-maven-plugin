package com.lsx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;

import com.lsx.util.TransFormUtil;
import com.lsx.util.VelocityUtil;

/**
 * 自动生成代码工具类
 *
 * @author sunlei
 * @version [版本号, 2016-3-6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GenerateUtil {
	
	public static VelocityEngine ve;

	public static void product(Map<String, String> map) throws Exception{
		//初始化velocityengine
		new VelocityUtil();
		// 生成 bean, service, serviceImpl, sql
		Connection conn = null;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Constant.annotation = entry.getValue();
			Constant.tableName = entry.getKey();
			//velocity模板参数设置
			Constant.ctx.put("tablename", Constant.tableName);//表名
			Constant.ctx.put("uname", TransFormUtil.initcap(Constant.tableName, true));//去前缀首字母大写表名
			Constant.ctx.put("lname", TransFormUtil.initcap(Constant.tableName, false));//去前缀首字母小写表名
			
			conn = init(conn, Constant.tableName);
			new ResponseUtil();
			new EntityUtil();
			new DaoUtil();
			new ServiceUtil();
			new ServiceImplUtil();
			new ControllerUtil();
			new SqlUtil(Constant.xmlPath);
			System.out.println(Constant.tableName+" producted!");
		}
		if(conn != null){
			conn.close();
		}
		System.out.println("end=======================");
	}

	private static Connection init(Connection conn, String key) throws Exception {
		if(conn == null){
			if(Constant.dialect.equals("mysql")){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(Constant.url,
						Constant.username, Constant.password);
			}
			if(Constant.dialect.equals("oracle")){
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(Constant.url,
						Constant.username, Constant.password);
			}
		}
		if(Constant.dialect.equals("mysql")){
			initMySql(key, conn);
		}
		if(Constant.dialect.equals("oracle")){
			initOracle(key, conn);
		}
		return conn;
//		if(conn != null){
//			conn.close();
//		}
	}
	
	private static void initOracle(String key, Connection conn) throws Exception {
		String sql = "select utc.column_name,utc.data_type,ucc.comments "
				+ " from user_tab_cols utc "
				+ " left join user_col_comments ucc on ucc.column_name=utc.column_name and ucc.table_name=utc.table_name "
				+ " where utc.table_name=upper('"+key+"') ";

		Statement stmt = null;
		ResultSet res = null;
		stmt = conn.createStatement();
		res = stmt.executeQuery(sql);

		List<Bean> models = new ArrayList<Bean>();
		// 循环遍历查询结果集
		int count=0;
		while (res.next()) {
			Bean mol = new Bean();
			mol.setColName(TransFormUtil.getLowerCamelStr(res.getString(1)));
			mol.setColType(res.getString(2));
			mol.setUname(TransFormUtil.initcap(res.getString(1), true));
			mol.setType(TransFormUtil.sqlType2JavaType(mol.getColType()));
			mol.setStr(res.getString(3));
			mol.setRealname(res.getString(1));
			models.add(mol);
			count ++;
		}
		if(count==0){
			System.out.println(Constant.tableName +" error");
			throw new Exception("未查询到表列信息");
		}
		Constant.ctx.put("cols", models);
		Constant.colnames = new String[models.size()];
		Constant.realnames = new String[models.size()];
		Constant.colTypes = new String[models.size()];
		Constant.strs = new String[models.size()];
		for (int i = 0; i < models.size(); i++) {
			Constant.realnames[i] = models.get(i).getRealname();
			Constant.colnames[i] = models.get(i).getColName();
			Constant.colTypes[i] = models.get(i).getColType();
			Constant.strs[i] = models.get(i).getStr();
			if (Constant.colTypes[i].equalsIgnoreCase("datetime")) {
				Constant.f_util = true;
			}
			if (Constant.colTypes[i].equalsIgnoreCase("image")
					|| Constant.colTypes[i].equalsIgnoreCase("text")) {
				Constant.f_sql = true;
			}
		}
		res.close();
		stmt.close();
	}

	private static void initMySql(String key, Connection conn) throws Exception {
		String sql = "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
				+ key + "' AND TABLE_SCHEMA = '" + Constant.database + "'";

		Statement stmt = null;
		ResultSet res = null;
		stmt = conn.createStatement();
		res = stmt.executeQuery(sql);

		List<Bean> models = new ArrayList<Bean>();
		// 循环遍历查询结果集
		int count = 0;
		while (res.next()) {
			Bean mol = new Bean();
			mol.setColName(TransFormUtil.getLowerCamelStr(res.getString(1)));
			mol.setColType(res.getString(2));
			mol.setUname(TransFormUtil.initcap(res.getString(1), true));
			mol.setType(TransFormUtil.sqlType2JavaType(mol.getColType()));
			mol.setStr(res.getString(3));
			mol.setRealname(res.getString(1));
			models.add(mol);
			count++;
		}
		if(count==0){
			System.out.println(Constant.tableName +" error");
			throw new Exception("未查询到表列信息");
		}
		Constant.ctx.put("cols", models);
		Constant.colnames = new String[models.size()];
		Constant.realnames = new String[models.size()];
		Constant.colTypes = new String[models.size()];
		Constant.strs = new String[models.size()];
		for (int i = 0; i < models.size(); i++) {
			Constant.realnames[i] = models.get(i).getRealname();
			Constant.colnames[i] = models.get(i).getColName();
			Constant.colTypes[i] = models.get(i).getColType();
			Constant.strs[i] = models.get(i).getStr();
			if (Constant.colTypes[i].equalsIgnoreCase("datetime")) {
				Constant.f_util = true;
			}
			if (Constant.colTypes[i].equalsIgnoreCase("image")
					|| Constant.colTypes[i].equalsIgnoreCase("text")) {
				Constant.f_sql = true;
			}
		}
		res.close();
		stmt.close();
	}

}
