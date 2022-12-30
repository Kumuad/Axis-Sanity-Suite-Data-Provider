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




public class TC06_CustomerSearchTest extends TestBase {
	

	LoginPage loginpage;
	HomePage homepage;
	CustomerPage customerPage;
	OffersAndLeadPage offersAndLeadPage;
	CustomerSearchPage customerSearchPage;
	
	
	public static int iterationCount = 0;
	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")


	public void verifyCustomerSearch(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyCustomerSearch";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyCustomerSearch",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyCustomerSearch".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;

		
		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"),data.get("Password"));
		Thread.sleep(2000);

		homepage = new HomePage(driver);
		
		// Select BRO Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);

		// move to Quick Link
		homepage.moveToQuickLink();
		Thread.sleep(2000);
		
		// click On Customer Search Tab under quick Links
		homepage.clickOnCustomerSearchTab();

		customerSearchPage = new CustomerSearchPage(driver);
		
		// verify Customer Search By Customer ID
		 customerSearchPage.verifyCustomerSearchByCustomerID(data.get("CustomerID") );
		 ScreenShot.takeScreenShot("TC06 Customer SearchBy CustomerID ");
		driver.navigate().back();
		Thread.sleep(3000);

		// verify Customer Search By Mobile Phone
	    customerSearchPage.verifyCustomerSearchByMobilePhone(data.get("Mobile"),sheetname,(iterationCount));
	    ScreenShot.takeScreenShot("TC06 Customer SearchBy Mobile ");
		
		driver.navigate().back();
		Thread.sleep(3000);

		// verify Customer Search By Email ID
		customerSearchPage.verifyCustomerSearchByEmail(data.get("Email"),sheetname,(iterationCount));
		 ScreenShot.takeScreenShot("TC06 Customer SearchBy Email ID ");
		driver.navigate().back();
		Thread.sleep(3000);

		// verify Customer Search By PAN Number
		customerSearchPage.verifyCustomerSearchByPANNumber(data.get("PAN"),sheetname,(iterationCount));
		 ScreenShot.takeScreenShot("TC06 Customer SearchBy PAN ");
		driver.navigate().back();
		Thread.sleep(5000);
		
		driver.navigate().back();
		Thread.sleep(3000);
		//logout
		loginpage.Logout();
	}

}
