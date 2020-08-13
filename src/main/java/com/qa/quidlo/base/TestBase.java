package com.qa.quidlo.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.quidlo.util.TestUtil;
import com.qa.quidlo.util.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;	
	public static WebDriverWait wait;
	public static Logger log;
	
static{
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.setProperty("currentdatetime", dateFormat.format(new Date()));
       System.out.println(System.getProperty("currentdatetime"));
    }


	//initialize properties file

	public TestBase() {
		try {
		prop=new Properties();
		System.out.println(System.getProperty("user.dir"));
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\quidlo\\properties\\config.properties");
		prop.load(file);
		}catch(FileNotFoundException e) {
			e.getStackTrace();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	//initialize browser
	public static void initialization() {
		log= Logger.getLogger(TestBase.class);
		
		String browserName=prop.getProperty("browser");
		log.info("Launching the "+browserName+" browser");
		if(browserName!=null) {
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", prop.getProperty("chromFilePath"));
				driver=new ChromeDriver();
			}
			else if(browserName.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxFilePath"));
				driver=new FirefoxDriver();
			}
			else {
				log.error("browser property is not correct in properties file");
			}
		}
		else {
				log.error("Browser property is null ");
		}
		
		e_driver = new EventFiringWebDriver(driver);
		
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		log.info("Applying browser settings");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait= new WebDriverWait(driver, 20);
		
	}
}
