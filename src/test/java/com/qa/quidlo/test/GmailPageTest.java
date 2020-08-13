package com.qa.quidlo.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.quidlo.base.TestBase;
import com.qa.quidlo.pages.GmailPage;

public class GmailPageTest extends TestBase{

	GmailPage gmailPage;
	
	GmailPageTest(){
		super();
	}
	
	@BeforeTest
	public void setUp() {
		initialization();
		driver.get(prop.getProperty("gmailURL"));
		gmailPage=new GmailPage();
	}
	
	//1. verify gmail page navigation test
	@Test
	public void verfiGmailPageNav() {
		log.info("Testcase to Verify Gmail Page Navigation");
		String gmailLoginPageTitle=gmailPage.gmailPageNavigation();
		log.debug("Checking Gmail login page title");
		Assert.assertEquals(gmailLoginPageTitle, prop.getProperty("gmailTitle"), "Gmail Page is not opened");
	}
	//2. verify gmail login and click on activation account link from email
	@Test
	public void verifyActivationAccountClick() {
		log.info("Testcase to activte the account");
		String chkActivationURL=gmailPage.verifyGmailSignIn();
		log.debug("checking activate account window is opened");
		Assert.assertTrue(chkActivationURL.contains(prop.getProperty("activateAccountURLText")),"Activate Account window is not opened");
	}
	
	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}
}
