package com.FreeCRM.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FreeCRM.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//form[@id='vContactsForm']//td[@class='datacardtitle'][contains(text(),'Contacts')]")
	WebElement contactLabel;
	
	@FindBy(xpath="//form[@id='vContactsForm']/table/tbody/tr[2]/td[1]/div/a")
	List<WebElement> pageCount;//to get the size of pages
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactLabel()
	{
		return contactLabel.isDisplayed();
	}

	public void pageCountInContactForm()
	{
		int pagecountSize = pageCount.size();
		for(int i=0; i<=pagecountSize-1;i++)
		{
			System.out.println(pageCount.get(i));
		}
	}
	
	public void searchContactByName(String name) throws InterruptedException
	{
		driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']/input[@name='contact_id']")).click();
	}
	
	public void getContactList()
	{
		String beforeXpath = "//form[@id='vContactsForm']//table[@class='datacard']/tbody/tr[";
		String afterXpath  = "]/td[2]";
		List<WebElement> rowData = driver.findElements(By.xpath("//form[@id='vContactsForm']//table[@class='datacard']/tbody/tr"));
		int rowCount = rowData.size();
		for(int i=2;i<=rowCount-1;i++)
		{
			String actualXpath = beforeXpath+i+afterXpath;
			String scndColData = driver.findElement(By.xpath(actualXpath)).getText();
			System.out.println(scndColData);
		}
	}
}
