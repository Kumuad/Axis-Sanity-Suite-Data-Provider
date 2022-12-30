package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;


public class LoginPage extends TestBase {
	
	public static Logger log = LoggerFactory.getLogger(LoginPage.class);
	// constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
		}

	
	
	
	

	// Methods

	// To login to page


	public void Login(String UserName, String Password) throws Exception {
		
		//Enter Username
		CommonMethods.sendkeys("Username_XPATH", UserName);
		
		//Enter password
		CommonMethods.sendkeys("Password_XPATH", Password);
		
		//click on login Button
		CommonMethods.Click("LoginButton_XPATH");
		
		
	}
	
	
	//logout
	public void Logout() throws Exception {
		CommonMethods.highlightelement("profileimage_XPATH");
		//CommonMethods.Click("profileimage_XPATH");
		CommonMethods.clickelementbyjavascript("profileimage_XPATH");
		Thread.sleep(2000);
		//CommonMethods.Click("LogoutButton_XPATH");
		CommonMethods.clickelementbyjavascript("LogoutButton_XPATH");

	}
public void Login(String sheetname) throws Exception {
		
		//Enter Username
		//CommonMethods.sendkeys("Username_XPATH", uname);
		CommonMethods.sendkeys("Username_XPATH", sheetname, "UserName", 1);
		
		//Enter password
		//CommonMethods.sendkeys("Password_XPATH", pwd);
		CommonMethods.sendkeys("Password_XPATH", sheetname, "Password", 1);
		//click on login Button
		CommonMethods.Click("LoginButton_XPATH");
		
		
	}
	

}
