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


public class TC10_ToVerifyPaginationAndOrderByOnAppointmentListingTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	
	CommonMethods commonmethods;
	RetailAppointmentPage retailAppointmentPage;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyPaginationOnApppointment(Hashtable<String, String> data) throws Exception {
		
		// Excel sheet path
		String sheetname = "verifyPaginationOnApppointment";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyPaginationOnApppointment",sheetname))) {
			  
			  throw new SkipException("Skipping the test " + "verifyPaginationOnApppointment".toUpperCase() + "as the Run mode is NO"); 
			  
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

		
		
		// CLick On Activities Link
		homepage.clickOnActivitiesLinkRole();

		Thread.sleep(2000);
		
		retailAppointmentPage = new RetailAppointmentPage(driver);
		
		// click on Arrow button Under Views
		retailAppointmentPage.clickOnArrowButton();
		Thread.sleep(4000);
		//click Sort Due Date to recently created(descending sorter)
		
		retailAppointmentPage.clickDueDateViews();
		//Verfiy Activity ID Column working by order bY
		retailAppointmentPage.verifyOrderBy();
		ScreenShot.takeScreenShot("TC10_ActivityPagination");		
		//Verfiy Pagination Dropdown Values
		retailAppointmentPage.verifyPaginationDropDownValues();
		
		Thread.sleep(5000);
		
		//verify Pagination Go to textBox
		retailAppointmentPage.verifypaginationGoToTextBox();
		Thread.sleep(5000);
		
		
		//logout
	     loginpage.Logout();


	}
}