package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.DetailsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC32_ToVerifySRHistoryPageTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	DetailsPage detailspage;
	RegistrationPage registrationPage;
	CommonMethods commonmethods;
	
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	
	public void verifySRHistoryPage(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifySRHistoryPage";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifySRHistoryPage",sheetname))) {

			throw new SkipException("Skipping the test "
					+ "verifySRHistoryPage".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
		
		
        loginpage = new LoginPage(driver);
      
       
		// Login to cce page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);
		
		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);
		
		// click On Standard WorkFLow Link
		homepage.clickOnStandardWorkFlow();
		Thread.sleep(2000);
		
		registrationPage = new RegistrationPage(driver);
		
		//create SR
		registrationPage.createStandardSRPhoneBaking(data.get("Product"),data.get("AccountCardDetailSearchParameter"),data.get("Function"),
			data.get("SubFunction")	,data.get("SubSubFunction"),data.get("NatureofQuery"),
			data.get("LodgementRemarks"),data.get("Preferrableaddress"),data.get("CardAccount"),
			data.get("STATEMENTNOTRECEIVED"),data.get("STATEMENTRECEIVEDLATE"),data.get("TRAVELLING"),
			data.get("CustomerID"),data.get("CustomerName"),data.get("RBLOCKCARDNO"),sheetname,rows.get(iterationCount-1));
		
		
		detailspage = new DetailsPage(driver);
		
		//click On History Tab
		detailspage.clickOnHistoryTab();
		
		
		Thread.sleep(2000);
		//verify Sr History details
		detailspage.verifySRHistoryDetails();
		Thread.sleep(2000);
		ScreenShot.takeScreenShot("TC32_SR History");
		// logout
		loginpage.Logout();

		
	}
}
