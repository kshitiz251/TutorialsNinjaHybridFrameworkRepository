package com.tutorialsninja.qa.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	// private static ExtentReports extentReport;
	private static Map<String, ExtentReports> extentMap = new HashMap<>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static synchronized ExtentReports generateExtentReport(String XMLtestName, String browser) {

		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport_" + XMLtestName + ".html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		Utilities utilities = new Utilities();

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Result");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm::ss");

		extentReport.attachReporter(sparkReporter);

		extentReport.setSystemInfo("Application URL", utilities.loadProperties("url"));
		extentReport.setSystemInfo("XML Test Name", XMLtestName);
		extentReport.setSystemInfo("Browser Name", browser);
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		extentMap.put(XMLtestName, extentReport);
		return extentReport;
	}

	public static ExtentReports getInstance(String XMLtestName) {
		return extentMap.get(XMLtestName);
	}

	public static ExtentTest getTest() {

		return test.get();
	}

	public static void setTest(ExtentTest extestTest) {

		test.set(extestTest);
	}

}
