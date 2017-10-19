package com.haisenberg.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {
    public static final Logger LOG = Logger.getLogger(PropertiesUtil.class);
    
    

	public static Properties getProperty(){
		
		Properties pro = new Properties();
		try {
			pro=PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
		} catch (IOException e) {
			LOG.error("未找到jdbc.properties配置文件！！！");
		}
		return pro;
	}
	
	public static String getProperty(String str){
		
		Properties pro = new Properties();
		try {
			pro=PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
		} catch (IOException e) {
			LOG.error("未找到jdbc.properties配置文件！！！");
		}
		return pro.getProperty(str);
	}
	public static void main(String[] args){
		Properties property = PropertiesUtil.getProperty();
		String jdbc_driver = property.getProperty("jdbc_driver");
		System.out.println(jdbc_driver);		
	}
}
