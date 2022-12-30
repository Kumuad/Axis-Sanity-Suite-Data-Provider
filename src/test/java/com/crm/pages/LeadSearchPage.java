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


public class LeadSearchPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(LeadSearchPage.class);
	// Constructor
	public LeadSearchPage(WebDriver driver) {
		this.driver = driver;
		

	}

	LeadsPage leadspage = new LeadsPage(driver);

	
	// Methods
	// verify Lead Search By Lead ID

	public void verifyLeadSearchByLeadID(String LeadID) throws Exception {

		//String actualLeadID = ExcelUtils.getCellData(leadcreation_sheetname, "LeadID", 1);
		Thread.sleep(2000);
		CommonMethods.sendkeys("leadIDtextBox_XPATH", LeadID);
		CommonMethods.Click("fetchButtonLeadSearchPage_XPATH");
		Thread.sleep(2000);
		String expLeadID = leadspage.leadIdNumber();

		Assert.assertEquals(LeadID, expLeadID, "Lead ID mismatched");
		//ScreenShot.takeScreenShot("Lead Search By Lead ID");

	}

	// verify Lead Search By Mobile Phone
	public void verifyLeadSearchByMobilePhone(String LeadID,String Mobile) throws Exception {

		//String actualMobilenumber = ExcelUtils.getCellData(leadcreation_sheetname, "Mobile", 1);
		Thread.sleep(2000);
		//String actualLeadID = ExcelUtils.getCellData(leadcreation_sheetname, "LeadID", 1);
		CommonMethods.sendkeys("leadIDtextBox_XPATH", LeadID);
		CommonMethods.sendkeys("MobiletextBox_XPATH", Mobile);
		Thread.sleep(2000);
		CommonMethods.Click("fetchButtonLeadSearchPage_XPATH");
		Thread.sleep(2000);
		String expMobilenumber = leadspage.verifymobileNumberOnLeadPage();

		Assert.assertEquals(Mobile, expMobilenumber, "Lead Mobile Number mismatched ");

		//ScreenShot.takeScreenShot("Lead Search By Mobile Number");

	}

	// verify Lead Search By PAN Number
	public void verifyLeadSearchByPANNumber(String LeadID,String PAN) throws Exception {
		Thread.sleep(2000);
		//String actualLeadID = ExcelUtils.getCellData(leadcreation_sheetname, "LeadID", 1);
		CommonMethods.sendkeys("leadIDtextBox_XPATH", LeadID);
		
		//String actualPANnumber = ExcelUtils.getCellData(leadcreation_sheetname, "PAN", 1);
		
		CommonMethods.sendkeys("PANtextBox_XPATH", PAN);
		CommonMethods.Click("fetchButtonLeadSearchPage_XPATH");
		

		
	}
}
