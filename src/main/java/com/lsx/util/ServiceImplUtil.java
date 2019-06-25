package com.lsx.util;

import java.io.File;

import com.lsx.Constant;

/**
 *
 * 一键生成实现类
 *
 * @author lsx
 * @version [版本号, 2015-11-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServiceImplUtil {

	public ServiceImplUtil() throws Exception {
		initBaseServiceImpl();
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.servicePath.replaceAll("\\.", "/")+File.separator+"impl";
			VelocityUtil.productFile(path, File.separator+TransFormUtil.initcap(Constant.tableName, true)+"ServiceImpl.java", "ServiceImpl.vm", true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseServiceImpl() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.servicePath.replaceAll("\\.", "/")+File.separator+"impl"+File.separator+"base";
			VelocityUtil.productFile(path, "BaseServiceImpl.java", "BaseServiceImpl.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}