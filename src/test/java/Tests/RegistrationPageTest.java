package Tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.opencart.base.Base;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.RegistrationPage;
import com.openkart.utils.ManageExcelData;
import com.openkart.utils.Utils;

public class RegistrationPageTest extends Base {
	
	
	HomePage hPage;
	RegistrationPage rPage;
	
	

	public RegistrationPageTest()
	{
		super();
	}
	@BeforeMethod
	public void initiate() {
		driverSetup();
		hPage = new HomePage();
		rPage = new RegistrationPage();
	}
	
	@Test(priority=1)
	public void validateRegistrationPageTest() {
		createTest("Validate the BreadCrumb, Heading, Page Title, Url of the Register Account Page");
		openUrl();
		hPage.redirectToRegisterAccountPage();
		logger.log(Status.INFO, "Verifing Registration Page Title");
		Assert.assertEquals(Utils.getTite(), RegistrationPage.ExpTitle);
		logger.log(Status.INFO, "Verifing Registration Page Heading");
		Assert.assertEquals(rPage.getHeading(), RegistrationPage.ExpHeading);
		logger.log(Status.INFO, "Verifing Registration Page URL");
		Assert.assertEquals(Utils.getUrl(), RegistrationPage.ExpUrl);
	}
	
	@Test(priority=2,dataProvider ="fetchData")
	public void registerAccountTest(String Test,String fname, String lname, String email, String telephone, String pass, String conpass, String news,String privacy) throws InterruptedException {
		createTest(Test);
		openUrl();
		hPage.redirectToRegisterAccountPage();
		rPage.enterDetails(fname, lname, email, telephone, pass, conpass,news,privacy);
	}
	
	
	@Test
	public void validateNewsletterField() {
		createTest("Validate Newsletter field is Subscribed to No by default when account page open");
		openUrl();
		hPage.redirectToRegisterAccountPage();
		Assert.assertTrue(rPage.validateNewsletterSubscription());
		
	}
	
	
	@Test
	public void validatePrivacyPolicyField() {
		createTest("Validate Privacy Policy is not selected by default when account page open");
		openUrl();
		hPage.redirectToRegisterAccountPage();
		Assert.assertFalse(rPage.validatePrivacyPolicy());
		
	}
	
	@Test
	public void validateAllLinks() {
		createTest("Validate all the Page link available on the Register Account Page are redirecting to Login page");
		openUrl();
		hPage.redirectToRegisterAccountPage();
		rPage.listOfAllLinks();
	}
	
	
	@DataProvider
	public String[][] fetchData() throws IOException{
		String[][] data = ManageExcelData.readData("Sheet1");
		return data;
	}
	
	@AfterMethod
	public void checkResult(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(Status.FAIL, result.getThrowable());
		}
		
		if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS,MarkupHelper.createLabel("Test Case Pass", ExtentColor.GREEN));
		}
		
		//driver.close();
	}
	
	@AfterTest
	public void teardown() {
		report.flush();
	}
}
