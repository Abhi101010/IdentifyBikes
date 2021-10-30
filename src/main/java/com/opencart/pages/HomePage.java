package com.opencart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.opencart.base.Base;

public class HomePage extends Base{
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	@FindBy(partialLinkText ="My Account")
	WebElement MyAccountButton;
	
	@FindBy(partialLinkText ="Register")
	WebElement RegisterButton;
	
	
	public void redirectToRegisterAccountPage() {
		logger.log(Status.INFO, "Clicked on My Account Button");
		MyAccountButton.click();
		logger.log(Status.INFO, "Clicked on Register Button");
		RegisterButton.click();
		
	}
}
