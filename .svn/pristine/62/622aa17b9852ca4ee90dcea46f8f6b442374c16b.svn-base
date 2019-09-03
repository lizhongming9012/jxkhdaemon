package org.jxkh.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public  class FreeMarkerCfg {

	@Autowired
	private static ConfigInfo configInfo;
	/* 在整个应用的生命周期中，这个工作你应该只做一次。 */
	/* 创建和调整配置。 */
	private static Configuration cfg;
	
	public static Configuration getCfg(String path) throws IOException{
		if(cfg == null){
		 cfg = new Configuration();
		  //cfg.setOutputEncoding("utf-8");
			cfg.setDirectoryForTemplateLoading(new File(path));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			return cfg;
		}else{
			return cfg;
		}
	}
}
