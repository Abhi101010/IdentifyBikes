package com.opencart.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.openkart.utils.ExtentReportManager;
import com.openkart.utils.Utils;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public ExtentReports report = ExtentReportManager.generateReport() ;
	public static ExtentTest logger;
	
	
	//**********************************Constructor************************************
	public Base(){
		prop = new Properties();
		FileInputStream file=null;
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/opencart/config/Config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//***************************************Driver********************************************
	public void driverSetup() {
		
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Utils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Utils.IMPLICITLY_WAIT, TimeUnit.SECONDS);
	}

	//**********************************OpenUrl*********************************************
	public void openUrl() {
		logger.log(Status.INFO, "Opening URL");
		driver.get(prop.getProperty("url"));
		logger.log(Status.PASS, "Successfully opened "+prop.getProperty("url"));
	}
	
	//************************************CreateTest*******************************************
	public void createTest(String test) {
		logger = report.createTest( test);
	}
}
