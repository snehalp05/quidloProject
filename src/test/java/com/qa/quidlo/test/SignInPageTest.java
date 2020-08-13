package com.qa.quidlo.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.quidlo.util.TestUtil;
import com.qa.quidlo.base.TestBase;
import com.qa.quidlo.pages.SignInPage;

public class SignInPageTest extends TestBase {
	
	SignInPage signInPage;
	String successMsg;
	
	SignInPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() {
			initialization();
			driver.get(prop.getProperty("signInURL"));
			signInPage=new SignInPage();	
	}
	//1. check navigation to SIgn in page
	@Test
	public void verifySignInPageTest() {
		log.info("Testcase to verify Sign in page title");
		try {
			String SignInPageTitle=signInPage.VerifySignInPageTitle();
			log.debug("Checking the sign in page title");
			Assert.assertEquals(SignInPageTitle, prop.getProperty("signInPageTitle"), "Sign In Page title does not match");
		} catch(Exception e) {
			log.error(e.getMessage());
			log.error(e.getStackTrace());
		}
	}
	
	@DataProvider
	public Object[][] getDataFromExcel() {
		Object data[][]=TestUtil.getTestData("SignInPage");
		return data;
	}
	//2. verify Sign in Test with data from Excel
	@Test(dataProvider="getDataFromExcel")
	public void verifySignInTest(String emailId, String quidloPassword, String signInMsg) {
		log.info("Testcase to verify login");
		try {
			successMsg=signInPage.SignIn(emailId, quidloPassword);
			log.debug("Checking the Sign in message on the screen");
			Assert.assertTrue(successMsg.contains(signInMsg));
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
