package com.lsx.util;

import java.io.File;

import com.lsx.Constant;

/**
 *
 * 一键生成sql文件
 *
 * @author lsx
 * @version [版本号, 2015-11-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SqlUtil {
	
	public SqlUtil(String packagePath) throws Exception {
		try {
			String path = Constant.baseDir + Constant.resources + File.separator
					+ packagePath.replaceAll("\\.", "/");
			VelocityUtil.productFile(path, File.separator+TransFormUtil.initcap(Constant.tableName, true)+".xml", "XML.vm", true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

}