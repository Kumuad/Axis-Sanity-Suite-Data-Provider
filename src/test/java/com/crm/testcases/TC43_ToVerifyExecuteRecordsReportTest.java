package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.DetailsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;
import com.crm.pages.ReportsPage;
import com.crm.utilities.CommonMethods;



public class TC43_ToVerifyExecuteRecordsReportTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	DetailsPage detailspage;
	ReportsPage reportspage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")


	public void verifyExecuteRecordsReport(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyExecuteRecordsReport";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyExecuteRecordsReport",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "TO verify Execute All Records Reports".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
		loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		
		
		Thread.sleep(2000);
		homepage = new HomePage(driver);
		
		//move to service request tab
		//homepage.moveToServiceRequestTab();
		
	   //click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);
		
	   //click On DPDIS Process
		homepage.clickDPDISProcessFlow();
		
		registrationPage = new RegistrationPage(driver);
		
		//create SR for DPDIS process
		registrationPage.createDPDISServiceRequest(data.get("Function"),data.get("SubFunction"),data.get("SubSubFunction"),data.get("NatureOfQuery"),
				data.get("TypeofRequest"),data.get("SlipSeries"),data.get("DigitallySignedDocument"),
				data.get("SerialNo"),data.get("LodgementRemarks"),sheetname,rows.get(iterationCount-1));
		
		detailspage = new DetailsPage(driver);
		
		//get SR Number
		String srnumber=detailspage.getSRNumber();
		
		//logout
 		loginpage.Logout();
 		Thread.sleep(2000);
 		
		// Login to dpdis supervisor role
		loginpage.Login(data.get("SupervisorUserName"),data.get("SupervisorPassword"));
		Thread.sleep(2000);
 		
		homepage = new HomePage(driver);

		// click on Reports Tab
		homepage.clickReportsTab();
		Thread.sleep(2000);
 		
		
		reportspage = new ReportsPage(driver);
		//search DPDIS Report
		reportspage.searchDPDISReport();
		Thread.sleep(2000);
		//verify reports open properly
		reportspage.verifyExecuteAllRecordReport(srnumber);
		
		
		Thread.sleep(2000);
		//logout
 		loginpage.Logout();
	}
}
