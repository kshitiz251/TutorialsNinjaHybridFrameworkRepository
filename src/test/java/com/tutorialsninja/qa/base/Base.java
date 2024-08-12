package com.tutorialsninja.qa.base;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.tutorialsninja.qa.utils.Utilities;

public class Base extends Utilities {
	
	WebDriver driver;
	
 
	public WebDriver initilizeBrowserAndOpenApplication(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("firefox")){
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--incognito");
			driver = new FirefoxDriver(options);
		}else if(browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--incognito");
			driver = new EdgeDriver(options);
		}else if(browser.equalsIgnoreCase("safari")) {
			SafariOptions options = new SafariOptions();
			driver = new SafariDriver(options);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_WAIT_TIME));
		driver.get(loadProperties("url"));
		
		return driver;
	}

}
