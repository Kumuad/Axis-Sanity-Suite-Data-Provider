package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.OffersAndLeadPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC26_ToVerifyonEditOffersAlldataDisplayInEditPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	OffersAndLeadPage offersAndLeadPage;
	
	

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	public void VerifyEditOnOffers(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "VerifyEditOnOffers";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);

		if (!(CommonMethods.isTestRunnable("VerifyEditOnOffers",sheetname))) {
			  
			  throw new SkipException("Skipping the test " + "VerifyEditOnOffers".toUpperCase() + "as the Run mode is NO"); 
			  
			  }
		
		iterationCount++;
		
		loginpage = new LoginPage(driver);
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));

		homepage = new HomePage(driver);
		
		
		// Select Administrator Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);
		
		// Move to sales Tab
		homepage.moveToMarketingTab();
		Thread.sleep(2000);
				
		//click On Offers Link
		homepage.clickOnOffersLink();
		
		//Thread.sleep(2000);
		// click On New Button
		homepage.clickOnNewButton();

		Thread.sleep(1000);

		// click On Offer Link Layout
		homepage.clickOfferLinkLayout();
		Thread.sleep(3000);
		offersAndLeadPage = new OffersAndLeadPage(driver);

		//create offer
		offersAndLeadPage.createOffer(data.get("Priority"),data.get("Product"),data.get("SubProduct"),sheetname,rows.get(iterationCount-1));
		
		//Click on Home bread crumb
		offersAndLeadPage.clickHome();
		
		Thread.sleep(3000);
		//click On recently Accessed Offer on Home Page
		homepage.clickOnRecentlyAccessedOffer();
		Thread.sleep(3000);
		
		offersAndLeadPage = new OffersAndLeadPage(driver);
		//click On Edit Offer Button
		offersAndLeadPage.clickEditOfferButton();
		
		Thread.sleep(3000);
		offersAndLeadPage.verifyEditOfferPage(data.get("Priority"),data.get("Product"),data.get("SubProduct"));
		
		ScreenShot.takeScreenShot("TC26_Editoffer");
		//logout
		loginpage.Logout();

	}
}
