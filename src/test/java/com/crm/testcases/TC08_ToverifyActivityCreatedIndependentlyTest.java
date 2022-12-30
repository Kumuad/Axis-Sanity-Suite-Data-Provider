package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;
import com.crm.listeners.CustomListener;

public class TC08_ToverifyActivityCreatedIndependentlyTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;

	RetailAppointmentPage retailAppointmentPage;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyActivityCreated(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyActivityCreated";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyActivityCreated", sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyActivityCreated ".toUpperCase() + "as the Run mode is NO");

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

		// CLick On Activities Link
		homepage.clickOnActivitiesLinkRole();

		Thread.sleep(4000);

		// click On New Button
		homepage.clickOnNewButton();

		Thread.sleep(2000);
		// click On Retail Appointment link
		homepage.clickOnRetailAppointmentLink();

		retailAppointmentPage = new RetailAppointmentPage(driver);

		// create independent Activity/Appointment
		retailAppointmentPage.createAppointment1(data.get("AppointmentStatus"), data.get("CustomerType"),
				data.get("TypeOfCustomer"), data.get("TypeofContact"), data.get("Subject"), data.get("Details"),
				sheetname,rows.get(iterationCount-1) );
		ScreenShot.takeScreenShot("TC08_ActivityCreated");
		Thread.sleep(2000);

		// logout
		loginpage.Logout();

	}

}
