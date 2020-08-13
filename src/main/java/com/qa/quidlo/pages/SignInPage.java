package com.qa.quidlo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.qa.quidlo.base.TestBase;
import com.qa.quidlo.util.TestUtil;

public class SignInPage extends TestBase {

	@FindBy(xpath="//input[@type='text']")
	WebElement inputEmailID;
	@FindBy(xpath="//input[@type='password']")
	WebElement inputPassword;
	@FindBy(xpath="//span[text()='Sign in']//parent::button")
	WebElement btnSignIn;
	@FindBy(xpath="//a[@role='button']")
	WebElement lnkUserProfile;
	@FindBy(xpath="//div[@class='Toaster-style__toaster___1WB4l']")
	WebElement docNode;
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}
	//verify Sign in Page title
	public String VerifySignInPageTitle() {
		log.info("Getting the sign in page title");
		String title=driver.getTitle();
		return title;
	}
	//verify sign in
	public String SignIn(String emailId, String password ) {
		 
		log.info("Entering email id: "+emailId);
		wait.until(ExpectedConditions.elementToBeClickable(inputEmailID));
		inputEmailID.sendKeys(emailId);
		log.info("Enter Password: ");
		wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
		inputPassword.sendKeys(password);
		log.info("Click on Sign in");
		wait.until(ExpectedConditions.elementToBeClickable(btnSignIn));
		btnSignIn.click();
		
		//TestUtil.explicitWait(3000);
		wait.until(ExpectedConditions.visibilityOf(docNode));
	
		log.info("Getting the sign in message on the page");
		String msg=docNode.getText();
		return msg;
	}
	
}
