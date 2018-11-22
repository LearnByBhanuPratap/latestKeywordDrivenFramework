
package com.bhanu.keyworddriver.framework.testBase;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("rawtypes")
public class LoggerHelper {
	
	private static boolean root = false;
	
	public static Logger getLogger(Class clas) {
		if(root){
			return Logger.getLogger(clas);
		}
		
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/java/propertiesFiles/log4j.properties");
		root = true;
		return Logger.getLogger(clas);
	}

}
