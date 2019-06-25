package com.lsx.util;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import com.lsx.Constant;

public class VelocityUtil {
	
	public static VelocityEngine ve;
	
	public VelocityUtil(){
		Properties properties=new Properties();
		properties.setProperty("resource.loader", "jar");
		String jarp = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		properties.setProperty("jar.resource.loader.class", "org.apache.velocity.runtime.resource.loader.JarResourceLoader");
		properties.setProperty("jar.resource.loader.path", "jar:file:"+jarp);
		properties.put("input.encoding", "UTF-8");
        properties.put("output.encoding", "UTF-8");
        ve = new VelocityEngine(properties);
	}
	
	public static void productFile(String path, String filename, String tmPath, boolean force) throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+tmPath, "UTF-8");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String f=path+File.separator+filename;
		file = new File(f);
		if(!file.exists() || force){
			PrintWriter fw = new PrintWriter(f);
			temp.merge(Constant.ctx, fw);
			fw.close();
		}
	}
	
}
