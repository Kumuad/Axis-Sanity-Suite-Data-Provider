package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


import com.crm.base.TestBase;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;



public class HomePage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(HomePage.class);

	

	// WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;

		
	}

	DetailsPage detailsPage = new DetailsPage(driver);
	

	

	public String verifyHomePageTitle() {
		return driver.getTitle();

	}

	// click on Offer Link Layout
	public void clickOfferLinkLayout() throws Exception {
		
		
		CommonMethods.highlightelement("offer_XPATH");
		CommonMethods.clickelementbyjavascript("offer_XPATH");
	}
	// click On ServiceRequest

	public void clickOnServiceRequest() throws Exception {
		
		
		
		CommonMethods.mouseHover("ServiceRequestTabIcon_Xpath");
		CommonMethods.highlightelement("StandardWorkFlow_Xpath");
		Thread.sleep(2000);
		CommonMethods.Click("StandardWorkFlow_Xpath");

	}

	// select roles
	public void selectrole(String Role) throws Exception {

		

		CommonMethods.Click("profileimage_XPATH");
		Thread.sleep(2000);
		
		CommonMethods.selectByText("rolesdropdown_XPATH", Role);
	}

	// Move to Quick Link tab
	public void moveToQuickLink() throws Exception {
	
		CommonMethods.mouseHover("quicklink_XPATH");
		CommonMethods.highlightelement("quicklink_XPATH");
	}

	// click On Lead Search Tab Under Quick Links

	public void clickOnLeadSearchTab() throws Exception {

		
		
		
		CommonMethods.highlightelement("leadSearchTab_XPATH");
		CommonMethods.Click("leadSearchTab_XPATH");

	}

	// click On Calendar Link
	public void clickOnCalendarLink() throws Exception {

		

		CommonMethods.highlightelement("calendarlinksidetab_XPATH");
		CommonMethods.Click("calendarlinksidetab_XPATH");

	}

	// click On Alerts Link
	public void clickOnAlertsLink() throws Exception {
		CommonMethods.highlightelement("alertslink_XPATH");
		CommonMethods.Click("alertslink_XPATH");

	}

	// click On Customer Search Tab Under Quick Links

	public void clickOnCustomerSearchTab() throws Exception {
	
		CommonMethods.highlightelement("customerSearchTab_XPATH");
		CommonMethods.Click("customerSearchTab_XPATH");

	}

	// click On Back Home

	public void clickOnBackHomeTab() throws Exception {
		
		CommonMethods.highlightelement("backHomeTab_XPATH");
		CommonMethods.Click("backHomeTab_XPATH");
		
	}

	// click On Home Bread crumb

	public void clickOnHomeTab() throws Exception {
		CommonMethods.highlightelement("HomeBreadcrumbTab_XPATH");
		CommonMethods.Click("HomeBreadcrumbTab_XPATH");
	}

	// Move to Sales tab
	public void moveToSalesTab() throws Exception {
		
		CommonMethods.mouseHover("salesTab_XPATH");
		CommonMethods.highlightelement("salesTab_XPATH");
	}

	// Move to Marketing Tab
	public void moveToMarketingTab() throws Exception {

		
		CommonMethods.mouseHover("marketingTab_XPATH");
		CommonMethods.highlightelement("marketingTab_XPATH");
		
	}

	// Move to Home tab
	public void moveToHomeTab() throws Exception {

		
		CommonMethods.mouseHover("homeTab_XPATH");
		CommonMethods.highlightelement("homeTab_XPATH");
		
	}

	// move to click On Customer Link under Sales Tab
	public void clickOnCustomerLink() throws Exception {

		

		CommonMethods.highlightelement("customerslink_XPATH");
		CommonMethods.Click("customerslink_XPATH");

	}

	// click On Leads Link

	public void clickOnLeadsLink() throws Exception {
		CommonMethods.scrollDown(200);
		CommonMethods.highlightelement("leadslink_XPATH");
		CommonMethods.Click("leadslink_XPATH");

	}

	// click on task link
	public void clickOnTaskLink() throws Exception {
		// CommonMethods.scrollDown(200);
		CommonMethods.highlightelement("tasklink_XPATH");
		CommonMethods.Click("tasklink_XPATH");

	}

	// click On Offers Link
	public void clickOnOffersLink() throws Exception {
		
		
		CommonMethods.highlightelement("offerslink_XPATH");
		CommonMethods.Click("offerslink_XPATH");
	}

	// Move to recent items and click on recent items
	public void clickOnRecentItems() throws Exception {

		

		CommonMethods.mouseHover("recentItems_XPATH");
		CommonMethods.highlightelement("recentItems_XPATH");
		Thread.sleep(2000);
		CommonMethods.highlightelement("recentfirstItems_XPATH");
		CommonMethods.Click("recentfirstItems_XPATH");
		//CommonMethods.clickelementbyjavascript("recentfirstItems_XPATH");
	}

	// move to click On Activities Link under Home Tab
	public void clickOnActivitiesLink() throws Exception {

		CommonMethods.highlightelement("activitieslink_XPATH");
		Thread.sleep(2000);
		CommonMethods.clickelementbyjavascript("activitieslink_XPATH");
		

	}

	// move to click On Activities Link
	public void clickOnActivitiesLinkRole() throws Exception {
		CommonMethods.highlightelement("activitieslink_XPATH");
		Thread.sleep(2000);

		CommonMethods.clickelementbyjavascript("activitieslink_XPATH");

	}

	// move to click On Summary Link
	public void clickOnSummaryLink() throws Exception {
		CommonMethods.highlightelement("SummaryLink_XPATH");
		Thread.sleep(2000);

		CommonMethods.clickelementbyjavascript("SummaryLink_XPATH");

	}
	// move to New Button

	// click On New Button On Customer Page
	public void clickOnNewButton() throws Exception {
		CommonMethods.mouseHover("newButton_XPATH");
		CommonMethods.highlightelement("newButton_XPATH");
		//CommonMethods.Click("newButton_XPATH");

	}

	// click on Lead layout
	public void clickLeadLayout() throws Exception {
		CommonMethods.highlightelement("leadslayout_XPATH");
		CommonMethods.Click("leadslayout_XPATH");
	}

	// click on retail Appointment
	public void clickOnRetailAppointmentLink() throws Exception {
		Thread.sleep(2000);
		CommonMethods.highlightelement("retailAppointmentlink_XPATH");

		Thread.sleep(1000);
		CommonMethods.Click("retailAppointmentlink_XPATH");

		
	}

	public void clickOnRecentlyAccessedOffer() throws Exception {
		
		
		CommonMethods.highlightelement("recentlyaccessedoffer_XPATH");


		CommonMethods.Click("recentlyaccessedoffer_XPATH");
		
	}

	public void enterSRNumberInSearchTextBox(String servicerequest_sheetname) throws IOException {

		CommonMethods.sendkeys("SRNumberSearchTextBox_XPATH", servicerequest_sheetname, "SRNumber", 1);
		String SRNumbertext = excel.getCellData(servicerequest_sheetname, "SRNumber", 1);
		CommonMethods.Click("FetchButton_XPATH");
		// return SRNumbertext;
	}

	// *******************Standard WorkFLow***************************
	
	// select Standard WorkFlow Process from layout
	public void clickOnStandardWorkFlow() throws Exception {
		
		CommonMethods.highlightelement("srcreationStandardWorkFLow_XPATH");
		CommonMethods.clickelementbyjavascript("srcreationStandardWorkFLow_XPATH");
	}

	// select dpdis workflow
	public void clickDPDISProcessFlow() throws Exception {
		CommonMethods.highlightelement("DPDISWorkflow_Xpath");
		CommonMethods.clickelementbyjavascript("DPDISWorkflow_Xpath");
		// CommonMethods.Click("DPDISWorkflow_Xpath");
	}

	// select Views Dropdown
	public void selectQueryViewsdropdown(String text) {
		
		
		CommonMethods.selectByText("QueryViewDropdown_XPATH", text);

	}

	// select status code views dropdown
	public void selectStatuscodeViewsdropdown(String text) {
		
		CommonMethods.selectByText("StatusViewDropdown_XPATH", text);
	}

	// click on views arrow button
	public void clickviewsArrowButton() {
		CommonMethods.scrollDown(500);
		CommonMethods.Click("viewsArrowButton_XPATH");
		
	}

	// click on views SR Number Link
	public void clickviewsSRNumberLink() throws Exception {
		
		CommonMethods.highlightelement("viewsSRNumber_XPATH");
		CommonMethods.Click("viewsSRNumber_XPATH");

	}

	// click on views SR Number checkbox
	public void clickviewsSRNumbercheckBox() throws InterruptedException {

		try {
			CommonMethods.Click("viewsSRNumbercheckBox_XPATH");
			Thread.sleep(2000);
		} catch (StaleElementReferenceException e) {
			System.out.println(e.getMessage());
		}

	}

	// click on SelfAssignMakerCustomButton

	public void clickSelfAssignMakerCustomButton() throws Exception {
		CommonMethods.highlightelement("SelfAssignMakerCustomButton_XPATH");
		CommonMethods.Click("SelfAssignMakerCustomButton_XPATH");
		
		Thread.sleep(2000);
		CommonMethods.Click("SelfAssignMakersearchButton_XPATH");
		Thread.sleep(2000);
		// select username from searchdropdown
		CommonMethods.selectByText("MakerassignSearchDropdown_XPATH", "User Name");
		
		String makeruser = "Mr. SWP Maker1";
		CommonMethods.sendkeys("inputSearchTextBox_XPATH", makeruser);
		Thread.sleep(2000);
		CommonMethods.Click("filterarrowbuttoncustom_XPATH");
		
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + makeruser + "')]"));
		CommonMethods.clickelementbyjavascript(element);
		//element.click();
		Thread.sleep(2000);
		
		CommonMethods.Click("updatebutton_XPATH");
		Thread.sleep(2000);
		
	}

	// verify maker assign msg for custom button
	public void verifycustomactionbuttonassigned() throws IOException, InterruptedException {
		
		Thread.sleep(4000);
		CommonMethods.isElementDisplayed("customactionsucessmsg_XPATH");
		ScreenShot.takeScreenShot("TC39_Custom Action Button");
	}

	// verify SR Number in Views
	public void verifySRassigned(String actualSrnumber) throws Exception {
		String expsrnumbertext = CommonMethods.getElementText("srnumbertextviews_XPATH");
		Assert.assertEquals(actualSrnumber, expsrnumbertext, "SR mismatched");

	}

	// click On Ok Button After self Assign
	public void clickOnOkButtonAfterselfAssign() throws Exception {
		
		
		CommonMethods.highlightelement("OkButtonSelAssign_XPATH");
		CommonMethods.Click("OkButtonSelAssign_XPATH");

	}

	// **************************CMS Process********************

	// select Standard WorkFlow Process from layout
	public void clickOnCMSFlow() throws Exception {
		
		
	  CommonMethods.highlightelement("srcreationCMSFLow_XPATH");
	     CommonMethods.Click("srcreationCMSFLow_XPATH");

	}

	// enter search By SR Number
	public void searchSRNumber(String sheetname) {
		CommonMethods.sendkeys("srSearchTextBox_XPATH", sheetname, "SRNumber", 1);
		CommonMethods.Click("FetchButton_XPATH");
	}

	public void verifySRSearchBySRNumber(String SRNumber) throws Exception {

		
		CommonMethods.sendkeys("srSearchTextBox_XPATH", SRNumber);
		
		CommonMethods.Click("FetchButton_XPATH");
		Thread.sleep(2000);
		//String actualsrnumber = excel.getCellData(sheetname, "SRNumber", row);

	    String expSRNumber = detailsPage.getSRNumber();
		
		
		Assert.assertEquals(expSRNumber, SRNumber, "SR Search Mismatched");
		

	}

	// verify SR Search BY CLient ID

	public void verifySRSearchByClientID(String ClientCode) throws Exception {
		
		//String actualclientcode = excel.getCellData(sheetname, "ClientCode", row);
		CommonMethods.sendkeys("clientcodeSearchTextBox_XPATH", ClientCode);
		CommonMethods.Click("FetchButton_XPATH");
		
		Thread.sleep(2000);

		String expclientcode = detailsPage.getClientCode();
		Assert.assertEquals(ClientCode, expclientcode, "SR Search By client ID  Mismatched");
	}

	// verify SR Search By Email

	public void verifySRSearchByEmail(String CommunicationEmailId) throws Exception {
		
		//String actualemailid = excel.getCellData(sheetname, "CommunicationEmailId", row);
		CommonMethods.sendkeys("emailIDSearchTextBox_XPATH", CommunicationEmailId);
		CommonMethods.Click("FetchButton_XPATH");
		
		Thread.sleep(2000);


		String expemailid = detailsPage.getEmailID();
		Assert.assertEquals(CommunicationEmailId, expemailid, "SR Search By Email ID  Mismatched");
	}

	// verify SR Search By CIF ID

	public void verifySRSearchByCIFID(String CIFID) throws Exception {
		
		//String actualcifid =excel.getCellData(sheetname, "CIFID", row);
		CommonMethods.sendkeys("cifIDSearchTextBox_XPATH", CIFID);
		CommonMethods.Click("FetchButton_XPATH");
		
		Thread.sleep(2000);


		String expcifid = detailsPage.getCIFID();
		Assert.assertEquals(CIFID, expcifid, "SR Search By CIF ID  Mismatched");
	}

	// verify SR Search By Corporate Name

	public void verifySRSearchByCorporateName(String CorporateName) throws Exception {
		
		//String actualcorporatename = excel.getCellData(sheetname, "CorporateName", row);
		CommonMethods.sendkeys("corporatenameSearchTextBox_XPATH", CorporateName);
		CommonMethods.Click("FetchButton_XPATH");
		
		Thread.sleep(2000);

		String expcorporatename = detailsPage.getCorporateName();
		Assert.assertEquals(CorporateName, expcorporatename, "SR Search By Corporate Name  Mismatched");
	}

	// ENter SR Number in Search Box
	public void enterSRNumberSearch(String actualSrnumber) throws IOException, InterruptedException {
		CommonMethods.sendkeys("srSearchTextBox_XPATH", actualSrnumber);
		Thread.sleep(2000);
		CommonMethods.Click("FetchButton_XPATH");
		Thread.sleep(2000);
	}

	// verify SR Is visible in Views
	public void verifySRVisibleInViews(String actualSrnumber) throws Exception {
		String expSRNumber = CommonMethods.getElementText("viewsSRNumbertext_XPATH");
		

		Assert.assertEquals(actualSrnumber, expSRNumber, "SR Number not visible in views  ");
		
	}

	// click on Report Tab
	public void clickReportsTab() throws Exception {

		CommonMethods.highlightelement("reportsTab_XPATH");
		CommonMethods.Click("reportsTab_XPATH");
	}

	// move to reports tab
	public void moveToReportsTab() throws Exception {
		CommonMethods.highlightelement("reportstabhover_XPATH");
		CommonMethods.mouseHover("reportstabhover_XPATH");

	}

	// click on Reports link under Reports Tab
	public void clickReportslink() throws Exception {
		CommonMethods.highlightelement("reportslink_XPATH");
		// CommonMethods.clickelementbyjavascript("reportslink_XPATH");
		CommonMethods.Click("reportslink_XPATH");
	}
	
	
	//click on Advanced Search
	public void clickAdvancedSearch() throws IOException, Exception {
		CommonMethods.highlightelement("AdvancedSearch_Xpath");
		
		CommonMethods.clickelementbyjavascript("AdvancedSearch_Xpath");
	}
	
}
