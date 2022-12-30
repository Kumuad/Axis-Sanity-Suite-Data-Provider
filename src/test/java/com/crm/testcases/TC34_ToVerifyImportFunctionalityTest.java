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


public class TC34_ToVerifyImportFunctionalityTest  extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	DetailsPage detailspage;

	CommonMethods commonmethods;
	
	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")
	
	public void verifyImportFunctionality(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyImportFunctionality";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyImportFunctionality",sheetname))) {

			throw new SkipException("Skipping the test "
					+ "verifyImportFunctionality".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
        
          
         loginpage = new LoginPage(driver);
  		
  		// Login to admin page with ccpho role
  		loginpage.Login(data.get("UserName"), data.get("Password"));
  		Thread.sleep(2000);
  		homepage = new HomePage(driver);
  		
  		
  		
  		// click On New Button
  		homepage.clickOnNewButton();
  		Thread.sleep(2000);
  		
  	  //click On CMS Flow from layout
  		homepage.clickOnCMSFlow();
 		
  		Thread.sleep(2000);
		registrationPage = new RegistrationPage(driver);
		
		
		//create SR For CMS process
		String SRNumber=registrationPage.createSRCMSFlow(data.get("Product"),data.get("Function"),data.get("SubFunction"),
				data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("BriefDescription"),
				data.get("ProductType"),data.get("PickUpFrequency"),data.get("PickUpAddress"),sheetname,rows.get(iterationCount-1));
		
		
		detailspage = new DetailsPage(driver);
		//String SRNumber=detailspage.getSRNumber();
		Thread.sleep(2000);
		CommonMethods.scrollAtBottom();
		//click On import Child Sr Button
		detailspage.clickOnImportChildSRButton();
		Thread.sleep(2000);
  		
		//detailspage file upload
		detailspage.uploadFileImport(SRNumber);
		
		Thread.sleep(2000);
		loginpage.Logout();
  		
  		}
}
