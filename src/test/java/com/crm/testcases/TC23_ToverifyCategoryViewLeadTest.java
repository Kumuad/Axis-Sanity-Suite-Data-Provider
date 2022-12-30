package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LeadSearchPage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;

public class TC23_ToverifyCategoryViewLeadTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	LeadSearchPage leadsearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyCategoryViewLead(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyCategoryViewLead";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyCategoryViewLead", sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyCategoryViewLead".toUpperCase() + "as the Run mode is NO");

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

		leadspage = new LeadsPage(driver);

		// Verify Category Views Lead display records
		leadspage.verifyCategoryViewLead(data.get("LeadID"));

		// logout
		loginpage.Logout();

//			// Move to sales Tab
//			//homepage.moveToSalesTab();
//			Thread.sleep(2000);
//
//			// click On Leads Link
//			homepage.clickOnLeadsLink();
//			
//			Thread.sleep(2000);
//			// Mouse hover on New Button
//			homepage.clickOnNewButton();
//			Thread.sleep(2000);
//			
//			//click On Lead Layout
//			homepage.clickLeadLayout();
//			
//
//			leadspage = new LeadsPage(driver);
//			
//			// create New Lead
//			leadspage.createLead1(sheetname);
//
//			Thread.sleep(2000);
//			//click On Home Tab
//			homepage.clickOnHomeTab();
//			Thread.sleep(2000);

	}
}
