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
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;


public class RegisterTest extends Base {
	
	WebDriver driver;
	RegisterPage registerPage; 
	AccountSuccessPage accountSuccessPage;
	

	
	@BeforeMethod
	@Parameters({"browser", "os"})
	public void setUp(@Optional("edge") String browser, String os, ITestContext context) {
		
		try {
			driver = initilizeBrowserAndOpenApplication(browser, os);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setAttribute("webdriver", driver);
		HomePage homepage = new HomePage(driver);
		registerPage = homepage.navigateToRegisterPage();	
	}
	
	@AfterMethod
	public void teardown() {
		
		 if (driver != null) {
	            driver.quit();
	     }
	}
	

	@Test(priority = 1, groups = {"smoke"})
	public void VerifyRegisteringAndAccountWithMandatoryFields() {

		accountSuccessPage = registerPage.registerWithMandatoryFields("kshitiz","gupta","kshitiz" + generateEmailTimeStamp() + "@gmail.com","7599020508","987654321");
		
		Assert.assertTrue(accountSuccessPage.retreiveAccountSuccessPageHeading().equals("Your Account Has Been Created!"));
		
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFeilds() {
		
		accountSuccessPage = registerPage.registerWithAllFields("kshitiz","gupta","kshitiz" + generateEmailTimeStamp() + "@gmail.com","7599020508","987654321");
		 
		Assert.assertTrue(accountSuccessPage.retreiveAccountSuccessPageHeading().equals("Your Account Has Been Created!"));
		

	} 

	@Test(priority =3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		registerPage.registerWithAllFields("kshitiz","Gupta","kshitizgupta.edu@gmail.com","7599020508","987654321");
		
		Assert.assertEquals(registerPage.retreiveDuplicateEmailAddressWarning(),"Warning: E-Mail Address is already registered!");
		
		
	}
	
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages("Warning: You must agree to the Privacy Policy!", "First Name must be between 1 and 32 characters!", "Last Name must be between 1 and 32 characters!","E-Mail Address does not appear to be valid!", "Telephone must be between 3 and 32 characters!","Password must be between 4 and 20 characters!"));
	
		
	}
}
