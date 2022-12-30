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

public class TC22_ToverifyPaginationAndOrderByOnLeadListingPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyPaginationOnLead(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyPaginationOnLead";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyPaginationOnLead",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyPaginationOnLead".toUpperCase()
					+ "as the Run mode is NO");

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

		// click On Arrow Button
		leadspage.clickOnArrowButton();
		Thread.sleep(4000);

		// verfiy Order By for Lead ID Column
		leadspage.verifyOrderBy();

		// verify Pagination Dropdown
		leadspage.verifyPaginationDropdown();
		ScreenShot.takeScreenShot("TC22_Pagination on Lead");

		// verify Pagination Go to TextBox
		leadspage.verifypaginationGoToTextBox();

		// logout
		loginpage.Logout();

	}

}
