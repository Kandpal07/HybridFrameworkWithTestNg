package com.org.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Reporter;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();
		Path dataPropFilePath=null;
		try {
			 dataPropFilePath = Paths.get(System.getProperty("user.dir") + File.separator +"test-output"+ File.separator +"ExtentReports"+ File.separator +"extentReport.html").toRealPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		File extentReportFile = new File(dataPropFilePath.toUri());
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Test Automation Result");
		sparkReporter.config().setDocumentTitle("AutomationReport");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		Properties configProp = new Properties();
		// for getting url,browser etc details loading the config.xml file
		try {
			dataPropFilePath = Paths.get(Paths.get(System.getProperty("user.dir") + File.separator +"src"+ File.separator +"main"+ File.separator +"java"+ File.separator +"com"+ File.separator +"org"+ File.separator +"qa"+ File.separator +"config"+ File.separator +"config.properties").toRealPath().toUri());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			FileInputStream fisConfigProp = new FileInputStream(dataPropFilePath.toFile());
			configProp.load(fisConfigProp);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name",configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email",configProp.getProperty("Email"));
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		return extentReport;

	}

}
