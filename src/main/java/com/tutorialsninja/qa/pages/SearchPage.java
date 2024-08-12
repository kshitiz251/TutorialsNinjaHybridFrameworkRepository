package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(xpath = "//a[text()='MacBook']")
	private WebElement validProduct;
	
	@FindBy(xpath = "//p[text()='There is no product that matches the search criteria.']")
	private WebElement noProductMesage;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfProduct() {
		return validProduct.isDisplayed();
	}
	
	public String retreiveNoProductMessageText() {
		return noProductMesage.getText();
	}
}
