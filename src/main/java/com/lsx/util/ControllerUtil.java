package com.lsx.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.lsx.Constant;

/**
 * 一键生成控制器
 * 
 * @author lsx
 * @version [版本号, 2018-4-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ControllerUtil {

	/**
	 * 全局类名
	 */
	public static String className = "";

	/**
	 * <默认构造函数>
	 */
	public ControllerUtil() throws Exception {
		initBaseController();
		if(StringUtils.isBlank(Constant.exceptionClass)){
			initExceptionController();
		}
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator+Constant.controllerPath.replaceAll("\\.", "/");
			VelocityUtil.productFile(path, TransFormUtil.initcap(Constant.tableName, true)+"Controller.java", "Controller.vm", true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseController() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.controllerPath.replaceAll("\\.", "/")+File.separator+"base";
			VelocityUtil.productFile(path, "BaseController.java", "BaseController.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private void initExceptionController() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.controllerPath.replaceAll("\\.", "/")+File.separator+"base";
			VelocityUtil.productFile(path, "ExceptionController.java", "ExceptionController.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}