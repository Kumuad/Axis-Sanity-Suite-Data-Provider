package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.EmailComposePage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

import com.crm.pages.RegistrationPage;
import com.crm.utilities.CommonMethods;

public class TC45_VerfyEmailSentToExistingSRTest extends TestBase {

	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	EmailComposePage emailcomposepage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyEmailSentToExistingSR(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyEmailSentToExistingSR";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyEmailSentToExistingSR",sheetname))) {

			throw new SkipException("Skipping the test " + "verifyEmailSentToExistingSR".toUpperCase()
					+ "as the Run mode is NO");
		}

		iterationCount++;
		loginpage = new LoginPage(driver);
		Thread.sleep(2000);
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);

		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);
		
		// click On CMS layout
		homepage.clickOnCMSFlow();
		Thread.sleep(2000);
		registrationPage = new RegistrationPage(driver);

		// create Service Request
		registrationPage.createSRCMSFlow(data.get("Product"),data.get("Function"),data.get("SubFunction"),
				data.get("SubSubFunction"),data.get("NatureofQuery"),data.get("BriefDescription"),
				data.get("ProductType"),data.get("PickUpFrequency"),data.get("PickUpAddress"),sheetname,rows.get(iterationCount-1));
		Thread.sleep(2000);

		// Create new object for emaicompose page

		emailcomposepage = new EmailComposePage(driver);

		// click on email compose tab

		emailcomposepage.clickOnEmailTab();
		Thread.sleep(2000);

		// click on email compose link
		emailcomposepage.clickOnComposeEmailLink();
		Thread.sleep(2000);

		// compose email switching window
		CommonMethods.switchToWindow();
		//emailcomposepage.switchinganotherwindow();
		
		
		Thread.sleep(2000);
		// Enter To address
		emailcomposepage.enterToEmailAddress();
		Thread.sleep(2000);
		// Enter CC address
		emailcomposepage.enterCCEmailAddress(sheetname);
		Thread.sleep(2000);
		// Enter BCC address
		emailcomposepage.enterBCCemailAddress(sheetname);
		Thread.sleep(2000);

		CommonMethods.scrollDown(400);
		// Enter subject
		emailcomposepage.enterEmailSubject();
		Thread.sleep(2000);
		// Enter email body
		emailcomposepage.enterEmailBody();
		Thread.sleep(2000);

		
		//logout
 		loginpage.Logout();
	}
}
