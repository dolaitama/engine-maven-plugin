package com.lsx;

import java.io.File;
import java.io.PrintWriter;

import org.apache.velocity.Template;

import com.lsx.util.TransFormUtil;
import com.lsx.util.VelocityUtil;

/**
 *
 * 一键生成sql文件
 *
 * @author hans
 * @version [版本号, 2015-11-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SqlUtil {
	
	public SqlUtil(String packagePath) throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"XML.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.resources + File.separator
					+ packagePath.replaceAll("\\.", "/");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+TransFormUtil.initcap(Constant.tableName, true)+".xml";
			PrintWriter fw = new PrintWriter(f);
			temp.merge(Constant.ctx, fw);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

}