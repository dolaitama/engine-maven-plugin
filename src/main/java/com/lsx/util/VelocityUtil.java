package com.lsx.util;

import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;

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
	
}
