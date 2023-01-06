package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC15_ToVerifyNewLeadCreatedTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyNewLeadIsCreated(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyNewLeadIsCreated";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyNewLeadIsCreated",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyNewLeadIsCreated".toUpperCase() + "as the Run mode is NO");

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

		leadspage = new LeadsPage(driver);

		Thread.sleep(2000);

		// click on New Button
		leadspage.clickOnNewButton();
		Thread.sleep(2000);

		// click On Lead Layout
		homepage.clickLeadLayout();

		// create New Lead
		// leadspage.createLead(sheetname);
		
		leadspage.createLead1(data.get("Name"),data.get("FirstName"),data.get("LastName"),data.get("Mobile"),
				data.get("Email"),data.get("DateofBirth"),data.get("PAN"),
				data.get("Address"),data.get("Product"),data.get("SubProduct"),data.get("Branch"),
				data.get("LeadSource"),data.get("Subsource"),data.get("LeadDescription"),sheetname,rows.get(iterationCount-1));
		ScreenShot.takeScreenShot("TC15_NewLeadCreated");
		Thread.sleep(2000);

		// logout
		loginpage.Logout();

	}

}
