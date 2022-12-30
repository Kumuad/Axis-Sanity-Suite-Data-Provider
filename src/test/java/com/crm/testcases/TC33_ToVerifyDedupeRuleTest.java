package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC33_ToVerifyDedupeRuleTest extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	CommonMethods commonmethods;
	
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyDedupeRule(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyDedupeRule";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyDedupeRule",sheetname))) {

			throw new SkipException("Skipping the test "
					+ "verifyDedupeRule".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
          
         loginpage = new LoginPage(driver);
  		
  		// Login to admin page with ccpho role
  		loginpage.Login(data.get("UserName"), data.get("Password"));
  		
  		homepage = new HomePage(driver);
  		
  		// click On New Button
  		homepage.clickOnNewButton();
  		Thread.sleep(2000);
  		
  		//click On CMS Flow from layout
  		homepage.clickOnCMSFlow();
 		
  		Thread.sleep(2000);
		registrationPage = new RegistrationPage(driver);
		
		
		//create SR For CMS process
		registrationPage.createSRCMSFlow(data.get("Product"),data.get("Function"),data.get("SubFunction"),
				data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("BriefDescription"),
				data.get("ProductType"),data.get("PickUpFrequency"),data.get("PickUpAddress"),sheetname,rows.get(iterationCount-1));
		
		// click On New Button
  		homepage.clickOnNewButton();
  		Thread.sleep(2000);
  		
  		//click On CMS Flow from layout
  		homepage.clickOnCMSFlow();
  		
		//verify Dedupe Rule
		registrationPage.createSRDedupeRule(data.get("Product"),data.get("Function"),data.get("SubFunction"),
				data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("BriefDescription"),data.get("ClientCode"),
				data.get("ProductType"),data.get("PickUpFrequency"),data.get("PickUpAddress"),sheetname,rows.get(iterationCount-1));
	
		ScreenShot.takeScreenShot("TC33_Dedupe Rule");
		Thread.sleep(2000);
		loginpage.Logout();
}
}
