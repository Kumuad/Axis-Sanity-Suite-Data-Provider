package com.crm.pages;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;



public class CustomerPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(CustomerPage.class);
	// Constructor
	public CustomerPage(WebDriver driver) {
		//super();
		this.driver = driver;
		
	}

	
	// **************Methods********************

	// click On New Button On Customer Page
	public void clickOnNewButton() throws Exception {
		
		
		CommonMethods.mouseHover("newButton_XPATH");
		CommonMethods.highlightelement("newButton_XPATH");

	}

	// click on retail Customer
	public void clickOnRetailCustomerLink() throws Exception {
		Thread.sleep(2000);
		
		
		CommonMethods.highlightelement("retailCustomer_XPATH");
		CommonMethods.Click("retailCustomer_XPATH");
	}

	// click On offers and Lead Tab on details page
	public void clickoffersandLeadTab() throws Exception {
		
		CommonMethods.highlightelement("offersandLeadTab_XPATH");
		CommonMethods.Click("offersandLeadTab_XPATH");
	}

	// click On offers and Lead Tab on details page
	public void clickActivitiesAndInteractionTab() throws Exception {
		
		CommonMethods.highlightelement("activitiesandinteractionTab_XPATH");
		CommonMethods.Click("activitiesandinteractionTab_XPATH");
		
		
	}
	
	//click On New Activity Tab
	public void clickOnNewActivityTab() throws Exception {
		
		CommonMethods.highlightelement("newActivityTab_XPATH");
		CommonMethods.Click("newActivityTab_XPATH");
		
		
	}
	// click On toggle button on details page
	public void clickToggleButton() throws Exception {
		
		
		
		CommonMethods.highlightelement("toggleButton_XPATH");
		CommonMethods.Click("toggleButton_XPATH");
	}
		
	
	
	// Get Text of Subject on Customer Page

	public  String getSubjectText() throws Exception {
      String subject= CommonMethods.getElementText("subjectText_XPATH");
		return subject;

	}
	
	
	public String generateRandomaccountno() {
		Random random = new Random();
		String id=String.format("%04d",random.nextInt(10000));
		String accid = String.valueOf(id);
		String text="account";
		String actualaccountname = text + accid;
		return actualaccountname;
	}
	
	public void createAccount1(String CustomerName,String ShortName,String CustomerType,String Email,
			String Mobile,String Territory,String LeadSource,String PAN,
			String CustomerID,String Gender,String Title,String sheetname,int row) throws Exception {
		
		
		//Enter Customer Name
		String accountname=generateRandomaccountno();
		CommonMethods.sendkeys("accountName_XPATH", accountname);
		excel.setCellData(sheetname, "CustomerName", row, accountname);


		//Enter shortname
		CommonMethods.clear("accountShortname_XPATH");
		CommonMethods.sendkeys("accountShortname_XPATH", ShortName);
		
		//Select Customer Type
		CommonMethods.selectByText("accountCustomerTypeDropdown_XPATH", CustomerType);
		CommonMethods.scrollDown(150);
		
		//Enter Email
		String email=CommonMethods.generateRandomEmail();
		CommonMethods.scrollByVisibilityofElement("accountEmail_XPATH");
		CommonMethods.sendkeys("accountEmail_XPATH", email);
		excel.setCellData(sheetname, "Email", row, email);
		
		CommonMethods.scrollDown(150);
		
		//Enter Mobile no
		String mobilenumber=CommonMethods.generateRandomMobileNumber();
		CommonMethods.sendkeys("accountMobileNumber_XPATH", mobilenumber);
		excel.setCellData(sheetname, "Mobile", row, mobilenumber);
		Thread.sleep(1000);
		  
		// Select Territory
		CommonMethods.sendkeys("accountTerritory_XPATH",Territory);
		CommonMethods.Click("table_XPATH");
		
		Thread.sleep(2000);
		
		// Select Lead source
		CommonMethods.selectByText("accountLeadSourceDropdown_XPATH", LeadSource);
		Thread.sleep(1000);
		CommonMethods.scrollDown(1000);
		
		// Enter PAN Number
		String pannumber = CommonMethods.generatePANNumber();
		CommonMethods.sendkeys("PANNumber_XPATH", pannumber);
		excel.setCellData(sheetname, "PAN", row, pannumber);
		
				
		//Enter Customer ID
		String customerid=CommonMethods.generateRandomCustomerID();
		CommonMethods.scrollByVisibilityofElement("accountCustomerID_XPATH");
		CommonMethods.sendkeys("accountCustomerID_XPATH", customerid);
		excel.setCellData(sheetname, "CustomerID", row, customerid);
		
		
		//Select Gender from dropdown
		CommonMethods.scrollByVisibilityofElement("accountGenderdropdown_XPATH");
		CommonMethods.selectByText("accountGenderdropdown_XPATH", Gender);
		
		//CommonMethods.scrollDown(1000);
		//Enter Title
		CommonMethods.scrollByVisibilityofElement("accountTitle_XPATH");
		CommonMethods.sendkeys("accountTitle_XPATH",Title);
		
		 
		// click on Save Button
		CommonMethods.highlightelement("saveButton_XPATH");	
		CommonMethods.Click("saveButton_XPATH");
		Thread.sleep(2000);
				  
		// click On Toggle button on Details Page 
		CommonMethods.Click("toggleButton_XPATH");
		Thread.sleep(5000);
				  
		 //Get exp account name from details page 		
		String AccountName= CommonMethods.getElementText("customernametext_XPATH");
		 Thread.sleep(2000);
		 
		 updateCustomerAllSheet(AccountName,customerid,email,pannumber,mobilenumber,row);
	}
	
	
	public void updateCustomerAllSheet(String AccountName,String customerid,String email,String  pannumber,String mobilenumber,int row) throws Exception {
		
		excel.setCellData("verifyLeadIsCreatedOnAccount", "CustomerID", row, customerid);
		excel.setCellData("verifyActivityCreationOnAccount", "CustomerID", row, customerid);
		excel.setCellData("verifyNewOfferCreatedOnAccount", "CustomerID", row, customerid);
		excel.setCellData("verifyCustomerSearch", "CustomerName", row, AccountName);
		excel.setCellData("verifyCustomerSearch", "CustomerID", row, customerid);
		excel.setCellData("verifyCustomerSearch", "Email", row, email);
		excel.setCellData("verifyCustomerSearch", "PAN", row, pannumber);
		excel.setCellData("verifyCustomerSearch", "Mobile", row, mobilenumber);
	}
	
	// get Customer ID from Customer Details Page
	public String getcustomerID() throws Exception {

		String expcustomerid = CommonMethods.getElementText("CustomerID_XPATH");

		return expcustomerid;

	}

	// get Mobile Phone from Customer Details Page
	public String getMobilePhone() throws Exception {

		String expmobile = CommonMethods.getElementText("mobilePhone_XPATH");

		return expmobile;
	}

	// get Mobile Phone from Customer Details Page
	public String getEmailId() throws Exception {

		String expemailid = CommonMethods.getElementText("emailid_XPATH");
		return expemailid;

	}

	// get Mobile Phone from Customer Details Page
	public String getPANNumber() throws Exception {

		String exppanno = CommonMethods.getElementText("panNumber_XPATH");
		return exppanno;

	}

	//
	public void verifyTabsVisibleOnCustomer() throws IOException, InterruptedException {

		List<WebElement> alloptions = driver
				.findElements(By.xpath("//ul[@class='crm-card-tab relative']/li/span/span"));
		int count = alloptions.size();
		System.out.println(count);
		for (WebElement ele : alloptions) {
			System.out.println("Actual Tabs Visible on Customer:" + ele.getText());
			log.info("Actual Tabs Visible on Customer:" + ele.getText());
			CustomListener.extentInfo("Actual Tabs Visible on Customer:" ,  ele.getText());
		}
		
	}

}
