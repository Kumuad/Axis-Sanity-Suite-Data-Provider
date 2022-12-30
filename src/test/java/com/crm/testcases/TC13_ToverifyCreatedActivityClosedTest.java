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


public class TC13_ToverifyCreatedActivityClosedTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	
	CommonMethods commonmethods;
	RetailAppointmentPage retailAppointmentPage;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyCreatedActivityClosed(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyCreatedActivityClosed";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyCreatedActivityClosed",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyCreatedActivityClosed".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;

		

		loginpage = new LoginPage(driver);
		Thread.sleep(1000);
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

		// click on Arrow button Under Views
		//retailAppointmentPage.clickOnArrowButton();
		Thread.sleep(2000);

		// Mouse Hover On Appointment Close Edit link in Views
		retailAppointmentPage.movehoverOnCLoseEditLink();
		Thread.sleep(2000);

		// click On CLose view Link
		retailAppointmentPage.clickOnCLoseViewLink();

		// close the appointment

		retailAppointmentPage.closeAppointment(data.get("CustomerName"),data.get("MobileNumber"),data.get("ProductPitched"),
				data.get("JointCall"),data.get("CommunicationSent"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC13_Appointment Closed");
		Thread.sleep(5000);

		// logout
		loginpage.Logout();

	}

}
