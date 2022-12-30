package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;
//import org.testng.annotations.Test;
import com.crm.base.TestBase;
import com.crm.pages.CustomerPage;
import com.crm.pages.CustomerSearchPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.OffersAndLeadPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;



public class TC04_ToverifyNewOfferCreatedOnAccountTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	CustomerPage customerPage;
	OffersAndLeadPage offersAndLeadPage;
	CustomerSearchPage customerSearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;
	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyNewOfferCreatedOnAccount(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyActivityCreationOnAccount";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyNewOfferCreatedOnAccount",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyNewOfferCreatedOnAccount".toUpperCase()
					+ "as the Run mode is NO");

		}

		iterationCount++;
		
		loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login(data.get("UserName"),data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);
		
		// Select  Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);

		// move to Quick Link
		homepage.moveToQuickLink();
		Thread.sleep(2000);

		// click On Customer Search Tab under quick Links
		homepage.clickOnCustomerSearchTab();

		customerSearchPage = new CustomerSearchPage(driver);

		// verify Customer Search By Customer ID
		customerSearchPage.verifyCustomerSearchByCustomerID(data.get("CustomerID"));

		// Click On Recent Items
		//homepage.clickOnRecentItems();
		Thread.sleep(2000);
		
		customerPage = new CustomerPage(driver);
		
		// click on Toggle Button on Details Page
		customerPage.clickToggleButton();
		Thread.sleep(2000);

		// click On Offers And Lead tab
		customerPage.clickoffersandLeadTab();

		Thread.sleep(5000);
		
		offersAndLeadPage = new OffersAndLeadPage(driver);
		
		// Create New offer On Existing Customer
		offersAndLeadPage.createNewOffer1(data.get("SubProduct"),data.get("HotRating"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC04_Offer Created On Account");
		// logout
		loginpage.Logout();

	}
}
