
package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC46_VerifyDataPresenterTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyDataPresenter(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyDataPresenter";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyDataPresenter",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "To Verfiy Data Presenter".toUpperCase() + "as the Run mode is NO");
		}

		iterationCount++;
		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);

		homepage = new HomePage(driver);

		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);

		// click On CMS layout
		homepage.clickOnCMSFlow();
		Thread.sleep(2000);
		registrationPage = new RegistrationPage(driver);
		
		// create Service Request
		registrationPage.createCMSSRForEDS(data.get("Product"),data.get("Function"),data.get("SubFunction"),
				data.get("SubSubFunction"),data.get("Natureofquery"),data.get("BriefQueryComplaintDescription"),
				data.get("ClientCode"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(5000);

		// Select product type
		registrationPage.selectProductFields(data.get("ProductType"));

		Thread.sleep(3000);

		// Select pickup frequency and pick up address
		registrationPage.selectPickupFrequency(data.get("PickupFrequency"));
		Thread.sleep(3000);

		// Select pickup address
		registrationPage.selectPickupAddress(data.get("PickUpAddress"));

		// create SR Through EDS Data Presenter
		registrationPage.createSREDS(sheetname,rows.get(iterationCount-1));
		// Take screenshot
		ScreenShot.takeScreenShot("TC46_SR Creation Data Presenter");
		// logout
		loginpage.Logout();

	}
}
