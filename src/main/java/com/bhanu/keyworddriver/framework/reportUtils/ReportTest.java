package com.bhanu.keyworddriver.framework.reportUtils;

import com.bhanu.keyworddriver.framework.testBase.TestBase;

public class ReportTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String startTime = TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa");
		
		ReportUtil.startTesting("index", startTime, "Test", "1.5");

		ReportUtil.startSuite("Suite1");

		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Fail", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("Navigate to page", "Navigate", "Pass", null);
        Thread.sleep(60000);
		ReportUtil.addTestCase("TopNavigation", startTime, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Pass");

		// For Second Scenarios
		ReportUtil.addKeyword("LoginTo Page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("LoginTo Page", "Navigate", "Pass", null);

		ReportUtil.addKeyword("LoginTo Page", "Navigate", "Pass", null);
		ReportUtil.addKeyword("LoginTo Page", "Navigate", "Pass", null);
		
		ReportUtil.addTestCase("Login", startTime, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Pass");

		
		ReportUtil.endSuite();
		ReportUtil.updateEndTime(TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"));

	}
}
