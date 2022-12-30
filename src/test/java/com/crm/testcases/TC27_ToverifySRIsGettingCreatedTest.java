package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.pages.HomePage;

import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC27_ToverifySRIsGettingCreatedTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	
	CommonMethods commonmethods;
	
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	
	public void verifySRIsCreated(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifySRIsCreated";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifySRIsCreated",sheetname))) {

			throw new SkipException("Skipping the test "
					+ "To Verfiy SR Is Created".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
        
		
        loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		
		homepage = new HomePage(driver);
		
		// click On New Button
		homepage.clickOnNewButton();
		
		Thread.sleep(2000);
		//click On Standard WorkFLow Link
		homepage.clickOnStandardWorkFlow();
		Thread.sleep(2000);
		
		registrationPage = new RegistrationPage(driver);
		
		//create Service Request
		registrationPage.createSRStandardWorkFLow(data.get("LodgementUserTeam"),data.get("Product"),data.get("AccountCardDetailSearchParameter"),
				data.get("Function"),data.get("SubFunction"),data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("LodgementRemarks"),sheetname,rows.get(iterationCount-1));
		ScreenShot.takeScreenShot("TC27_SR Creation");
		
		//logout
		loginpage.Logout();
		

}
}