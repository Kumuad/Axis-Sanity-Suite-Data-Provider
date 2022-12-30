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

public class TC20_ToverifyTaskCreatedOnLeadTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;
	LeadSearchPage leadsearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyTaskCreatedOnLead(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyTaskCreatedOnLead";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyTaskCreatedOnLead",sheetname))) {

			throw new SkipException(
					"Skipping the test " + " verifyTaskCreatedOnLead".toUpperCase() + "as the Run mode is NO");

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

		leadspage = new LeadsPage(driver);

		Thread.sleep(2000);
		// Click on Toggle Button
		leadspage.clickToggleButton();

		Thread.sleep(2000);

		// click On Activities Tab
		leadspage.clickOnActivitiestab();
		Thread.sleep(2000);

		// click On New Task Link
		leadspage.clickOnNewTaskLink();

		// Create Task On Lead

		taskpage = new TaskPage(driver);

		// create Task On Lead
		taskpage.createtask(data.get("Subject"),data.get("Description"),sheetname,rows.get(iterationCount-1));
		ScreenShot.takeScreenShot("TC20_TaskCreatedOnLead");
		// logout
		loginpage.Logout();

		// click On Leads Link
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
//		// create New Lead
//		leadspage.createLead1(sheetname);
//		Thread.sleep(2000);
//		
//		// Click On Recent Items
//		homepage.clickOnRecentItems();
	}
}
