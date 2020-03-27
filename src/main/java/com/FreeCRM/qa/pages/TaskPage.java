package com.FreeCRM.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.FreeCRM.qa.base.TestBase;

public class TaskPage extends TestBase {
	
	@FindBy(id="title")
	WebElement titleInTask;
	
	@FindBy(name="auto_extend")
	WebElement autoExtend_InTask;
	
	@FindBy(name="status")
	WebElement status_InTask;
	
	@FindBy(id="completion")
	WebElement completionPercentage_InTask;
	
	@FindBy(name="task_type")
	WebElement taskType_InTask;
	
	@FindBy(name="priority")
	WebElement priority_InTask;
	
	@FindBy(xpath="//*[@id='taskForm']/table/tbody/tr[2]/td/table/tbody/tr[9]/td[2]/input[@name='prospect_lookup']")
	WebElement deal_Lookup_InTask;
	
	@FindBy(xpath="//form[@id='taskForm']/table/tbody/tr[1]/td/input[@type='submit']")
	WebElement click_On_Save;
			
	@FindBy(xpath="//fieldset[@class='fieldset']//legend[text()='Task Information']")
	WebElement task_label;

	public TaskPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyTaskLabel()
	{
		return task_label.isDisplayed();
	}

	public void createNewTask(String title, String autoExtendValue, String completionPercent,
			String type, String priority, String dealName)
	{
		titleInTask.sendKeys(title);
		
		Select autoExtend = new Select(autoExtend_InTask);
		autoExtend.selectByVisibleText(autoExtendValue);
		/*
		 * Select status_task = new Select(status_InTask);
		 * status_task.selectByVisibleText(Status);
		 */	
		
		completionPercentage_InTask.sendKeys(completionPercent);
		deal_Lookup_InTask.sendKeys(dealName);
		
		Select typeOfTask = new Select(taskType_InTask);
		typeOfTask.selectByVisibleText(type);
		
		Select priority_task = new Select(priority_InTask);
		priority_task.selectByVisibleText(priority);
		click_On_Save.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
