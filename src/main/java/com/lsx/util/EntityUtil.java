package com.lsx.util;

import java.io.File;

import com.lsx.Constant;

/**
 * 一键生成实体类
 * 
 * @author lsx
 * @version [版本号, 2015-11-16]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EntityUtil {

	/**
	 * <默认构造函数>
	 * @throws Exception 
	 */
	public EntityUtil() throws Exception {
		initBaseEntity();
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator+Constant.entityPath.replaceAll("\\.", "/");
			VelocityUtil.productFile(path, File.separator+TransFormUtil.initcap(Constant.tableName, true)+".java", "Entity.vm", true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void initBaseEntity() throws Exception {
		try {
			String path = Constant.baseDir + Constant.java + File.separator
					+ Constant.path.replaceAll("\\.", "/")+File.separator
					+Constant.entityPath.replaceAll("\\.", "/")+File.separator+"base";
			VelocityUtil.productFile(path, "BaseEntity.java", "BaseEntity.vm", false);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}