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

public class TC44_ToVerifyReportsAddedToQuickLinksTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	ReportsPage reportspage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyReportsAddedToQuicklinks(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyReportsAddedToQuicklinks";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyReportsAddedToQuicklinks",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyReportsAddedToQuicklinks".toUpperCase()
					+ "as the Run mode is NO");

		}

		iterationCount++;

		loginpage = new LoginPage(driver);

		// Login to dpdis supervisor role
		loginpage.Login(data.get("UserName"), data.get("Password"));

		homepage = new HomePage(driver);

		// Select Administrator Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);

		CommonMethods.scrollDown(100);
		Thread.sleep(5000);
		// move to reports tab
		homepage.moveToReportsTab();
		Thread.sleep(1000);
		// click on Reports Link
		homepage.clickReportslink();

		reportspage = new ReportsPage(driver);
		Thread.sleep(2000);

		// verify reports open properly
		reportspage.verifyreportsaddedtoquicklinks();

		// logout
		loginpage.Logout();
	}

}
