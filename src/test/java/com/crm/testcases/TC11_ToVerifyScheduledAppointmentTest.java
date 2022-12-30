package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.CalendarPage;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC11_ToVerifyScheduledAppointmentTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RetailAppointmentPage retailAppointmentPage;
	CalendarPage calendarPage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyScheduledAppointment(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyScheduledAppointment";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyScheduledAppointment", sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyScheduledAppointment".toUpperCase() + "as the Run mode is NO");

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
		// homepage.moveToHomeTab();

		// click on calendar link
		homepage.clickOnCalendarLink();
		Thread.sleep(2000);

		calendarPage = new CalendarPage(driver);

		calendarPage.clickCalendarButton();
		Thread.sleep(2000);

		calendarPage.clickDayButton();
		Thread.sleep(2000);

		retailAppointmentPage = new RetailAppointmentPage(driver);
		String appointmentsubject = retailAppointmentPage.getAppointmentSubject(sheetname);

		calendarPage.verfiyCreatedAppointmentOnDay(appointmentsubject);

		ScreenShot.takeScreenShot("TC11_ScheduledAppointment Day");
		Thread.sleep(2000);

		Thread.sleep(2000);
		calendarPage.clickCalendarButton();

		Thread.sleep(2000);

		calendarPage.clickWeekButton();
		Thread.sleep(2000);

		calendarPage.verfiyCreatedAppointmentOnWeek(appointmentsubject);

		ScreenShot.takeScreenShot("TC11_ScheduledAppointment Week");
		// logout
		loginpage.Logout();
	}

}