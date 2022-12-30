package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;
import com.crm.pages.ReportsPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC41_ToVerifyListOfReportsDisplayTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	ReportsPage reportspage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifylistofReportsDisplay(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifylistofReportsDisplay";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifylistofReportsDisplay",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifylistofReportsDisplay".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;
		loginpage = new LoginPage(driver);

		// Login to dpdis supervisor role
		loginpage.Login(data.get("UserName"), data.get("Password"));

		homepage = new HomePage(driver);

		// click on Reports Tab
		homepage.clickReportsTab();

		reportspage = new ReportsPage(driver);
		Thread.sleep(5000);
		// verify list of reports display
		reportspage.listofreportsdisplay();
		ScreenShot.takeScreenShot("TC41_List Of Reports");
		Thread.sleep(2000);
		// logout
		loginpage.Logout();
	}
}
