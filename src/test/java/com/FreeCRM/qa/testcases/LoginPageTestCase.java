package com.FreeCRM.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.FreeCRM.qa.base.TestBase;
import com.FreeCRM.qa.pages.HomePage;
import com.FreeCRM.qa.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;


public class LoginPageTestCase extends TestBase {
	
	LoginPage lpage;
	HomePage homepage; // creating an object of homepage class so that login method can return the homepage
	
	
	public LoginPageTestCase() //we will create constructor to call the properties constructor in TestBase class
	{
		super(); // we will use super keyword to call the constructor of super class
	}
	
	@BeforeMethod
	public void setUp()
	{
		TestBase.log.debug("Initializing the browser and launching the URL");
		initialization();
		lpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void LoginTitleTest()
	{	
		TestBase.log.info("Validating title of LoginPage");
		extentTest = extent.startTest("LoginTitleTest");
		String loginPagetitle = lpage.ValidateLoginPageTitle();
		Assert.assertEquals(loginPagetitle, "CRMPRO - CRM software for customer relationship management, sales, and support.", "Login Page title not found");
		if(loginPagetitle!=driver.getTitle())
		{
			TestBase.log.error("Title didn't matched");
		}
	}
	
	@Test(priority=2)
	public void crmLogoImageTest()
	{
		extentTest = extent.startTest("crmLogoImageTest");
		boolean flag = lpage.validateCRMLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest() throws InterruptedException
	{
		extentTest = extent.startTest("loginTest");
		Thread.sleep(2000l);
		homepage = lpage.login(prop.getProperty("username"), prop.getProperty("password")); //after login we will jump into homepage
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
	  if(result.getStatus()==ITestResult.FAILURE)
		  {
			  extentTest.log(LogStatus.FAIL,"Test Case Failed Is"+result.getName()); 
			  extentTest.log(LogStatus.FAIL,"Test Case Failed Is"+result.getThrowable()); 
			  String screenshotDestination = TestBase.takeScreenshot(result.getMethod().getMethodName());
			  extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotDestination));  
		  }
	  else if(result.getStatus()==ITestResult.SKIP)
	  		{
		  extentTest.log(LogStatus.SKIP,"Test Case Skipped Is"+result.getName()); 
	  		}
	  else if(result.getStatus()==ITestResult.SUCCESS)
	  {
		  extentTest.log(LogStatus.PASS, "Test Case Passes Is"+ result.getName());
	  }
	  extent.endTest(extentTest);
	  driver.quit();
	}
}
