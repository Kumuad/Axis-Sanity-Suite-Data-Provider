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

public class TC39_ToverifyCustomActionButtonTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	DetailsPage detailspage;

	CommonMethods commonmethods;
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyCustomActionButton(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyCustomActionButton";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyCustomActionButton",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "TO verify Custom Action Button".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
		loginpage = new LoginPage(driver);

//		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);
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
//		// logout
		loginpage.Logout();
		Thread.sleep(2000);
		// login with loc admin
		loginpage.Login(data.get("LocAdminUserName"),data.get("Password"));
		Thread.sleep(2000);

		// homepage = new HomePage(driver);
		// select values from views
		homepage.selectQueryViewsdropdown("Mumbai Location Admin All SRs");

		// select status code values from dropdown
		homepage.selectStatuscodeViewsdropdown("Registration");

		// click On Views Arrow Button
		homepage.clickviewsArrowButton();

		Thread.sleep(3000);
		// click on view SR number check box in views
		homepage.clickviewsSRNumbercheckBox();

		// click On Self Assign Custom Button
		homepage.clickSelfAssignMakerCustomButton();

		// verify custom action button assigned message
		homepage.verifycustomactionbuttonassigned();

		// click on OK button
		homepage.clickOnOkButtonAfterselfAssign();
		Thread.sleep(3000);

		// logout
		loginpage.Logout();

		Thread.sleep(2000);

		// login with loc admin
		loginpage.Login(data.get("MakerUserName"),data.get("Password"));
		Thread.sleep(2000);
		// select values from views
		homepage.selectQueryViewsdropdown("Assigned To me - All SR");

		// select status code values from dropdown
		homepage.selectStatuscodeViewsdropdown("All");

		// click On Views Arrow Button
		homepage.clickviewsArrowButton();
		Thread.sleep(3000);

		// verify SR is assigned to maker user
		homepage.verifySRassigned(actualSrnumber);

		// logout
		loginpage.Logout();

	}
}