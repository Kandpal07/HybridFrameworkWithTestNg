package com.org.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;
import com.org.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
		
	public Base()  {
		prop = new Properties();
		dataProp = new Properties();
		// Define the file path components
		String dataPropFileLocation = System.getProperty("user.dir") + File.separator  +"src"+ File.separator  +"main"+ File.separator  +"java"+ File.separator  +"com"+ File.separator  +"org"+ File.separator  +"qa"+File.separator  +"testdata"+ File.separator +"testdata.properties";
		String configFileLocation =   System.getProperty("user.dir") + File.separator  +"src"+ File.separator  +"main"+ File.separator  +"java"+ File.separator  +"com"+ File.separator  +"org"+ File.separator  +"qa"+ File.separator  +"config"+File.separator  +"config.properties";
		// Construct the full path
		Path dataPropFilePath = Paths.get(dataPropFileLocation);
		Path configFilePath = Paths.get(configFileLocation);
		// Get the canonical path
		Path canonicalPathForDataPropFile = null;
		try {
			canonicalPathForDataPropFile = dataPropFilePath.toRealPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Path canonicalPathForConfigFilePath = null;
		try {
			canonicalPathForConfigFilePath = configFilePath.toRealPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			FileInputStream dataFis = new FileInputStream(canonicalPathForDataPropFile.toFile());
			dataProp.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(canonicalPathForConfigFilePath.toFile());
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
			
		if(browserName.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")) {
			
			driver = new SafariDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
