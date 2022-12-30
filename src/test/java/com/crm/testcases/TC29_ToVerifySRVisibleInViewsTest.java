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
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC29_ToVerifySRVisibleInViewsTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	DetailsPage detailspage;

	CommonMethods commonmethods;
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifySRVisibleInViews(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifySRVisibleInViews";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifySRVisibleInViews", sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifySRVisibleInViews".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;

		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));

		homepage = new HomePage(driver);

		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);

		// click On Standard WorkFLow Link
		homepage.clickOnStandardWorkFlow();
		Thread.sleep(2000);

		registrationPage = new RegistrationPage(driver);

		// create Service Request
		registrationPage.createSRStandardWorkFLow(data.get("LodgementUserTeam"), data.get("Product"),
				data.get("AccountCardDetailSearchParameter"), data.get("Function"), data.get("SubFunction"),
				data.get("SubSubFunction"), data.get("NatureofQuery"), data.get("LodgementRemarks"), sheetname,
				rows.get(iterationCount-1));

		detailspage = new DetailsPage(driver);

		String actualSrnumber = detailspage.getSRNumber();

		// logout
		loginpage.Logout();

		Thread.sleep(3000);

		// Login to admin page
		loginpage.Login(data.get("LocAdminUserName"), data.get("Password"));
		CommonMethods.scrollDown(400);

		// click on views arrow button
		homepage.clickviewsArrowButton();

		// verify SR Is visible in Views
		homepage.verifySRVisibleInViews(actualSrnumber);
		ScreenShot.screenshot("TC29_SR Visible in Views ");
		// logout
		loginpage.Logout();

	}
}
