package com.tutorialsninja.qa.testcases;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.dataprovider.LoginDataProvider;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;

 

public class LoginTest extends Base  {
	
	WebDriver driver;
	LoginPage loginPage;
	
	
	@BeforeMethod
	@Parameters({"browser", "os"})
	public void setUp(@Optional("edge") String browser,String os, ITestContext context) {
		 
		try {
			driver = initilizeBrowserAndOpenApplication(browser, os);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setAttribute("webdriver", driver);
		HomePage homepage = new HomePage(driver);
		loginPage = homepage.navigateToLoginPage();
		
	}
	
	@AfterMethod
	public void teardown() {
		 if (driver != null) {
	            driver.quit();
	     }
		
	}
	

	@Test(priority = 1, dataProvider = "supplyDataChain" ,dataProviderClass = LoginDataProvider.class ,groups = {"smoke"})
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption()," Warning Message");
		
	
	}

	@Test(priority = 2, groups = {"smoke"})
	public void verifyLoginWithInvalidCredentials() {

		loginPage.login(generateEmailTimeStamp()+"@gmail.com", "9876543");
		
		Assert.assertTrue(loginPage.retrieveEmailPAsswordNotMatchingWarningMessageText(),"Warning Message");
	}

	@Test(priority = 3,dataProvider = "supplyDataChain",dataProviderClass = LoginDataProvider.class)
	public void verifyLoginWithInvalidEmailAndValidPassword(String name, String password) {

		
		loginPage.login(generateEmailTimeStamp()+"@gmail.com", password);
		
		Assert.assertTrue(loginPage.retrieveEmailPAsswordNotMatchingWarningMessageText(),"Warning Message");
	
		
	}

	@Test(priority = 4 )
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		loginPage.login("kshitizgupta.edu@yopmail.com", "987654");
		
		Assert.assertTrue(loginPage.retrieveEmailPAsswordNotMatchingWarningMessageText(),"Warning Message");


	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		
		loginPage.login("", "");
		
		Assert.assertTrue(loginPage.retrieveEmailPAsswordNotMatchingWarningMessageText(),"Warning Message");
	
	}
	
	
	
}
