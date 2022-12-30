package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;
import com.crm.pages.LeadSearchPage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;

import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC16_LeadSearchTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	LeadSearchPage leadsearchPage;
	TaskPage taskpage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyLeadSearch(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyLeadSearch";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyLeadSearch",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyLeadSearch".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;

		// String sheetname1 = "verifyNewLeadIsCreated";

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

		// move to Quick Link
		// homepage.moveToQuickLink();

		Thread.sleep(2000);

		// click On Lead Search tab Under Quick Link
		// homepage.clickOnLeadSearchTab();
		Thread.sleep(2000);

		leadsearchPage = new LeadSearchPage(driver);

		// Verify Lead Search By Lead ID
		leadsearchPage.verifyLeadSearchByLeadID(data.get("LeadID"));
		ScreenShot.takeScreenShot("TC16_Lead Search By Lead ID");
		driver.navigate().back();
		Thread.sleep(2000);

		// Verify Lead Search By Lead Mobile Number
		leadsearchPage.verifyLeadSearchByMobilePhone(data.get("LeadID"),data.get("Mobile"));
		ScreenShot.takeScreenShot("TC16_Lead Search By Mobile Number");

		driver.navigate().back();

		Thread.sleep(2000);

		// Verify Lead Search By Lead PAN Number
		leadsearchPage.verifyLeadSearchByPANNumber(data.get("LeadID"),data.get("PAN"));
		ScreenShot.takeScreenShot("TC16_Lead Search By PAN Number");

		driver.navigate().back();

		Thread.sleep(2000);

		driver.navigate().back();
		// logout
		loginpage.Logout();

	}

}
