package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC12_ToVerfiyCategoryViewAppointmentTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RetailAppointmentPage retailAppointmentPage;

	CommonMethods commonmethods;
	
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	
	public void verfiyCategoryViewAppointment(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verfiyCategoryViewAppointment";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verfiyCategoryViewAppointment",sheetname))) {

			throw new SkipException("Skipping the test "
					+ "verfiyCategoryViewAppointment".toUpperCase() + "as the Run mode is NO");

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
		
		// Move to Home Tab
		//homepage.moveToHomeTab();

		Thread.sleep(3000);
		
		// CLick On Activities Link
		homepage.clickOnActivitiesLink();
		Thread.sleep(3000);
		
		
		retailAppointmentPage = new RetailAppointmentPage(driver);
		
		String appointmentsubject=retailAppointmentPage.getAppointmentSubject(sheetname);
		
		
		retailAppointmentPage.verifyMyActivitiesView(appointmentsubject);
		ScreenShot.takeScreenShot("TC12_CategoryViewAppointment");
		// click On New Button
		//homepage.clickOnNewButton();

		Thread.sleep(2000);
		// click On Retail Appointment link
		//homepage.clickOnRetailAppointmentLink();
		Thread.sleep(2000);
		
		
	
		//logout
		loginpage.Logout();

		
		
		
}
}