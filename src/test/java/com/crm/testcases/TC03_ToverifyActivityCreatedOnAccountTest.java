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

import com.crm.pages.RetailAppointmentPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;



//@Listeners(CustomListener.class)
public class TC03_ToverifyActivityCreatedOnAccountTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	CustomerSearchPage customerSearchPage;
	CustomerPage customerPage;
	RetailAppointmentPage retailAppointmentPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;
	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyActivityCreationOnAccount(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyActivityCreationOnAccount";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyActivityCreationOnAccount",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyActivityCreationOnAccount".toUpperCase() + "as the Run mode is NO");
		}

		iterationCount++;

		loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login(data.get("UserName"),data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);

		Thread.sleep(2000);
		
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
		
		
		// Click On Recent Items
		//homepage.clickOnRecentItems();
		Thread.sleep(2000);

		customerPage = new CustomerPage(driver);
		
		// click on Toggle Button on Details Page
		customerPage.clickToggleButton();
		Thread.sleep(2000);

		// click On Activities and Interaction Tab on customer details Page
		customerPage.clickActivitiesAndInteractionTab();

		Thread.sleep(2000);
		// click On New Activity Tab
		customerPage.clickOnNewActivityTab();
		Thread.sleep(2000);

		retailAppointmentPage = new RetailAppointmentPage(driver);

		// create Activity/Appointment on existing Customer
		retailAppointmentPage.createAppointment1(data.get("AppointmentStatus"),data.get("CustomerType"),data.get("TypeOfCustomer"),
				data.get("TypeofContact"),data.get("Subject"),data.get("Details"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(2000);

		ScreenShot.takeScreenShot("TC03_Activity Created On Account");
		// logout
		loginpage.Logout();

	}

}
