package com.lsx;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;

import com.lsx.util.SaxUtil;

@Mojo(name="start")
public class Engine extends AbstractMojo {
	
	private Map<String, String> tables=new HashMap<String, String>();
	
	@Parameter( property = "start.config", defaultValue = "engine.xml" )
	private String config;
	
	public void execute() throws MojoExecutionException {
		System.out.println("start=======================");
		Document doc;
		try{
			doc = SaxUtil.analyXmlPath(Constant.baseDir+Constant.resources+File.separator+this.config);
		}catch(Exception e){
			throw new MojoExecutionException("xml配置文件格式不正确");
		}
		Element root = doc.getRootElement();
		Element database = root.getChild("database");
		Element javaFile = root.getChild("javaFile");
		List<Element> list = database.getChildren("table");
		if(database.getAttribute("url") != null &&
				StringUtils.isNotBlank(database.getAttributeValue("url"))){
			Constant.url = database.getAttributeValue("url");
		}
		if(database.getAttribute("dialect") != null &&
				StringUtils.isNotBlank(database.getAttributeValue("dialect"))){
			Constant.dialect = database.getAttributeValue("dialect").toLowerCase();
		}
		if(database.getAttribute("database") != null &&
				StringUtils.isNotBlank(database.getAttributeValue("database"))){
			Constant.database = database.getAttributeValue("database");
		}else{
			if(database.getAttribute("dialect") != null &&
					database.getAttributeValue("dialect").toLowerCase().equals("mysql")){
				throw new MojoExecutionException("请设置数据库名称");
			}
		}
		if(database.getAttribute("username") != null){
			Constant.username = database.getAttributeValue("username");
		}
		if(database.getAttribute("password") != null){
			Constant.password = database.getAttributeValue("password");
		}
		if(database.getAttribute("modelName") != null){
			Constant.modelName = database.getAttributeValue("modelName");
		}else{
			Element modelNames = database.getChild("modelNames");
			if(modelNames != null){
				List<Element> modelNameList = modelNames.getChildren("modelName");
				for(Element modelName : modelNameList){
					Constant.modelName += modelName.getText()+",";
				}
				if(Constant.modelName.length()>1){
					Constant.modelName = Constant.modelName.substring(0, Constant.modelName.length()-1);
				}
			}
		}
		
		if(javaFile.getAttribute("java") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("java"))){
			Constant.java = javaFile.getAttributeValue("java");
		}
		if(javaFile.getAttribute("resource") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("resource"))){
			Constant.resources = javaFile.getAttributeValue("resource");
		}
		if(javaFile.getAttribute("author") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("author"))){
			Constant.author = javaFile.getAttributeValue("author");
		}
		if(javaFile.getAttribute("projectName") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("projectName"))){
			Constant.projectName = javaFile.getAttributeValue("projectName");
		}
		if(javaFile.getAttribute("isCreateId") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("isCreateId"))){
			try {
				Constant.isCreateId = javaFile.getAttribute("isCreateId").getBooleanValue();
			} catch (DataConversionException e) {
				Constant.isCreateId = true;
			}
		}
		if(javaFile.getAttribute("path") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("path"))){
			Constant.path = javaFile.getAttributeValue("path");
		}else{
			throw new MojoExecutionException("请设置基础代码包");
		}
		if(javaFile.getAttribute("exceptionClass") != null && 
				StringUtils.isNotBlank(javaFile.getAttributeValue("exceptionClass")) &&
				javaFile.getAttribute("exceptionPath") != null && 
						StringUtils.isNotBlank(javaFile.getAttributeValue("exceptionPath"))){
			Constant.exceptionClass = javaFile.getAttributeValue("exceptionClass");
			Constant.exceptionPath = javaFile.getAttributeValue("exceptionPath");
		}
		if(javaFile.getAttribute("controllerPath") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("controllerPath"))){
			Constant.controllerPath = javaFile.getAttributeValue("controllerPath");
		}
		if(javaFile.getAttribute("entityPath") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("entityPath"))){
			Constant.entityPath = javaFile.getAttributeValue("entityPath");
		}
		if(javaFile.getAttribute("servicePath") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("servicePath"))){
			Constant.servicePath = javaFile.getAttributeValue("servicePath");
		}
		if(javaFile.getAttribute("xmlPath") != null &&
				StringUtils.isNotBlank(javaFile.getAttributeValue("xmlPath"))){
			Constant.xmlPath = javaFile.getAttributeValue("xmlPath");
		}
		Constant.ctx.put("path", Constant.path);
		Constant.ctx.put("controllerPath", Constant.controllerPath);
		Constant.ctx.put("servicePath", Constant.servicePath);
		Constant.ctx.put("entityPath", Constant.entityPath);
		Constant.ctx.put("daoPath", Constant.daoPath);
		Constant.ctx.put("responsePath", Constant.responsePath);
		Constant.ctx.put("isCreateId", Constant.isCreateId);
		Constant.ctx.put("exceptionClass", Constant.exceptionClass);
		Constant.ctx.put("exceptionPath", Constant.exceptionPath);
		Constant.ctx.put("dialect", Constant.dialect);
		for(Element table : list){
			this.tables.put(table.getAttributeValue("name"), table.getAttributeValue("desc"));
		}
		try{
			GenerateUtil.product(this.tables);
		}catch (Exception e){
			e.printStackTrace();
			throw new MojoExecutionException(e.getMessage());
		}
	}

	public Map<String, String> getTables() {
		return tables;
	}

	public void setTables(Map<String, String> tables) {
		this.tables = tables;
	}
	
}
