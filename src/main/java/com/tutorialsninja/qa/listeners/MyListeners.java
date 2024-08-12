package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentText;
	
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		extentText = extentReport.createTest(result.getName());
		extentText.log(Status.INFO, result.getName()+ "started executing" );
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentText.log(Status.PASS, result.getName() + " got Successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestContext().getAttribute("webdriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		extentText.addScreenCaptureFromPath(Utilities.captureScreenshoot(driver, result.getName()));
		extentText.log(Status.INFO, result.getThrowable());
		extentText.log(Status.FAIL, " got failed");
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		extentText.log(Status.INFO, result.getThrowable());
		extentText.log(Status.SKIP, result.getName()+ " got skipped");
	
	}

	@Override
	public void onFinish(ITestContext context) {
		 if (extentReport != null) {
	            extentReport.flush();
	     }
		 
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		 File extentReportFile = new File(pathOfExtentReport);
		 try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
	

}
