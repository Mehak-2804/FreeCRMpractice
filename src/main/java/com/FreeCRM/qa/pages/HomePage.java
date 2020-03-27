package com.FreeCRM.qa.pages;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.FreeCRM.qa.base.TestBase;

public class HomePage extends TestBase{

	
	@FindBy(xpath = "//td[contains(text(),'User: Demo User')]")
	//@CacheLookup
	WebElement userLabel;
	
	@FindBy(xpath = "//div[@id=\"leftMenuContainer\"]/div/ul/li")
	List<WebElement> leftPannelicons;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath = "//a[contains(text(),'Docs')]")
	WebElement docsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	WebElement reportsLink;
	
	@FindBy(xpath="//a[@title='New Task']")
	WebElement newTaskLinkInTaskPage;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}

	public String verfyHomePagetitle()
	{
		return driver.getTitle();
	}
	
	public String verifyCorrectUserName()
	{
		/* return userLabel.isDisplayed(); */
		
		  String userLabeltext = userLabel.getText();
		  return userLabeltext;
	}
	
	public void leftPannenIconDetail()
	{
		System.out.println("size of leftpannelIcon is "+ leftPannelicons.size());
		int leftpannerSize = leftPannelicons.size();
		 for(int i=0;i<=leftpannerSize-1;i++) 
		  {
			  System.out.println(leftPannelicons.get(i).getText());
		  } 
	}
	
	public ContactsPage ClickOnContactsLink()
	{
		contactsLink.click();	
		return new ContactsPage();
	}
	
	public DealsPage ClickOnDealsLink()
	{
		dealsLink.click();
		return new DealsPage();		
	}
	
	public TaskPage ClickOnTasksLink()
	{
		tasksLink.click();
		return new TaskPage();
	}
	
	public DocsPage ClickOnDocsPage()
	{
		docsLink.click();
		return new DocsPage();
	}
	
	public ReportsPage ClickOnReportsPage()
	{
		reportsLink.click();
		return new ReportsPage();
	}

	public void ClickOnNewTaskLink() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(tasksLink));
		Actions action = new Actions(driver); 
		action.moveToElement(tasksLink).build().perform();
		newTaskLinkInTaskPage.click();
	}
}

