package com.FreeCRM.qa.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.FreeCRM.qa.Util.Utils;
import com.FreeCRM.qa.base.TestBase;
import com.FreeCRM.qa.pages.ContactsPage;
import com.FreeCRM.qa.pages.HomePage;
import com.FreeCRM.qa.pages.LoginPage;
import com.FreeCRM.qa.pages.TaskPage;
import com.relevantcodes.extentreports.LogStatus;

public class TasksPageTestCase extends TestBase{

	HomePage homepage;
	LoginPage lpage;
	ContactsPage contactPage;
	Utils utilpage;
	TaskPage taskpage;
	
	public TasksPageTestCase()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		initialization();
		lpage = new LoginPage();
		homepage = new HomePage();
		contactPage = new ContactsPage();
		utilpage = new Utils();
		taskpage = new TaskPage();
		homepage = lpage.login(prop.getProperty("username"), prop.getProperty("password"));
		utilpage.switchToFrame();
		//taskpage = homepage.ClickOnTasksLink();
	}
	
	  @Test
	  public void verifyTaskLabelTest() throws InterruptedException 
	  { 
		  extentTest = extent.startTest("verifyTaskLabelTest");
		  homepage.ClickOnNewTaskLink();
	  Assert.assertTrue(taskpage.verifyTaskLabel(), "Task Label didn't matched"); 
	  }
	 
	 
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = utilpage.getTestData("TaskData");
		return data;
	}

	@Test(dataProvider="getData")
	public void createNewTaskTest(String title, String autoExtendValue, String completionPercent,
			String type, String priority, String dealName) throws InterruptedException
	{
		extentTest = extent.startTest("createNewTaskTest");
		homepage.ClickOnNewTaskLink();
		taskpage.createNewTask(title, autoExtendValue, completionPercent, type, priority, dealName);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
	  if(result.getStatus()==ITestResult.FAILURE)
	  {
		  extentTest.log(LogStatus.FAIL,"TestCase Failed Is"+ result.getName());
		  extentTest.log(LogStatus.FAIL, "TestCase Failed Is"+ result.getThrowable());
		  String screenshotDestination = TestBase.takeScreenshot(result.getMethod().getMethodName());
		  extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotDestination));
	  }
	  else if(result.getStatus()==ITestResult.SKIP)
	  {
		  extentTest.log(LogStatus.SKIP, "TestCase skipped Is"+result.getName());
	  }
	  else if(result.getStatus()==ITestResult.SUCCESS)
	  {
		  extentTest.log(LogStatus.PASS, "Test Case Passes Is"+ result.getName());
	  }
	extent.endTest(extentTest);
	driver.quit();
	}
}
