package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement firstNameFeild;
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastNameFeild;
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailAddressFeild;
	
	@FindBy(xpath = "//input[@name='telephone']")
	private WebElement telephoneFeild;
	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordFeild;
	
	@FindBy(xpath = "//input[@name='confirm']")
	private WebElement passwordConfirmFeild;
	
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement privacyPolicyFeild;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	 
	@FindBy(xpath = "(//input[@name='newsletter'])[1]")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement LastNameWarning;
	
	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement passswordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	
	public String retreiveDuplicateEmailAddressWarning() {
		return duplicateEmailAddressWarning.getText();
	}
	
//	public String retreivePrivacyPolicyWarning() {
//		return privacyPolicyWarning.getText();
//	}
	
//	public String retreiveFirstNameWarning() {
//		return firstNameWarning.getText();
//	}
	
//	public String retreiveLastNameWarning() {
//		return LastNameWarning.getText();
//	}
	
//	public String retreiveEmailWarning() {
//		return emailWarning.getText();
//	}
	
//	public String retreiveTelephoneWarning() {
//		return telephoneWarning.getText();
//	}
	
	
	public String retreivePasswordWarnign() {
		return passswordWarning.getText();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText, String emailText,String telephoneText, String passwordText  ) {
		firstNameFeild.sendKeys(firstNameText);
		lastNameFeild.sendKeys(lastNameText);
		emailAddressFeild.sendKeys(emailText);
		telephoneFeild.sendKeys(telephoneText);
		passwordFeild.sendKeys(passwordText);
		passwordConfirmFeild.sendKeys(passwordText);
		privacyPolicyFeild.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText, String emailText,String telephoneText, String passwordText  ) {
		firstNameFeild.sendKeys(firstNameText);
		lastNameFeild.sendKeys(lastNameText);
		emailAddressFeild.sendKeys(emailText);
		telephoneFeild.sendKeys(telephoneText);
		passwordFeild.sendKeys(passwordText);
		passwordConfirmFeild.sendKeys(passwordText);
		yesNewsLetterOption.click();
		privacyPolicyFeild.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastNameWarning, String expectedEmailWarnig, String expectedTelephoneWarnig, String expectedPasswordWarning  ) {
		boolean actualPrivacyPolicyWarning= privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
		boolean actulFirstNameWarning = firstNameWarning.getText().equals(expectedFirstNameWarning);
		boolean actualLastNameWarning = LastNameWarning.getText().equals(expectedLastNameWarning);
		boolean actualEmailWarning = emailWarning.getText().equals(expectedEmailWarnig);
		boolean actualTelephoneWarnig = telephoneWarning.getText().equals(expectedTelephoneWarnig);
		boolean actualPasswordWarning = passswordWarning.getText().equals(expectedPasswordWarning);
		return actualPrivacyPolicyWarning && actulFirstNameWarning && actualLastNameWarning && actualEmailWarning && actualTelephoneWarnig && actualPasswordWarning;
	}
}

