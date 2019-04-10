package com.lsx;

import java.io.File;
import java.io.PrintWriter;

import org.apache.velocity.Template;

import com.lsx.util.TransFormUtil;
import com.lsx.util.VelocityUtil;

/**
 *
 * 一键生成实现类
 *
 * @author hans
 * @version [版本号, 2015-11-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServiceImplUtil {

	public ServiceImplUtil() throws Exception {
		initBaseServiceImpl();
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"ServiceImpl.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.servicePath.replaceAll("\\.", "/")+File.separator+"impl";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+TransFormUtil.initcap(Constant.tableName, true)+"ServiceImpl.java";
			PrintWriter fw = new PrintWriter(f);
			temp.merge(Constant.ctx, fw);
			fw.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseServiceImpl() throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"BaseServiceImpl.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.servicePath.replaceAll("\\.", "/")+File.separator+"impl"+File.separator+"base";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+"BaseServiceImpl.java";
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