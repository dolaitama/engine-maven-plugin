package com.lsx;

import java.io.File;
import java.io.PrintWriter;

import org.apache.velocity.Template;

import com.lsx.util.VelocityUtil;

public class DaoUtil {

	public DaoUtil() throws Exception {
		initBaseDao();
		initBaseDaoImpl();
	}

	private void initBaseDao() throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"BaseDao.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.daoPath.replaceAll("\\.", "/")+File.separator+"base";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+"BaseDao.java";
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

	private void initBaseDaoImpl() throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"BaseDaoImpl.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.daoPath.replaceAll("\\.", "/")+File.separator+"impl";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+"BaseDaoImpl.java";
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
