package com.bhanu.keyworddriver.framework.testController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bhanu.keyworddriver.framework.enums.Browsers;
import com.bhanu.keyworddriver.framework.keywords.Keywords;
import com.bhanu.keyworddriver.framework.reportUtils.ReportUtil;
import com.bhanu.keyworddriver.framework.testBase.TestBase;

public class TestController extends TestBase{

	@BeforeClass
	public void initBrowser() throws IOException {
		Initialize();
	}

	@Test
	public void TestCaseController() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		
		String startTime = TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa");
		
		ReportUtil.startTesting("index", startTime, "QA", "1706.5");
		
		ReportUtil.startSuite("Suite1");
		String TCStatus="Pass";
		
		// loop through the test cases
		for(int TC=2;TC<=SuiteData.getRowCount("TestCases");TC++) {
			
			// This will Read testCase ID from TestSuite e.g (TC01,TC02)
			String TestCaseID = SuiteData.getCellData("TestCases", "TCID", TC);
			
			// This will Read RunMode from TestSuite for testID
			String RunMode = SuiteData.getCellData("TestCases", "RunMode", TC);
			
			if(RunMode.equals("Y")) {
				String TSStatus="Pass";
				driver = TestBase.selectBrowser(Browsers.CHROME.name());
				
				// For testCase Id get the numbers of Test data rows from TestSuite1Data.xlsx
				int rows = TestStepData.getRowCount(TestCaseID);
				
				// This is required to handle the scenarios where there is no test data.
				// in that case we need to run the test case once
				if(rows<2){
					rows = 2;
				}

				// loop through test data
				for(int TD=2;TD<=rows;TD++ ) {
				if(driver == null){
					driver = TestBase.selectBrowser(Browsers.CHROME.name());
				}
					// loop through the test steps Form TestSuite1.xlsx for each Test ID
					
					for(int TS=2;TS<=SuiteData.getRowCount(TestCaseID);TS++) {
						
						keyword = SuiteData.getCellData(TestCaseID, "Keyword", TS);
						webElement = SuiteData.getCellData(TestCaseID, "WebElement", TS);
						ProceedOnFail = SuiteData.getCellData(TestCaseID, "ProceedOnFail", TS);
						TSID = SuiteData.getCellData(TestCaseID, "TSID", TS);
						Description = SuiteData.getCellData(TestCaseID, "Description", TS);
						
						TestDataField = SuiteData.getCellData(TestCaseID, "TestDataField", TS);
						
						if(TestDataField!= null && TestDataField!=""){
							TestData = TestStepData.getCellData(TestCaseID, TestDataField, TD);	
						}
						
							
						
						if(TestDataField.equals("email")){
							TestData = "test"+System.currentTimeMillis()+"@gmail.com";
						}
						
						
						Method method = Keywords.class.getMethod(keyword);	
						TSStatus = (String) method.invoke(method);
						
						if(TSStatus.contains("Failed")) {
							// take the screenshot
							String filename = TestCaseID+"["+(TD-1)+"]";
							String screenShot = TestBase.getScreenShot(filename);
							
							TCStatus = TSStatus;
							// report error
							ReportUtil.addKeyword(Description, keyword, TSStatus, screenShot);

							if(ProceedOnFail.equals("N")) {
								break;
							}
						} else {
							ReportUtil.addKeyword(Description, keyword, TSStatus, null);
						}
						
					}
					ReportUtil.addTestCase(TestCaseID, startTime, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), TCStatus);
					driver.quit();
				}
			}else {
				// skip the test case
				ReportUtil.addTestCase(TestCaseID, startTime, TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"), "Skipped");
				driver.quit();
			}
		}

		ReportUtil.endSuite();
		ReportUtil.updateEndTime(TestBase.now("dd.MMMM.yyyy hh.mm.ss aaa"));
		
	}
	
	
	@AfterClass
	public void quitBrowser() {
		System.out.println("In quitBrowser---------------------------");
		driver.quit();
	}
	
	
}
