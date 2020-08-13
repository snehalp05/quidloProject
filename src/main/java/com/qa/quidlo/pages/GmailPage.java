package com.qa.quidlo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.quidlo.base.TestBase;
import com.qa.quidlo.util.TestUtil;

public class GmailPage extends TestBase {
	
	@FindBy(xpath="//input[@type='email']")
	WebElement inputEmailID;
	@FindBy(xpath="//input[@type='password']")
	WebElement inputPassword;
	@FindBy(xpath="//span[text()='Next']/following-sibling::div")
	WebElement btnNext;
	@FindBy(xpath="//a[@class='gb_D']")
	WebElement btnApps;
	@FindBy(xpath="//a[@href='https://mail.google.com/mail/?tab=km']")
	WebElement btnGmail;
	@FindBy(xpath="//input[@aria-label='Search mail']")
	WebElement inputSearch;
	@FindBy(xpath="//button[@aria-label='Search mail']")
	WebElement btnSearch;
	@FindBy(xpath="//div[@class='aDP']//descendant::span[contains(text(),':) Activate your account')]")
	WebElement txtSearchResult;
	@FindBy(xpath="//a[contains(text(),' ACTIVATE ACCOUNT')]")
	WebElement lnkActivate;
	
	String activationURL;
	
	public GmailPage(){
		PageFactory.initElements(driver, this);
	}
	//verify gmail page title
	public String gmailPageNavigation() {
		log.info("Getting gmail login page title");
		String title=driver.getTitle();
		return title;
	}
	//verify gmail signIn
	public String verifyGmailSignIn() {
		log.info("Entering email Id: "+prop.getProperty("emailId"));
		if(inputEmailID.isDisplayed())
			inputEmailID.sendKeys(prop.getProperty("emailId"));
		
		if (btnNext.isDisplayed())
			btnNext.click();
		
		log.info("Entering password: "+prop.getProperty("gmailPassword"));
	    wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
	    inputPassword.sendKeys(prop.getProperty("gmailPassword"));
	    if (btnNext.isDisplayed())
			btnNext.click();
	    
	    activationURL=clickonActivationLink();
	    return activationURL;
	}
	//click on activation account link from email
	public String clickonActivationLink() {
		
		log.info("Searching for quidlo activation link email with : "+prop.getProperty("searchCriteria"));
		if (inputSearch.isDisplayed())
			inputSearch.sendKeys(prop.getProperty("searchCriteria"));
		if (btnSearch.isDisplayed())
			btnSearch.click();
		
		log.info("Activating account");
		if (txtSearchResult.isDisplayed())
			txtSearchResult.click();
		
		log.info("Click on Activation link");
		if (lnkActivate.isDisplayed())
			lnkActivate.click();
		
		TestUtil.switchToChildWindow(2);
		
		log.info("Get activation link page URL");
		activationURL=driver.getCurrentUrl();
		return activationURL;
	}
	
	
	
}
