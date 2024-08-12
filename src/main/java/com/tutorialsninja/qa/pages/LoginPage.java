package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement emailAddressFeild;
	
	@FindBy(xpath = "//input[@id='input-password']")
	private WebElement passwordFeild;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[text()='Warning: No match for E-Mail Address and/or Password.']")
	private WebElement emailPasswordNotMachingWarning;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	public AccountPage login(String emailText,String passwordText) {
		emailAddressFeild.sendKeys(emailText);
		passwordFeild.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public boolean retrieveEmailPAsswordNotMatchingWarningMessageText() {
		return emailPasswordNotMachingWarning.isDisplayed();
	}
}
