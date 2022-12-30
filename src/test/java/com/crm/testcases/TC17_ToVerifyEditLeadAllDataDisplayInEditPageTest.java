package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;
import com.crm.pages.LeadSearchPage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.OffersAndLeadPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC17_ToVerifyEditLeadAllDataDisplayInEditPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	OffersAndLeadPage offersAndLeadPage;
	LeadsPage leadspage;
	LeadSearchPage leadsearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyEditLead(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyEditLead";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyEditLead", sheetname))) {

			throw new SkipException("Skipping the test " + "verifyEditLead ".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;
		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);

		homepage = new HomePage(driver);
		// Select Administrator Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);

		// click On Leads Link
		homepage.clickOnLeadsLink();

		Thread.sleep(2000);

		// click on Advanced search
		homepage.clickAdvancedSearch();

		leadsearchPage = new LeadSearchPage(driver);

		// Lead Search By Lead ID
		leadsearchPage.verifyLeadSearchByLeadID(data.get("LeadID"));
		leadspage = new LeadsPage(driver);
		Thread.sleep(2000);
		// click On Toggle Button
		leadspage.clickToggleButton();

		Thread.sleep(2000);

		Thread.sleep(2000);
		leadspage.clickOnEditButton();

		Thread.sleep(5000);
		
		leadspage.verifyEditLeadPage(data.get("FirstName"),data.get("LastName"),data.get("Mobile"),
			data.get("Email"),data.get("PAN"),data.get("DateofBirth"),data.get("Address"),
			data.get("Product"),data.get("SubProduct"),data.get("Branch"),data.get("LeadSource"),
			data.get("Subsource"),data.get("LeadDescription"),sheetname,rows.get(iterationCount-1));
				

		Thread.sleep(2000);
		loginpage.Logout();

		ScreenShot.takeScreenShot("TC17_EditLead");

//		// click On Leads Link
//		homepage.clickOnLeadsLink();
//
//		leadspage = new LeadsPage(driver);
//
//		Thread.sleep(2000);
//
//		// click on New Button
//		leadspage.clickOnNewButton();
//		Thread.sleep(2000);
//
//		// click On Lead Layout
//		homepage.clickLeadLayout();
//
//		
//		leadspage.createLead1(sheetname);
//
//		// Click On Recent Items
//		homepage.clickOnRecentItems();

	}
}