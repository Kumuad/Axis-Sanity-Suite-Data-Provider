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

public class TC30_ToVerifySRSearchTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifySRSearch(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifySRSearch";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifySRSearch", sheetname))) {

			throw new SkipException("Skipping the test " + "verifySRSearch".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;

		loginpage = new LoginPage(driver);

		// Login to admin page with ccpho role
		loginpage.Login(data.get("UserName"), data.get("Password"));

		homepage = new HomePage(driver);

		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(3000);

		// click On CMS Flow from layout
		homepage.clickOnCMSFlow();

		Thread.sleep(2000);
		registrationPage = new RegistrationPage(driver);
		
		// create SR For CMS process
		String SRNumber=registrationPage.createSRCMSFlow(data.get("Product"),data.get("Function"),data.get("SubFunction"),
				data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("BriefDescription"),
				data.get("ProductType"),data.get("PickUpFrequency"),data.get("PickUpAddress"),sheetname,rows.get(iterationCount-1));

		// move to Quick Link
		homepage.clickOnBackHomeTab();

		Thread.sleep(2000);

		// Verify SR Search by SR Number
		homepage.verifySRSearchBySRNumber(SRNumber);

		driver.navigate().back();
		Thread.sleep(2000);

		// verify SR search by client code
		homepage.verifySRSearchByClientID(data.get("ClientCode"));
		driver.navigate().back();
		Thread.sleep(2000);

		// verify sr search by email
		homepage.verifySRSearchByEmail(data.get("CommunicationEmailId"));
		driver.navigate().back();
		Thread.sleep(2000);

		// verify sr search by cif id
		homepage.verifySRSearchByCIFID(data.get("CIFID"));
		driver.navigate().back();
		Thread.sleep(2000);

		// verify sr search by corporate name
		homepage.verifySRSearchByCorporateName(data.get("CorporateName"));
		ScreenShot.screenshot("TC30_SR Search");
		driver.navigate().back();
		Thread.sleep(2000);

		// logout
		loginpage.Logout();

	}
}