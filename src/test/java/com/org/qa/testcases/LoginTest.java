package com.org.qa.testcases;

import com.org.qa.base.Base;
import com.org.qa.pages.HomePage;
import com.org.qa.pages.LoginPage;
import com.org.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Base{
	public LoginTest() {
		super();
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
//		loginPage = homePage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider()
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
     //h3[@data-test='error']
	}

}
