package com.FreeCRM.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FreeCRM.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[@class='input-group-btn']/input[@value='Login']")
	WebElement loginBtn;

	@FindBy(xpath="//a[text()='Sign Up']")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement CRMlogo;
	
	@FindBy(xpath="//a[@target='_blank']/font[text()='Selenium Jobs']")
	WebElement SeleniumJobsLink;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}

	public String ValidateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMLogo()
	{
		return CRMlogo.isDisplayed();
	}
	
	public HomePage login(String uname, String pswrd)
	{
	username.sendKeys(uname);
	password.sendKeys(pswrd);
	loginBtn.click();
	return new HomePage();
	}
	
	//after clicking on login button its returning to homepage so login should return HomePage, 
	//that's y we've mentioned return as new HomePage() to return homepage object
	
	
}
