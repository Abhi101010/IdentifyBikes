package com.crm.qea.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.crm.qea.base.TestBase;
import com.crm.qea.pages.LoginPage;
import com.crm.qea.pages.WelcomePage;
import com.crm.qea.utils.TestUtils;

public class WelcomePageTest extends TestBase {

	WelcomePage wPage;
	LoginPage lPage;
	String title = "#1 Free CRM customer relationship management software cloud";

	public WelcomePageTest() {
		super();
	}

	@BeforeTest
	public void createTest() {
		logger = report.createTest("Welcome Page Test");
	}

	@BeforeMethod
	public void setup() {
		DriverSetup();
		OpenUrl();
		wPage = new WelcomePage();
	}

	@Test(priority = 2)
	public void welcomePageTitleTest() {
		cLogger = logger.createNode("Welcome Page Title Test");
		TestUtils.validateTitle(title, wPage.validateLoginPageTitle(), "Valid Title", "Invalid Title");
	}

	@Test(priority = 1)
	public void welcomePageLogoTest() {
		cLogger = logger.createNode("Welcome Page Logo Test");
		boolean flag = wPage.validateLoginPageLogo();
		if (flag == true) {
			cLogger.log(Status.PASS, "Valid Logo");
		} else {
			cLogger.log(Status.FAIL,"InValid Logo");
			try {
				TestUtils.testfail("Invalid Logo", System.currentTimeMillis()+".jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Test(priority = 3)
	public void redirectToLoginPageTest() {
		cLogger = logger.createNode("Redirect To Login Page Test");
		lPage = wPage.redirectToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@AfterSuite
	public void flushReport() {
		report.flush();
	}
}
