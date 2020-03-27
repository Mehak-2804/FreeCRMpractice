package com.FreeCRM.qa.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;

import com.FreeCRM.qa.base.TestBase;

public class Utils extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT =  10;
	public static JavascriptExecutor js;
	static Workbook book;
	static Sheet sheet;
	static String Test_Data_Sheet_Path = "C:\\Users\\Mehak\\Desktop\\selenium\\Eclipse Projects\\Practice-workspace\\FreeCRMpractice\\src\\main\\java\\com\\FreeCRM\\qa\\testData\\Task_FreeCRM.xlsx";
	
	public void switchToFrame()
	{
		driver.switchTo().frame("mainpanel");
	}
	
	public void scrollBy()
	{
		js = (JavascriptExecutor)driver; 
		js.executeScript("window.scrollBy(0,200)");
	}
	
	public void scrollByTillBottom()
	{
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(Test_Data_Sheet_Path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

}
