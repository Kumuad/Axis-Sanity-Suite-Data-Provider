package com.crm.base;

//import java.io.File;
import java.io.FileInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.UnhandledException;
//import org.apache.log4j.PropertyConfigurator;
///import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.Status;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;

import io.github.bonigarcia.wdm.WebDriverManager;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static  WebDriver driver;
	
	//public static Properties config = new Properties();;
	//public static Properties ObjectRepository = new Properties();	
	public static ExcelUtils excel = new ExcelUtils();
	public static Logger log = LoggerFactory.getLogger(TestBase.class);
	public TestBase() {
		/**prop = new Properties();
		ObjectRepository = new Properties();
		FileInputStream ip;
		try {
			
			ip= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\propertiesfile\\confiq.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
		
			ip= new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\propertiesfile\\ObjectRepository.properties");
			ObjectRepository.load(ip);
		}
		catch(FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
**/
	}

	//properties file
		public static Properties loadConfig() {
			  Properties config = new Properties();

			  FileInputStream fis = null;
			
			try {

				
				
				//fis = new FileInputStream(
						//System.getProperty("user.dir") + "\\src\\test\\resources\\propertiesfile\\config.properties");
				//comment the previous two lines and Uncomment this for exporting the code as jar.
				fis = new FileInputStream(
						 System.getProperty("user.dir") + "\\resources\\propertiesfile\\config.properties");
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return config;
			
		}



	public static void initialization(String sheetName) throws Exception  {
		String browserName = CommonMethods.readPropertyFile("browser");
		if (browserName.equals("chrome")) {
			
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Browser launched");
			
		} else if (browserName.equals("FireFox")) {
			

			driver = new FirefoxDriver();
			log.info("Browser launched");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(CommonMethods.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(CommonMethods.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(CommonMethods.readPropertyFile("url"));
		log.info("URL : "+CommonMethods.readPropertyFile("url"));
		CustomListener.testReport.get().log(Status.INFO,"URL  " + " : "+CommonMethods.readPropertyFile("url"));
		 
	}
	
	
	
	@AfterTest
	public void teardown() {
		driver.quit();
		log.info("Browser closed");
	}

}
