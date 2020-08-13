package com.qa.quidlo.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.quidlo.base.TestBase;
import com.qa.quidlo.pages.HomePage;
import com.qa.quidlo.pages.SignupPage;

public class HomePageTest extends TestBase{

	HomePage homePage;
	SignupPage signupPage;
	
	HomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();	
		driver.get(prop.getProperty("quidloURL"));
		homePage=new HomePage();
	}
	//1. verify Home Page Title Test
	@Test
	public void verifyHomePageTitle() {
		log.info("Testcase to verify Home page title");
		try {
			String homePageTitle=homePage.getHomePageTitle();
			System.out.println(homePageTitle);
			log.debug("Checking the Home page title");
			Assert.assertEquals(homePageTitle, prop.getProperty("actualHomePageTitle"), "Home Page title does not match");
		}catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	//2. verify navigation to Sign up page
	@Test
	public void verifySignUpPageNav() {
		log.info("Testcase to verify navigation to sign up page");
		log.info("Navigating to Sign up page");
		try {
			signupPage=homePage.openSignUpPage();
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
