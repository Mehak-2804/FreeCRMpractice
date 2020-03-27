package com.FreeCRM.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.FreeCRM.qa.Util.Utils;
import com.FreeCRM.qa.base.TestBase;
import com.FreeCRM.qa.pages.ContactsPage;
import com.FreeCRM.qa.pages.HomePage;
import com.FreeCRM.qa.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTestCase extends TestBase {

	HomePage homepage;
	LoginPage lpage;
	ContactsPage contactPage;
	Utils utilpage;

	// ctrl+shift+o -- to import necessary package

	public HomePageTestCase() {
		super();
	}

	@BeforeMethod
	public void setUp() 
	{
		initialization();
		utilpage = new Utils();
		lpage = new LoginPage();
		contactPage = new ContactsPage();
		homepage = lpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	  @Test(priority=1)
	  public void verifyHomePagetitleTest() throws InterruptedException 
	  { 
		  Thread.sleep(2000l);
		  extentTest = extent.startTest("verifyHomePagetitleTest");
		  String homePagetitle = homepage.verfyHomePagetitle(); Assert.assertEquals(homePagetitle, "CRMPRO",
	      "HomePage title not matched");
	  }
	  
	  @Test(priority=2)
	  public void userLabelCheckTest() throws InterruptedException 
	  {
		  extentTest = extent.startTest("userLabelCheckTest");
		  utilpage.switchToFrame(); // //
	 // Assert.assertTrue(homepage.verifyCorrectUserName(), "username not found");
		  String usernameText = homepage.verifyCorrectUserName();
		  System.out.println(usernameText); 
	  }
	 	
	  @Test(priority=3)
	  public void leftPanneliconsTest()
	  { 
		  extentTest = extent.startTest("leftPanneliconsTest");
		  utilpage.switchToFrame();
		  homepage.leftPannenIconDetail();
	  }
	 
	  @Test(priority=4)
	  public void verifyContactsLinkTest() 
	  { 
		  extentTest = extent.startTest("verifyContactsLinkTest");
		  utilpage.switchToFrame();
		  contactPage = homepage.ClickOnContactsLink(); 
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
