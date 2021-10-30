package com.opencart.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.opencart.base.Base;
import com.openkart.utils.Utils;

public class RegistrationPage extends Base {

	public RegistrationPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//h1)[2]")
	WebElement heading;

	@FindBy(id = "input-firstname")
	WebElement firstName;

	@FindBy(id = "input-lastname")
	WebElement lastName;

	@FindBy(id = "input-email")
	WebElement emailid;

	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(id = "input-confirm")
	WebElement confirmPassword;

	@FindBy(id = "input-telephone")
	WebElement telephone;

	@FindBy(xpath = "//input[@name='newsletter' and @value=1]")
	WebElement newsletter_yes;
	
	@FindBy(xpath = "//input[@name='newsletter' and @value=0]")
	WebElement newsletter_no;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement privacypolicy;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement submit;
	
	@FindBy(xpath="//div[@class='list-group']")
	WebElement allLink;
	
	public static String ExpTitle="Register Account";
	public static String ExpHeading="Register Account";
	public static String ExpUrl="https://demo.opencart.com/index.php?route=account/register";

	public String getHeading() {
		return heading.getText();
	}

	public void enterDetails(String fname, String lname, String email, String tele, String pass, String confPass,String news,String privacy) {
		
		try {
			if ( fname != "") {
				firstName.sendKeys(fname);
				logger.log(Status.INFO, "Entered First Name");
			} else {
				logger.log(Status.INFO, "First Name Not Entered");
			}
		} catch (Exception e) {
		}
		
		
		try {
			if ( lname != "") {
				lastName.sendKeys(lname);
				logger.log(Status.INFO, "Entered Last Name");
			} else {
				logger.log(Status.INFO, "Last Name Not Entered");
			}
		} catch (Exception e) {
		}

		
		try {
			if ( email != "") {
				emailid.sendKeys(email);
				logger.log(Status.INFO, "Entered E-Mail");
			} else {
				logger.log(Status.INFO, "E-Mail Not Entered");
			}
		} catch (Exception e) {
		}
		
		
		try {
			if ( tele != "") {
				telephone.sendKeys(tele);
				logger.log(Status.INFO, "Entered Telephone");
			} else {
				logger.log(Status.INFO, "Telephone Not Entered");
			}
		} catch (Exception e) {
		}
		
		
		try {
			if ( pass != "") {
				password.sendKeys(pass);
				logger.log(Status.INFO, "Entered Password");
			} else {
				logger.log(Status.INFO, "Password Not Entered");
			}
		} catch (Exception e) {
		}
		
		
		try {
			if ( confPass != "") {
				confirmPassword.sendKeys(confPass);
				logger.log(Status.INFO, "Entered Confirm Password");
			} else {
				logger.log(Status.INFO, "Confirm Password Not Entered");
			}
		} catch (Exception e) {
		}
		
		if(privacy.equalsIgnoreCase("Yes")) {
		privacypolicy.click();
		logger.log(Status.INFO, "Privacy policy accepted");
		}
		
		if(news.equalsIgnoreCase("Yes")) {
			newsletter_yes.click();
			logger.log(Status.INFO, "Newsletter Subscribed");
		}
		
		submit.click();
		logger.log(Status.INFO, "Clicked on Submit Button");

		if (Utils.getTite().equalsIgnoreCase("Register Account")) {
			try {
				logger.log(Status.FAIL, driver.findElement(By.xpath("(//div[@class='text-danger'])[1]")).getText());
				logger.log(Status.FAIL, driver.findElement(By.xpath("(//div[@class='text-danger'])[2]")).getText());
				logger.log(Status.FAIL, driver.findElement(By.xpath("(//div[@class='text-danger'])[3]")).getText());
				logger.log(Status.FAIL, driver.findElement(By.xpath("(//div[@class='text-danger'])[4]")).getText());
				logger.log(Status.FAIL, driver.findElement(By.xpath("(//div[@class='text-danger'])[5]")).getText());
			} catch (Exception e) {
			}
			
			try {
				logger.log(Status.FAIL, driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText());
			} catch (Exception e) {
			}
			Assert.fail();
		}
		else if (Utils.getTite().equalsIgnoreCase("Your Account Has Been Created!")) {
			logger.log(Status.PASS, "Account Created");
		}
	}
	
	public boolean validateNewsletterSubscription() {
		logger.log(Status.INFO, "Checking if Newsletter subscription is selected No by default" );
		return newsletter_no.isSelected();
	}
	
	public boolean validatePrivacyPolicy() {
		logger.log(Status.INFO, "Checking if Privacy Policy is not selected by default" );
		return privacypolicy.isSelected();
	}
	
	public void listOfAllLinks() {
		List<WebElement> links = allLink.findElements(By.xpath("//a[@class='list-group-item']"));
		
		
		for(int i=0;i<links.size();i++) {
			
				if(i==1)continue;		
				PageFactory.initElements(driver, this);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(links.get(i)));
			
			links.get(i).click();
			logger.log(Status.PASS, "Clicked on");
			Assert.assertEquals(Utils.getTite(), LoginPage.expTitle);
			logger.log(Status.PASS, "Successfully Rediredted to Login Page");
			
			driver.navigate().back();
		}
	}

}
