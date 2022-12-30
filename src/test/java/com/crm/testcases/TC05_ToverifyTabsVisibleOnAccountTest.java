package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CustomerPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.OffersAndLeadPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;




public class TC05_ToverifyTabsVisibleOnAccountTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	CustomerPage customerPage;
	OffersAndLeadPage offersAndLeadPage;
	
	CommonMethods commonmethods;

	public static int iterationCount = 0;
	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyTabsVisibleOnAccount(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyActivityCreationOnAccount";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyTabsVisibleOnAccount",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyTabsVisibleOnAccount".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;
		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"),data.get("Password"));
		Thread.sleep(2000);

		log.info("Logged in to  Home Page");
		
		homepage = new HomePage(driver);
		
		// Select Administrator Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);

		Thread.sleep(2000);

		// Click On Recent Items
		homepage.clickOnRecentItems();
		Thread.sleep(2000);
		
		customerPage = new CustomerPage(driver);
		
		// click on Toggle Button on Details Page
		customerPage.clickToggleButton();
		Thread.sleep(2000);

	// verify Tab visible on Customer
		customerPage.verifyTabsVisibleOnCustomer();
		ScreenShot.takeScreenShot("TC_05Tabs Visible On Customer");
		// logout
		loginpage.Logout();

	}

}
