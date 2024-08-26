package com.tutorialsninja.qa.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

//	private static ExtentReports extentReport;
	
	
	
	@Override
	public void onStart(ITestContext context) {
		String XMLtestName = context.getCurrentXmlTest().getName();
		String browser = context.getCurrentXmlTest().getParameter("browser");
		ExtentReporter.generateExtentReport(XMLtestName, browser);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String XMLtestName = result.getTestContext().getCurrentXmlTest().getName();
		ExtentTest extentText = ExtentReporter.getInstance(XMLtestName).createTest(result.getName());
		ExtentReporter.setTest(extentText);
		ExtentReporter.getTest().log(Status.INFO, result.getName()+ "started executing" );	
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReporter.getTest().log(Status.PASS, result.getName() + " got Successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestContext().getAttribute("webdriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ExtentReporter.getTest().addScreenCaptureFromPath(Utilities.captureScreenshoot(driver, result.getName()+result.getTestContext().getCurrentXmlTest().getName()));
		ExtentReporter.getTest().log(Status.INFO, result.getThrowable());
		ExtentReporter.getTest().log(Status.FAIL, " got failed");
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReporter.getTest().log(Status.INFO, result.getThrowable());
		ExtentReporter.getTest().log(Status.SKIP, result.getName()+ " got skipped");
	
	}

	@Override
	public void onFinish(ITestContext context) {

		ExtentReporter.getInstance(context.getName()).flush();
//		 if (extentReport != null) {
//	            extentReport.flush();
//	     }
		 
//		 String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
//		 File extentReportFile = new File(pathOfExtentReport);
//		 try {
//			Desktop.getDesktop().browse(extentReportFile.toURI());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		 
	}
	
	

}
