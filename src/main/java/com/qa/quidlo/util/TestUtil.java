package com.qa.quidlo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.quidlo.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=20;
	public static String excelSheetPath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\quidlo\\testdata\\Testdata_quidlo.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	static String parentWindow;
	
	//get data from Testdata excel file
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(excelSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
	
	//Take screenshot
	public static void takeScreenshotAtEndOfTest() throws IOException {
		log.info("Taking screenshot");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	

	public static void switchToChildWindow(int num) {
		int count=0;
		log.info("Switching to Child window");
		parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
			for(String windowHandle  : handles)
				{
						driver.switchTo().window(windowHandle);
						count++;
						if(count==num)
							break;
				}
	       
	}
}
