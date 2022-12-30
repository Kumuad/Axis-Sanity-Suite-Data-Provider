package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CustomerPage;
import com.crm.pages.CustomerSearchPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.OffersAndLeadPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC02_ToverifyLeadIsCreatedOnAccountTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	CustomerPage customerPage;
	OffersAndLeadPage offersAndLeadPage;
	CustomerSearchPage customerSearchPage;
	
	CommonMethods commonmethods;

	public static int iterationCount = 0;
	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")


	public void verifyLeadIsCreatedOnAccount(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyLeadIsCreatedOnAccount";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyLeadIsCreatedOnAccount",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyLeadIsCreatedOnAccount".toUpperCase() + "as the Run mode is NO");
		}

		
		iterationCount++;

		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"),data.get("Password"));
		Thread.sleep(3000);
		homepage = new HomePage(driver);

		// Select Administrator Role
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
		
		Thread.sleep(2000);
	
		// Click On Recent Items
		//homepage.clickOnRecentItems();
		Thread.sleep(2000);

		customerPage = new CustomerPage(driver);

		// click on Toggle Button on Details Page
		customerPage.clickToggleButton();
		Thread.sleep(2000);

		// click On Offers And Lead tab
		customerPage.clickoffersandLeadTab();

		CommonMethods.scrollDown(400);

		offersAndLeadPage = new OffersAndLeadPage(driver);

		// Create Lead on Existing Customer
		offersAndLeadPage.createNewLead1(data.get("Product"),data.get("Subproduct"),data.get("Branch"),data.get("LeadSource"),
				data.get("Description"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(5000);
		ScreenShot.takeScreenShot("TC02_LeadCreation On Account");
		// logout
		loginpage.Logout();

	}
}
