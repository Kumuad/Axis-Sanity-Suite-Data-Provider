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
import com.crm.pages.SummaryPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC47_ToVerifySummaryPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	DetailsPage detailspage;
    SummaryPage summarypage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifySummaryPage(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifySummaryPage";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifySummaryPage",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifySummaryPage".toUpperCase() + "as the Run mode is NO");

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

		// move to click On Summary Link
		homepage.clickOnSummaryLink();
		Thread.sleep(4000);
		
		summarypage = new SummaryPage(driver);
		//verify Lead ID On Summary PAge
		summarypage.verfiyleadIDOnSummaryPage(data.get("LeadID"));
		Thread.sleep(2000);
		
		//verify Appointment  On Summary PAge
		summarypage.verfiyAppointmentOnSummaryPage(data.get("Subject"));
		
		ScreenShot.takeScreenShot("TC47_SummaryPage");
		
		
		//logout
		loginpage.Logout();

	}
}
