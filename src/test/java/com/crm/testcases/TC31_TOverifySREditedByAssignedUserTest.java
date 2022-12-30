package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.DetailsPage;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.MakerPage;
import com.crm.pages.RegistrationPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC31_TOverifySREditedByAssignedUserTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	DetailsPage detailspage;
	MakerPage makerpage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifySREditedByAssignedToUser(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifySREditedByAssignedToUser";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifySREditedByAssignedToUser",sheetname))) {

			throw new SkipException("Skipping the test " + "verifySREditedByAssignedToUser".toUpperCase()
					+ "as the Run mode is NO");

		}
		
		iterationCount++;
		// Excel sheet path

		
		
		loginpage = new LoginPage(driver);

		// Login to admin page
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
		registrationPage.createSRStandardWorkFLow(data.get("LodgementUserTeam"),data.get("Product"),data.get("AccountCardDetailSearchParameter"),
				data.get("Function"),data.get("SubFunction"),data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("LodgementRemarks"),sheetname,rows.get(iterationCount-1));

		// logout
		loginpage.Logout();
		Thread.sleep(2000);

		// login with loc admin user
		loginpage.Login(data.get("LocAdminUserName"), data.get("Password"));
		Thread.sleep(2000);
		// select values from views
		homepage.selectQueryViewsdropdown("Mumbai Location Admin All SRs");

		// select status code values from dropdown
		homepage.selectStatuscodeViewsdropdown("Registration");

		homepage.clickviewsArrowButton();

		Thread.sleep(3000);
		// click on view SR number check box in views
		homepage.clickviewsSRNumbercheckBox();

		// //click On Self Assign Custom Button
		homepage.clickSelfAssignMakerCustomButton();

		// click on OK button
		homepage.clickOnOkButtonAfterselfAssign();

		// logout
		loginpage.Logout();

		Thread.sleep(2000);
		// login
		loginpage.Login(data.get("MakerUserName"),data.get("Password"));
		Thread.sleep(3000);

		homepage = new HomePage(driver);

		// select values from views
		homepage.selectQueryViewsdropdown("Assigned To me - All SR");
		
		homepage.clickviewsArrowButton();
		Thread.sleep(2000);
		// click Sr in views
		homepage.clickviewsSRNumberLink();

		makerpage = new MakerPage(driver);
		// verify SR is assigned to maker user
		makerpage.clickOnEditSR();

		String actualurl = "https://axistest.crmnext.com/snAutomation/app/crmnextobject/edit/Case";
		String expurl = makerpage.getcurrentURL();

		// Verify Assigned User Able TO Edit SR
		makerpage.verifySREditedByAssignedUser(actualurl, expurl);
		ScreenShot.takeScreenShot("TC31_SR EditedByAssignedUser");
		// logout
		loginpage.Logout();

	}
}