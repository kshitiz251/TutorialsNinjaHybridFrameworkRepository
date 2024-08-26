package com.tutorialsninja.qa.base;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.tutorialsninja.qa.utils.Utilities;

public class Base extends Utilities {
	
	WebDriver driver;
	
 
public WebDriver initilizeBrowserAndOpenApplication(String browser, String os) throws MalformedURLException {
		
		if(loadProperties("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}else {
				System.out.println("No matching os found");
			}
			
			switch(browser.toLowerCase()) {
			
			case "chrome":
				capabilities.setBrowserName("chrome");
				ChromeOptions option1 = new ChromeOptions();
				option1.addArguments("--incognito");
				capabilities.setCapability(ChromeOptions.CAPABILITY, option1);
				break;
				
			case "firefox":
				capabilities.setBrowserName("firefox");
				FirefoxOptions option2 = new FirefoxOptions();
				option2.addArguments("--incognito");
				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, option2);
				break;
				
			case "edge":
				capabilities.setBrowserName("edge");
				EdgeOptions option3 = new EdgeOptions();
				option3.addArguments("--incognito");
				capabilities.setCapability(EdgeOptions.CAPABILITY, option3);
				break;
				
			case "safari":
				capabilities.setBrowserName("safari");
				break;
			
			default: 
				System.out.println("No matching browser");
			}
			
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
			
		}
		
		if(loadProperties("execution_env").equalsIgnoreCase("local")) {
			
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
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_WAIT_TIME));
		driver.get(loadProperties("url"));
		
		return driver;
	}

}


