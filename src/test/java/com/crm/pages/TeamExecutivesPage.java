package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.utilities.CommonMethods;

public class TeamExecutivesPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(TeamExecutivesPage.class);
	public TeamExecutivesPage(WebDriver driver) {
		this.driver = driver;

	}

	// Methods start here

	// Select value from dropdown from views for Assigned TO
	public void selectViewsAssignedTodropdown(String text) throws Exception {
		CommonMethods.scrollByVisibilityofElement("viewsAssignedTodropdown_XPATH");
		Thread.sleep(2000);
		CommonMethods.selectByText("viewsAssignedTodropdown_XPATH", text);

	}

	// Select value from dropdown from views for Status Code
	public void selectViewsStatusCodedropdown(String text) {
		CommonMethods.selectByText("viewsStatuscodedropdown_XPATH", text);

	}

	// click on views arrow button
	public void clickviewsArrowButton() {
		CommonMethods.Click("viewsArrowButton_XPATH");

	}

	// click on views SR Number Link
	public void clickviewsSRNumbercheckBox() throws IOException, Exception {
		//CommonMethods.Click("viewsSRNumbercheckBox_XPATH");
		CommonMethods.clickelementbyjavascript("viewsSRNumbercheckBox_XPATH");
	}

	// click on self assign team executive Button

	public void clickSelfAssignTeamExecutiveCustomButton() throws Exception {
		CommonMethods.scrollAtBottom();
		CommonMethods.highlightelement("selfassignteamexecutivebutton_XPATH");
		CommonMethods.Click("selfassignteamexecutivebutton_XPATH");
		Thread.sleep(2000);

		// CLick On Ok Button
		CommonMethods.Click("OkButtonSelAssign_XPATH");

	}

	// click on views SR Number Link
	public void clickviewsSRNumberLink() {

		CommonMethods.Click("viewsSRNumberLink_XPATH");

	}

	// click On Edit SR
	public void clickOnEditSR() {
		CommonMethods.Click("EditSRButtonTeamExecutive_XPATH");

	}

	// sent to STO
	public void sentSRToSTO() throws InterruptedException {
		CommonMethods.Click("TeamExecutiveSTO_XPATH");

		// click On Search Icon
		CommonMethods.Click("TeamExecutiveSTOReasonSearchIcon_XPATH");

		// Enter STO Reason
		CommonMethods.sendkeys("inputSearchTextBox_XPATH", "Others");
		// select Check Box
		CommonMethods.Click("TeamExecutiveSTOSearchcheckbox_XPATH");

		// click on OK Button
		CommonMethods.Click("TeamExecutiveSTOoKButton_XPATH");

		// Enter Remarks
		CommonMethods.sendkeys("TeamExecutiveSTORemarks_XPATH", "STO");
		// click On Save and proceed
		CommonMethods.Click("saveandProceedButton_XPATH");
        Thread.sleep(5000);
	}

	// verify actual and expected status code on Team Executive Details Page
	public void verifyexpStatusCodeOnTeamExecutiveDetailsPage(String expstatuscode) throws IOException, Exception {
		CommonMethods.expStatusCode("statuscodeMakerPage_XPATH", expstatuscode);

	}
	// verify actual and expected assigned to on Team Executive Details Page

	public void verifyexpAssignedToOnTeamExecutiveDetailsPage(String expsassignedto) throws IOException, Exception {
		CommonMethods.expAssignedTo("assignedToMakerPage_XPATH", expsassignedto);

	}

	// click on Closure Button
	public void clickOnCLosureMilestone() {
		CommonMethods.Click("CLosuremilestoneCheckerPage_XPATH");

	}

	// click on Closure Button
	public void selectsubstatus(String text) throws IOException, InterruptedException {
		CommonMethods.sendkeys("TeamExecutivesubstatustextbox_XPATH", text);
		CommonMethods.PickerSelect("pickerSelect_XPATH",text);

	}

	// Enter text in remark field
	public void enterremarks() throws Exception {
		CommonMethods.scrollByVisibilityofElement("TeamExecutiveSTORemarks_XPATH");
		CommonMethods.sendkeys("TeamExecutiveSTORemarks_XPATH", "test");
	}

	// click On Edit SR
	public void clickOnSaveandProceed() {

		CommonMethods.Click("saveandProceedButton_XPATH");

	}
}
