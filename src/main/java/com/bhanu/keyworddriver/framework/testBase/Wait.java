package com.bhanu.keyworddriver.framework.testBase;

public class Wait extends TestBase{
	

	private static String explicitWait;
	
	private static String implicitWait;
	
	private static String pageloadTime;
	
	
	public static String getExplicitWait() {
		return Repository.getProperty("explicitWait");
	}

	public static String getImplicitWait() {
		return Repository.getProperty("implicitWait");
	}

	public static String getPageloadTime() {
		return Repository.getProperty("pageloadTime");
	}


}
