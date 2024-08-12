package com.tutorialsninja.qa.dataprovider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.tutorialsninja.qa.utils.Utilities;

public class LoginDataProvider extends Utilities {

	@DataProvider(name = "supplyDataChain" , indices = {0,2})
	public Object[][] supplyData(Method method) {

		switch (method.getName()) {

		case "verifyLoginWithValidCredentials":
			return getTextDataFromExcel("Login");

		case "verifyLoginWithInvalidEmailAndValidPassword":
			return getTextDataFromExcel("Login");

		}

		return null;
	}

}
