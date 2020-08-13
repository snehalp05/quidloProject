package com.qa.quidlo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.qa.quidlo.base.TestBase;

public class HomePage extends TestBase {
	
	SignupPage signupPage;
	//WebElements on the page
	@FindBy(xpath="//a[@class='button']")
	WebElement btnSignUp;
	
	//initialize webelements
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//verify Home Page navigation
	public String getHomePageTitle() {
		log.info("Getting the page title on Home Page");
		String title=driver.getTitle();
		return title;
	}
	
	//open signup page
	public SignupPage openSignUpPage() {
		log.info("Click on Sign up link");
		 wait.until(ExpectedConditions.elementToBeClickable(btnSignUp));
			 btnSignUp.click();
		 
		 return new SignupPage();
	}

}
