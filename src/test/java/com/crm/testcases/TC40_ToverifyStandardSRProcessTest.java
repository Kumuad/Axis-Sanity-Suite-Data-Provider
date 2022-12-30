package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.BranchHeadPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.pages.RegistrationPage;
import com.crm.pages.TeamExecutivesPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;

public class TC40_ToverifyStandardSRProcessTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RegistrationPage registrationPage;
	TeamExecutivesPage teamExecutivesPage;
	BranchHeadPage branchHeadPage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")


	public void verifyStandardSRProcess(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
		String sheetname = "verifyStandardSRProcess";
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyStandardSRProcess",sheetname))) {

			throw new SkipException(
					"Skipping the test " + "verifyStandardSRProcess".toUpperCase() + "as the Run mode is NO");

		}
		iterationCount++;
		loginpage = new LoginPage(driver);

		// Login TO cce ROle
		
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));

		homepage = new HomePage(driver);

		// click On New Button
		homepage.clickOnNewButton();
		Thread.sleep(2000);

		// click On Standard WorkFLow Link
		homepage.clickOnStandardWorkFlow();
		Thread.sleep(2000);

		registrationPage = new RegistrationPage(driver);

		// create SR
		registrationPage.createStandardSRPhoneBaking(data.get("Product"),data.get("AccountCardDetailSearchParameter"),data.get("Function"),
				data.get("SubFunction")	,data.get("SubSubFunction"),data.get("NatureofQuery"),
				data.get("LodgementRemarks"),data.get("Preferrableaddress"),data.get("CardAccount"),
				data.get("STATEMENTNOTRECEIVED"),data.get("STATEMENTRECEIVEDLATE"),data.get("TRAVELLING"),
				data.get("CustomerID"),data.get("CustomerName"),data.get("RBLOCKCARDNO"),sheetname,rows.get(iterationCount-1));

		// logout
		loginpage.Logout();
		Thread.sleep(2000);
		
		
		// Login to testteamexecutives role
		loginpage.Login(data.get("TeamExecutiveUserName"),data.get("Password"));
		Thread.sleep(3000);
		// CommonMethods.scrollDown(300);
		teamExecutivesPage = new TeamExecutivesPage(driver);
		// Select value from Assigned To Dropdown
		teamExecutivesPage.selectViewsAssignedTodropdown("Mumbai Location Admin All SRs");
		Thread.sleep(2000);
		// Select value from Status COde To Dropdown
		teamExecutivesPage.selectViewsStatusCodedropdown("All");
		Thread.sleep(2000);
		// CLick on Views Arrow Button
		teamExecutivesPage.clickviewsArrowButton();
		Thread.sleep(4000);
		// CLick on Views SR Number CheckBox
		teamExecutivesPage.clickviewsSRNumbercheckBox();
		Thread.sleep(3000);
		// click on self assign team executive Button
		teamExecutivesPage.clickSelfAssignTeamExecutiveCustomButton();

		Thread.sleep(3000);
		// Select value from Assigned To Dropdown
		teamExecutivesPage.selectViewsAssignedTodropdown("Assigned To me - All SR");
		Thread.sleep(4000);
		// Select value from Status COde To Dropdown
		teamExecutivesPage.selectViewsStatusCodedropdown("All");
		Thread.sleep(2000);
		// CLick on Views Arrow Button
		teamExecutivesPage.clickviewsArrowButton();
		Thread.sleep(3000);
		// click on views SR Number Link
		teamExecutivesPage.clickviewsSRNumberLink();
		Thread.sleep(3000);
		// click On Edit SR
		teamExecutivesPage.clickOnEditSR();

		Thread.sleep(3000);
		// sent sr to STO
		teamExecutivesPage.sentSRToSTO();
		Thread.sleep(4000);
		// verify actual and expected status code on Team Executive Details Page after
		// sent to STO
		String expstatuscodeafterSTO = "STO";
		teamExecutivesPage.verifyexpStatusCodeOnTeamExecutiveDetailsPage(expstatuscodeafterSTO);
		Thread.sleep(2000);

		// verify actual and expected assigned to on Team Executive Details Page after
		// sent to STO
		String expsassignedafterSTO = "Admin Axis";
		teamExecutivesPage.verifyexpAssignedToOnTeamExecutiveDetailsPage(expsassignedafterSTO);

		// ScreenShot.takeScreenShot("STO Details Page");
		// logout
		loginpage.Logout();

		// Login TO Branch Role
		
		loginpage.Login(data.get("BranchUserName"),data.get("Password"));

		branchHeadPage = new BranchHeadPage(driver);
		CommonMethods.scrollDown(900);
		Thread.sleep(4000);
		// Select value from dropdown from views for Category
		branchHeadPage.selectCategoryValuefromdropdown("Standard Workflow");
		Thread.sleep(2000);
		// click on views arrow button
		branchHeadPage.clickviewsArrowButton();
		Thread.sleep(3000);
		// click on views SR Number Link
		teamExecutivesPage.clickviewsSRNumberLink();
		Thread.sleep(3000);
		// click On Edit SR
		branchHeadPage.clickOnEditSR();
		Thread.sleep(3000);
		// Enter text in remark field
		branchHeadPage.enterremarks();

		// click On Save and proceed
		branchHeadPage.clickOnSaveandProceed();
		Thread.sleep(3000);
		// logout
		loginpage.Logout();
		Thread.sleep(3000);

		// Login TO Team Exceutive Role
		
		loginpage.Login(data.get("TeamExecutiveUserName"),data.get("Password"));
		Thread.sleep(3000);
		// teamExecutivesPage = new TeamExecutivesPage(driver);
		// Select value from Assigned To Dropdown
		teamExecutivesPage.selectViewsAssignedTodropdown("Assigned To me - All SR");
		Thread.sleep(2000);
		// Select value from Status COde To Dropdown
		teamExecutivesPage.selectViewsStatusCodedropdown("All");
		Thread.sleep(2000);
		// CLick on Views Arrow Button
		teamExecutivesPage.clickviewsArrowButton();
		Thread.sleep(3000);
		// click on views SR Number Link
		teamExecutivesPage.clickviewsSRNumberLink();
		Thread.sleep(3000);
		// click On Edit SR
		teamExecutivesPage.clickOnEditSR();
		Thread.sleep(3000);

		// click on closure button
		teamExecutivesPage.clickOnCLosureMilestone();

		// select sub status
		teamExecutivesPage.selectsubstatus("Approved and Closed");

		// enter remarks
		teamExecutivesPage.enterremarks();

		teamExecutivesPage.clickOnSaveandProceed();

		Thread.sleep(5000);
		// verify actual and expected status code on Team Executive Details Page after
		// sent to STO
		String expstatuscodeafterclosure = "Closed";
		teamExecutivesPage.verifyexpStatusCodeOnTeamExecutiveDetailsPage(expstatuscodeafterclosure);
		Thread.sleep(2000);

		// verify actual and expected assigned to on Team Executive Details Page after
		// sent to STO
		String expsassignedafterclosure = "TestTeamExecutives";
		teamExecutivesPage.verifyexpAssignedToOnTeamExecutiveDetailsPage(expsassignedafterclosure);
		ScreenShot.takeScreenShot("TC40_Standard Process SR CLosure");
		Thread.sleep(3000);
		// logout
		loginpage.Logout();

	}

}
