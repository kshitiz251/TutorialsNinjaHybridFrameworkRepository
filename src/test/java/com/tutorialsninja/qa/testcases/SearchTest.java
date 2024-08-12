package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;



	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(@Optional("edge") String browser, ITestContext context) {

		driver = initilizeBrowserAndOpenApplication(browser);
		context.setAttribute("webdriver", driver);
		homePage = new HomePage(driver);

	}

	@AfterMethod
	public void teardown() {
		 if (driver != null) {
	            driver.quit();
	     }
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		searchPage = homePage.SearchProductIntoSearchBoxFeild("mac");

		Assert.assertTrue(searchPage.displayStatusOfProduct());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		searchPage = homePage.SearchProductIntoSearchBoxFeild("Honda");

		Assert.assertEquals(searchPage.retreiveNoProductMessageText(),
				"There is no product that matches the search criteria.");
	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.SearchProductIntoSearchBoxFeild("Honda");

		Assert.assertEquals(searchPage.retreiveNoProductMessageText(),
				"There is no product that matches the search criteria.");
	}

}
