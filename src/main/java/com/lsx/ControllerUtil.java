package com.lsx;

import java.io.File;
import java.io.PrintWriter;

import org.apache.velocity.Template;

import com.lsx.util.TransFormUtil;
import com.lsx.util.VelocityUtil;

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
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"Controller.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator+Constant.controllerPath.replaceAll("\\.", "/");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+TransFormUtil.initcap(Constant.tableName, true)+"Controller.java";
			PrintWriter fw = new PrintWriter(f);
			temp.merge(Constant.ctx, fw);
			fw.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseController() throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"BaseController.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.controllerPath.replaceAll("\\.", "/")+File.separator+"base";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+"BaseController.java";
			file = new File(f);
			if(!file.exists()){
				PrintWriter fw = new PrintWriter(f);
				temp.merge(Constant.ctx, fw);
				fw.close();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}