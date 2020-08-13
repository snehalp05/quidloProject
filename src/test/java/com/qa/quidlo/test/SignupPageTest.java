package com.qa.quidlo.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.quidlo.base.TestBase;
import com.qa.quidlo.pages.HomePage;
import com.qa.quidlo.pages.SignupPage;

public class SignupPageTest extends TestBase {
	
	HomePage homePage;
	SignupPage signupPage;
	
	SignupPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		driver.get(prop.getProperty("quidloURL"));
		homePage=new HomePage();
		signupPage=homePage.openSignUpPage();
		
	}
	
	//1. verify Sign up page navigation
	@Test
	public void verifySignupPageTest() {
		log.info("Testcase to verify Sign up page title");
		try {
			String signupPageTitle=signupPage.verifySignupPage();
			log.debug("Checking the Sign up page title");
			Assert.assertTrue(signupPageTitle.contains(prop.getProperty("signupPageTitle")), "Sign up Page title does not match");
		}catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	//2. Verify sign up process
	@Test
	public void verifySignupTest() {
		log.info("Testcase to verify Sign up process");
		try {
			String msgText=signupPage.fillSignupDetails();
			log.debug("Checking the success Message on screen");
			Assert.assertTrue(msgText.contains(prop.getProperty("successMsg")));	
		}catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
