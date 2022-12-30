package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;

import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LeadSearchPage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC18_ToverifyDocumentAttachementOnLeadDetailsPageTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	LeadsPage leadspage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;
	LeadSearchPage leadsearchPage;
	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void verifyDocumentAttachementOnLead(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyDocumentAttachementOnLead";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyDocumentAttachementOnLead",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyDocumentAttachementOnLead".toUpperCase()
					+ "as the Run mode is NO");

		}

		iterationCount++;
		loginpage = new LoginPage(driver);

		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);

		// Select BRO Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);
		// click On Leads Link
		homepage.clickOnLeadsLink();

		Thread.sleep(2000);

		// click on Advanced search
		homepage.clickAdvancedSearch();

		leadsearchPage = new LeadSearchPage(driver);

		// Lead Search By Lead ID
		leadsearchPage.verifyLeadSearchByLeadID(data.get("LeadID"));

		leadspage = new LeadsPage(driver);

		Thread.sleep(2000);

		// click On Toggle Button
		leadspage.clickToggleButton();

		// click On attachments Tab on Leads Details Page
		leadspage.clickOnAttachementstab();
		Thread.sleep(2000);

		// click On New Attachments Link
		leadspage.clickOnNewAttachementsLink();
		Thread.sleep(3000);

		// verify Document Attached
		leadspage.verifyDocumentsAttachedOnDetailsPage();
		ScreenShot.takeScreenShot("TC18_Documents attached On Lead");

		// logout
		loginpage.Logout();

//		// click On Leads Link
//		homepage.clickOnLeadsLink();
//
//		leadspage = new LeadsPage(driver);
//
//		Thread.sleep(2000);
//
//		// click on New Button
//		leadspage.clickOnNewButton();
//		Thread.sleep(2000);
//
//		// click On Lead Layout
//		homepage.clickLeadLayout();
//
//		// create New Lead
//		leadspage.createLead1(sheetname);
//		
//		Thread.sleep(3000);
//		// Click On Recent Items
//		homepage.clickOnRecentItems();
//		

	}
}
