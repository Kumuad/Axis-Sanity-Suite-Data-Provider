package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CustomerPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;




public class TC01_ToverifyAccountIsCreatedTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	CustomerPage customerPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;
	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyAccountIsCreated(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyAccountIsCreated";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyAccountIsCreated",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verify Account Is Created".toUpperCase() + "as the Run mode is NO");

		}

		iterationCount++;
		loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login(data.get("UserName"),data.get("Password"));

		homepage = new HomePage(driver);

		// Select Administrator Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);

		// Move to sales Tab
		homepage.moveToSalesTab();
		Thread.sleep(2000);

		// click On customer Link in Sales Tab
		homepage.clickOnCustomerLink();

		customerPage = new CustomerPage(driver);

		// click on New Button
		customerPage.clickOnNewButton();

		// CLick On Customer Link under New Button
		customerPage.clickOnRetailCustomerLink();
		
		
		Thread.sleep(2000);

		// create customer/ account
		customerPage.createAccount1(data.get("CustomerName"),data.get("ShortName"),data.get("CustomerType"),data.get("Email"),data.get("Mobile"),
				data.get("Territory"),data.get("LeadSource"),data.get("PAN"),data.get("CustomerID"),data.get("Gender"),data.get("Title"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC01_Account Creation");
		
		// logout
		loginpage.Logout();
	}
}
