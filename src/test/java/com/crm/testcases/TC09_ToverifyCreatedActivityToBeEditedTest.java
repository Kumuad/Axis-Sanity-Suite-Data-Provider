package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;
import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;



public class TC09_ToverifyCreatedActivityToBeEditedTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	
	CommonMethods commonmethods;
	RetailAppointmentPage retailAppointmentPage;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyCreatedActivityEdited(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyCreatedActivityEdited";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyCreatedActivityEdited",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyCreatedActivityEdited".toUpperCase()
					+ "as the Run mode is NO");

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

		// Move to Home Tab
		//homepage.moveToHomeTab();

		Thread.sleep(2000);

		// CLick On Activities Link
		homepage.clickOnActivitiesLink();
		Thread.sleep(2000);
		retailAppointmentPage = new RetailAppointmentPage(driver);
		// click on Arrow button Under Views
		retailAppointmentPage.clickOnArrowButton();
		
		Thread.sleep(4000);
		retailAppointmentPage.searchActivity(data.get("ActivityID"));
		Thread.sleep(2000);
		// Mouse Hover On Appointment Close Edit link in Views
		retailAppointmentPage.movehoverOnCLoseEditLink();
		Thread.sleep(2000);

		// click on Edit Link under Views
		retailAppointmentPage.clickOnEditViewLink();
		Thread.sleep(2000);

		// Edit Appointment
		retailAppointmentPage.editAppointment(data.get("AppointmentStatus"),data.get("Details"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC09_Appointment Edited");
		// logout
		loginpage.Logout();

	}

}
