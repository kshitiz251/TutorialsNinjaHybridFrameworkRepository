package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 20;
	public static final int PAGE_WAIT_TIME = 5;

	public String generateEmailTimeStamp() {

		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	public String loadProperties(String key) {

		Properties properties = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resourcess\\config.properties");
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties.getProperty(key);

	}

	public Object[][] getTextDataFromExcel(String sheetName) {

		Object[][] data = null;

		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resourcess\\TutorialTestNinjaData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rows = sheet.getLastRowNum();
			int columns = sheet.getRow(0).getLastCellNum();

			data = new Object[rows][columns];

			for (int i = 0; i < rows; i++) {
				XSSFRow row = sheet.getRow(i + 1);

				for (int j = 0; j < columns; j++) {
					XSSFCell cell = row.getCell(j);
					if (cell == null) {
						data[i][j] = "";
						continue;
					}
					CellType cellType = cell.getCellType();

					switch (cellType) {
					case STRING:
						data[i][j] = cell.getStringCellValue();
						break;
					case NUMERIC:
						data[i][j] = Integer.toString((int) cell.getNumericCellValue());
						break;
					case BOOLEAN:
						data[i][j] = cell.getBooleanCellValue();
						break;
					default:
						data[i][j] = "";
						break;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String captureScreenshoot(WebDriver driver, String testName) {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+ "\\ScreenShot\\"+ testName+".png";
		
		try {
			FileHandler.copy(source, new File(destinationScreenshotPath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return destinationScreenshotPath;
		
	}
}
