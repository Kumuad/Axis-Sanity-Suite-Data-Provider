package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;



public class CustomerSearchPage extends TestBase{
	
	public static Logger log = LoggerFactory.getLogger(CustomerSearchPage.class);
	
	// Constructor
		public CustomerSearchPage(WebDriver driver) {
			
			this.driver = driver;
			

		}
		
		CustomerPage customerpage = new CustomerPage(driver);

		
		
		
		//Methods
		
		//verify Customer Search By Customer ID
		public void verifyCustomerSearchByCustomerID(String CustomerID) throws Exception {
			
			//String actualcustomerid = ExcelUtils.getCellData(customer_sheetname, "CustomerID", 1);
			CommonMethods.sendkeys("customerIDTextBox_XPATH", CustomerID);
			Thread.sleep(2000);
			CommonMethods.Click("fetchButtonCustomerSearchPage_XPATH");
			Thread.sleep(2000);
			String expcustomerid = customerpage.getcustomerID();
			
			Assert.assertEquals(CustomerID, expcustomerid, "Customer ID mismatched");
			//ScreenShot.takeScreenShot("Customer ID mismatched");
			
		}

		//verify Customer Search By Mobile Phone
		public void verifyCustomerSearchByMobilePhone(String Mobile,String sheetname,int row) throws Exception {

			
			
			
			//String actualmobile = ExcelUtils.getCellData(customer_sheetname, "Mobile", 1);
			CommonMethods.sendkeys("mobilePhoneTextBox_XPATH", Mobile);
			Thread.sleep(2000);
			CommonMethods.Click("fetchButtonCustomerSearchPage_XPATH");
			Thread.sleep(2000);
			
			String expmobile = customerpage.getMobilePhone();
			Assert.assertEquals(Mobile, expmobile, "Mobile Phone mismatched");
			//ScreenShot.takeScreenShot("Mobile Phone mismatched");
		}
		
		//verify Customer Search By PAN Number
		
		public void verifyCustomerSearchByPANNumber(String PAN,String sheetname,int row) throws Exception {

			
			
			//String actualpannum = ExcelUtils.getCellData(customer_sheetname, "PAN", 1);
			CommonMethods.sendkeys("panNumbertextBox_XPATH", PAN);
			Thread.sleep(2000);
			CommonMethods.Click("fetchButtonCustomerSearchPage_XPATH");
			Thread.sleep(2000);
			
			
			
			String exppannumber = customerpage.getPANNumber();
			Assert.assertEquals(PAN, exppannumber, "PAN Number mismatched");
			//ScreenShot.takeScreenShot("Customer Search By PAN Number");
			
		}
		
		//verify Customer Search By Email ID
		
		public void verifyCustomerSearchByEmail(String Email,String sheetname,int row) throws Exception {

			
			
			
		
			//String actualemail = ExcelUtils.getCellData(customer_sheetname, "Email", 1);
			CommonMethods.sendkeys("emailtextBox_XPATH", Email);
			Thread.sleep(2000);
			CommonMethods.Click("fetchButtonCustomerSearchPage_XPATH");
			Thread.sleep(2000);
			
			String expemailid = customerpage.getEmailId();
			Assert.assertEquals(Email, expemailid, "Email ID mismatched");
			//ScreenShot.takeScreenShot("Email ID mismatched");
		}
		
}
