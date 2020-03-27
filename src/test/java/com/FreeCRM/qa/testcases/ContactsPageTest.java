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

public class ContactsPageTest extends TestBase {

	HomePage homepage;
	LoginPage lpage;
	ContactsPage contactPage;
	Utils utilpage;

	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		lpage = new LoginPage();
		homepage = new HomePage();
		contactPage = new ContactsPage();
		utilpage = new Utils();
		homepage = lpage.login(prop.getProperty("username"), prop.getProperty("password"));
		utilpage.switchToFrame();
		contactPage = homepage.ClickOnContactsLink();
	}
	
	  @Test (priority = 1)
	  public void verifyContactLabelTest() throws InterruptedException 
	  {
		  
		  extentTest = extent.startTest("verifyContactLabelTest");
	  Assert.assertTrue(contactPage.verifyContactLabel(),
	  "contact page label is not found"); 
	  Thread.sleep(2000l);
	  }
	  
	  @Test (priority = 2)
	  public void pageCountTest()
	  { 
		  extentTest = extent.startTest("pageCountTest");
		  contactPage.pageCountInContactForm(); 
	  }
	 
	  @Test (priority = 3)
	  public void searchByContactNameTest() throws InterruptedException 
	  {
		  extentTest = extent.startTest("searchByContactNameTest");
	  utilpage.scrollBy(); contactPage.searchContactByName("Betty Jones"); 
	  }
	 
	 @Test (priority = 4)
	 public void getContactListTest()
	 {
		 extentTest = extent.startTest("getContactListTest");
		 utilpage.scrollByTillBottom();
		 contactPage.getContactList();
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
