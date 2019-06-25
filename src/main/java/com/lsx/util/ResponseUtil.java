package com.lsx.util;

import java.io.File;

import com.lsx.Constant;

public class ResponseUtil {
	
	public ResponseUtil() throws Exception{
		initResult();
		initDataGrid();
	}

	private void initResult() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.responsePath.replaceAll("\\.", "/");
			VelocityUtil.productFile(path, "Result.java", "Result.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initDataGrid() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.responsePath.replaceAll("\\.", "/");
			VelocityUtil.productFile(path, "DataGrid.java", "DataGrid.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
