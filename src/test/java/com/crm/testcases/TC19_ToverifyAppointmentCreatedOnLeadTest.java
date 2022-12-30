package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LeadSearchPage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;
import com.crm.listeners.CustomListener;

public class TC19_ToverifyAppointmentCreatedOnLeadTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;
	LeadSearchPage leadsearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyAppointmentCreatedOnLead(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyAppointmentCreatedOnLead";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyAppointmentCreatedOnLead",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyAppointmentCreatedOnLead".toUpperCase()
					+ "as the Run mode is NO");

		}

		iterationCount++;
		// String sheetname1 = "verifyAccountIsCreated";

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
		// click On Toggle Button
		leadspage.clickToggleButton();

		Thread.sleep(2000);

		// click On Activities Tab
		leadspage.clickOnActivitiestab();
		Thread.sleep(2000);

		// click On New Activity
		leadspage.clickOnNewActivityLink();

		Thread.sleep(2000);
		retailAppointmentPage = new RetailAppointmentPage(driver);

		// create Activity/Appointment on existing Lead
		retailAppointmentPage.createAppointment1(data.get("AppointmentStatus"), data.get("CustomerType"),
				data.get("TypeOfCustomer"), data.get("TypeofContact"), data.get("Subject"), data.get("Details"),
				sheetname, rows.get(iterationCount-1));
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC19_AppointmentCreatedOnLead");
		// logout
		loginpage.Logout();

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
//		// create New Lead
//		leadspage.createLead1(sheetname);
//		Thread.sleep(3000);
//		// Click On Recent Items
//		homepage.clickOnRecentItems();

		// leadspage = new LeadsPage(driver);

	}
}