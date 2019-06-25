package com.lsx.util;

import java.io.File;

import com.lsx.Constant;

/**
 *
 * 一键生成接口
 *
 * @author lsx
 * @version [版本号, 2015-11-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServiceUtil {

	public ServiceUtil() throws Exception {
		initBaseService();
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator+Constant.servicePath.replaceAll("\\.", "/");
			VelocityUtil.productFile(path, File.separator+TransFormUtil.initcap(Constant.tableName, true)+"Service.java", "Service.vm", true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseService() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.servicePath.replaceAll("\\.", "/")+File.separator+"base";
			VelocityUtil.productFile(path, "BaseService.java", "BaseService.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}