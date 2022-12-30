package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CalendarPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC35_ToVerifyCalendarAppointmentCreationTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RetailAppointmentPage retailAppointmentPage;
     CalendarPage calendarPage;   
	
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void VerifyCalendarAppointment(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "VerifyCalendarAppointment";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("VerifyCalendarAppointment",sheetname))) {

			throw new SkipException("Skipping the test "
					+ "VerifyCalendarAppointment".toUpperCase() + "as the Run mode is NO");

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
		
		
		//click on calendar link
		homepage.clickOnCalendarLink();
		Thread.sleep(2000);
		calendarPage = new CalendarPage(driver);
		
		//click Calendar Button
		calendarPage.clickCalendarButton();
		Thread.sleep(2000);
		
		//select Month from dropdown
		calendarPage.clickMonthButton();
		Thread.sleep(2000);
		
		//create appointment on Calendar 
		calendarPage.createappointmentOnCalendar(data.get("Reason"),data.get("ClientSegmentCode"),sheetname,rows.get(iterationCount-1));
		
		String subject=calendarPage.getAppointmentSubject(sheetname);
		Thread.sleep(5000);
		
		//verify created Appointment 
		calendarPage.verifycreatedAppointmentOnCalendar(subject);
		
		ScreenShot.takeScreenShot("TC35_Appointment CreationOnCalendar");
		//logout
		loginpage.Logout();
}
}