package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;
import com.crm.pages.LeadSearchPage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC21_ToverifyLeadHistoryPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;
	LeadSearchPage leadsearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyLeadHistoryPage(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyLeadHistoryPage";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyLeadHistoryPage",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyLeadHistoryPage".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;
		loginpage = new LoginPage(driver);
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);
		// Select BRO Role
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

		Thread.sleep(2000);
		leadspage = new LeadsPage(driver);
		// click On Toggle Button
		leadspage.clickToggleButton();

		// click On history Tab
		leadspage.clickOnHistoryTab();
		Thread.sleep(3000);

		// verify Lead History Details Page
		leadspage.verifyLeadHistoryDetails();
		ScreenShot.takeScreenShot("TC21_Lead History Details Tab");
		// logout
		loginpage.Logout();

//		Thread.sleep(2000);
//
//		// click on New Button
//		leadspage.clickOnNewButton();
//		Thread.sleep(2000);
//
//		// click On Lead Layout
//		homepage.clickLeadLayout();
//
//		// create New Lead
//		leadspage.createLead1(sheetname);
//
//		// Click On Recent Items
//		homepage.clickOnRecentItems();
//
//		leadspage = new LeadsPage(driver);
//
//	

	}

}
