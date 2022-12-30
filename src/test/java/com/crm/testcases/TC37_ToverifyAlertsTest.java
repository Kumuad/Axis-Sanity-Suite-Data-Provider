package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.AlertsPage;
import com.crm.pages.CalendarPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC37_ToverifyAlertsTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	AlertsPage alertspage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void VerifyAlerts(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "VerifyAlerts";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("VerifyAlerts",sheetname))) {

			throw new SkipException("Skipping the test " + "VerifyAlerts".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
		Thread.sleep(1000);

		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);

		homepage = new HomePage(driver);
		// Select Administrator Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);
		// Move to Home Tab
		homepage.moveToHomeTab();

		// click on calendar link
		homepage.clickOnAlertsLink();
		Thread.sleep(3000);

		alertspage = new AlertsPage(driver);

		// click on New Button
		alertspage.clickOnNewButton();
		Thread.sleep(2000);

		// create alerts
		alertspage.createalert(data.get("Title"),data.get("Details"),sheetname,rows.get(iterationCount-1));
		ScreenShot.takeScreenShot("TC37_Alert Creation");
		Thread.sleep(2000);
		// logout
		loginpage.Logout();

	}
}
