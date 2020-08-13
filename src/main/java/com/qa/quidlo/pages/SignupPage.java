package com.qa.quidlo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.qa.quidlo.base.TestBase;

public class SignupPage extends TestBase{
	
	//WebElements on the page
	@FindBy(xpath="//input[@type='text']")
	WebElement inputEmailAddress;
	@FindBy(xpath="//input[@type='password']")
	WebElement inputPassword;
	@FindBy(xpath="//div[@class='Checkbox-style__check___e8GGP']")
	WebElement chkTerms;
	@FindBy(xpath="//span[contains(text(),'Sign up')]//parent::button")
	WebElement btnSignup;
	@FindBy(xpath="//div[@class='InfoCard__infoCard___2ljym']")
	WebElement txtSuccessMsg;
	
	//initilize elements
	public SignupPage() {
		PageFactory.initElements(driver, this);
	}
	//verify signup page title
	public String verifySignupPage() {
		log.info("Getting the title from signup page");
		String title=driver.getTitle();
		return title;
	}
	//Fill the signup page form
	public String fillSignupDetails() {
		log.info("Entering email address: "+prop.getProperty("emailId"));
		wait.until(ExpectedConditions.elementToBeClickable(inputEmailAddress));
			inputEmailAddress.sendKeys(prop.getProperty("emailId"));
			
		log.info("Entering password: "+prop.getProperty("quidloPassword"));
		if (inputPassword.isDisplayed())
			inputPassword.sendKeys(prop.getProperty("quidloPassword"));
		
		log.info("Selecting Terms checkbox");
		if (chkTerms.isDisplayed())
			chkTerms.click();
		
		log.info("Clicking on Signup button");
		if (btnSignup.isDisplayed())
			btnSignup.click();
		
		log.info("Retrieving message on the page");
		String msgText=txtSuccessMsg.getText();

		return msgText;
	}
	
}
