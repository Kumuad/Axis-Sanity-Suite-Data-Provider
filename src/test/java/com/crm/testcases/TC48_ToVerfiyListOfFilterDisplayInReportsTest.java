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
import com.crm.utilities.ScreenShot;

public class TC48_ToVerfiyListOfFilterDisplayInReportsTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	ReportsPage reportspage;
	DetailsPage detailspage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyListOfFiltersReports(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyListOfFiltersReports";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyListOfFiltersReports",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyListOfFiltersReports".toUpperCase() + "as the Run mode is NO");

		}
		
		// Excel sheet
		//String sheetname = "verifyListOfFiltersReports";
		//String sheetname1 = "supervisor_login";
		iterationCount++;
		
		loginpage = new LoginPage(driver);
		

		// Login to bro page
		loginpage.Login(data.get("UserName"), data.get("Password"));
	
		Thread.sleep(2000);
		homepage = new HomePage(driver);

		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);

		// click On DPDIS Process
		homepage.clickDPDISProcessFlow();

		registrationPage = new RegistrationPage(driver);

		// create SR for DPDIS process
		registrationPage.createDPDISServiceRequest(data.get("Function"),data.get("SubFunction"),data.get("SubSubFunction"),data.get("NatureOfQuery"),
				data.get("TypeofRequest"),data.get("SlipSeries"),data.get("DigitallySignedDocument"),
				data.get("SerialNo"),data.get("LodgementRemarks"),sheetname,rows.get(iterationCount-1));
		detailspage = new DetailsPage(driver);
		
		//get SR Number
		String srnumber=detailspage.getSRNumber();
				
		// logout
		loginpage.Logout();
		Thread.sleep(3000);

		// Login to admin page
		loginpage.Login(data.get("SupervisorUserName"), data.get("SupervisorPassword"));
		Thread.sleep(3000);
		
		homepage = new HomePage(driver);

		// click on Reports Tab
		homepage.clickReportsTab();
						
		reportspage = new ReportsPage(driver);
		
		//search DPDIS Report
		reportspage.searchDPDISReport();
		
		//select Created On filter and enter values
		reportspage.selectCreatedOnFilter();

		// select Internal Expected Closure Date On Filter and enter values
		reportspage.selectInternalExpectedCLosureDateFilter(sheetname,rows.get(iterationCount-1));
		
		// select SR Number Filter and enter values
		reportspage.selectSRNumberFilter(sheetname,rows.get(iterationCount-1));
		
		//select Status Code Filter and enter values
		reportspage.selectStatusCodeFilter(data.get("StatusCode"));
		
		//select Source Filter and enter values
		reportspage.selectSourceFilter(data.get("Source"));
		
		//select SUb SUb Function Filter and enter values
		reportspage.selectSubSubFunctionFilter(data.get("SubSubFunction"));
		
		//select Nature Of Query Filter enter values
		reportspage.selectNatureOfQueryFilter(data.get("NatureOfQuery"));
		
		//select AssignedToUser Filter
		reportspage.selectAssignedToUserFilter(data.get("AssignedToUser"));
		
		//click On Apply Button
		reportspage.clickOnApplyButton();
		
		//verify SR Number After applying filter and SR created is same
		reportspage.verifySRNumberFilter(srnumber);
		
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC48_Reports Filter");
		
		CommonMethods.switchtoparentwindow();
		
		Thread.sleep(2000);
		
				
		//logout
 		loginpage.Logout();		  
	}
}
