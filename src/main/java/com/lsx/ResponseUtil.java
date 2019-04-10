package com.lsx;

import java.io.File;
import java.io.PrintWriter;

import org.apache.velocity.Template;

import com.lsx.util.VelocityUtil;

public class ResponseUtil {
	
	public ResponseUtil() throws Exception{
		initResult();
		initDataGrid();
	}

	private void initResult() throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"Result.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.responsePath.replaceAll("\\.", "/");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+"Result.java";
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

	private void initDataGrid() throws Exception {
		Template temp = VelocityUtil.ve.getTemplate(Constant.vmPath+"DataGrid.vm", "UTF-8");
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.responsePath.replaceAll("\\.", "/");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String f=path+File.separator+"DataGrid.java";
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
