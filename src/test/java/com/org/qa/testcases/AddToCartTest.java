package com.org.qa.testcases;

import com.org.qa.base.Base;
import com.org.qa.pages.HomePage;
import com.org.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCartTest extends Base {
	public AddToCartTest() {
		super();
	}
	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		LoginPage loginPage=new LoginPage(driver);
		loginPage.logInToApplication();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyUserCanAddItemToTheCart() {
		new HomePage(driver).selectSauceLabsBackpack();
	}

//	@Test(priority=2)
//	public void verifyUserCanRemoveItemFromTheCart() {
//	}
}
