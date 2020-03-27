package com.FreeCRM.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

import com.FreeCRM.qa.Util.Utils;
import com.FreeCRM.qa.Util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver; 
	public static WebEventListener eventListener;
	public ExtentReports extent;
	public ExtentTest extentTest;

	public static final Logger log = Logger.getLogger(TestBase.class);
	
	public TestBase()
	{
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/FreeCRM/qa/config/config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mehak\\Desktop\\selenium\\Jar Files\\chromedriver 80\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mehak\\Desktop\\selenium\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			driver = new HtmlUnitDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		//Now create an object for EvenListenerHandler to register with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Utils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Utils.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));		
	}
	
	public static String takeScreenshot(String testMethodName) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenShotPath = "./Screenshots/"+testMethodName+".png";
			File destination  = new File(screenShotPath);
			FileHandler.copy(src, destination);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Screenshot folder Not found");
		}
		return testMethodName;
	}
	
    @BeforeTest
	
	public void setExtent()
	{
		
		extent = new ExtentReports("./ExtentReport/" + "ExtentReportsTestNG.html",true);
		extent.addSystemInfo("Hostname","Desktop-N9IAUI1");
		extent.addSystemInfo("Username", "Mehak_Kapoor");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	
	public void setEndReport()
	{

	extent.flush();
	extent.close();
	}
	
	
}
