package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.OffersAndLeadPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC25_ToverifyOfferCreationTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	OffersAndLeadPage offersAndLeadPage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyOfferCreation(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyOfferCreation";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyOfferCreation",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyOfferCreation".toUpperCase() + "as the Run mode is NO");

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

		// Move to sales Tab
		homepage.moveToMarketingTab();
		Thread.sleep(2000);

		// click On Offers Link
		homepage.clickOnOffersLink();

		// click On New Button
		homepage.clickOnNewButton();

		Thread.sleep(1000);

		// click On Offer Link Layout
		homepage.clickOfferLinkLayout();
		Thread.sleep(2000);

		offersAndLeadPage = new OffersAndLeadPage(driver);

		// create offer
		offersAndLeadPage.createOffer(data.get("Priority"),data.get("Product"),data.get("SubProduct"),sheetname,rows.get(iterationCount-1));
		ScreenShot.takeScreenShot("TC25_Createoffer");
		Thread.sleep(1000);
		// logout
		loginpage.Logout();

	}
}
