package com.crm.qea.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qea.base.TestBase;
import com.crm.qea.pages.ContactsPage;
import com.crm.qea.pages.CreateNewContactPage;
import com.crm.qea.pages.HomePage;
import com.crm.qea.pages.LoginPage;
import com.crm.qea.pages.WelcomePage;
import com.crm.qea.utils.TestUtils;

public class CreateNewContactPageTest extends TestBase {

	public CreateNewContactPageTest() {
		super();
	}

	WelcomePage wPage;
	LoginPage lPage;
	HomePage hPage;
	ContactsPage cPage;
	CreateNewContactPage cnPage;
	String sheetName = "Sheet2";
	String title="Create New Contact";

	@BeforeMethod
	public void setup() {
		DriverSetup();
		OpenUrl();
		wPage = new WelcomePage();
		lPage = wPage.redirectToLoginPage();
		hPage = lPage.doLogin(prop.getProperty("userid"), prop.getProperty("password"));
		cPage = hPage.returnTitlePageObject().redirectToContanctsPage();
		cnPage=cPage.addContact();
		driver.navigate().refresh();
		
	}

	@Test(priority=1)
	public void validateCreateNewContactPageTitleTest() {
		
		cLogger = logger.createNode("Validate Create New Contact Page Title");
		TestUtils.validateTitle(title, lPage.validateLoginPageTitle(),"Valid Title","Invalid Title");
	}
	
	@DataProvider
	public Object[][] FreeCrmContactData() {
		
		return TestUtils.testData(sheetName);
	}
	
	@Test(priority=2,dataProvider = "FreeCrmContactData")
	public void enterUserDetails(String fname, String lname, String company, String position,String test) {
		cLogger = logger.createNode(test);
		cnPage.enterDetails(fname, lname,company,position);
		cnPage.saveContact();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
