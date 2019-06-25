package com.lsx.util;

import java.io.File;

import com.lsx.Constant;

public class DaoUtil {

	public DaoUtil() throws Exception {
		initBaseDao();
		initBaseDaoImpl();
	}

	private void initBaseDao() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.daoPath.replaceAll("\\.", "/")+File.separator+"base";
			VelocityUtil.productFile(path, "BaseDao.java", "BaseDao.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseDaoImpl() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.daoPath.replaceAll("\\.", "/")+File.separator+"impl";
			VelocityUtil.productFile(path, "BaseDaoImpl.java", "BaseDaoImpl.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
