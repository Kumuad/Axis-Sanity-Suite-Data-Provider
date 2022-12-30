package com.crm.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;

public class LeadsPage extends TestBase {

	public static Logger log = LoggerFactory.getLogger(LeadsPage.class);

	// Constructor
	public LeadsPage(WebDriver driver) {
		this.driver = driver;

	}
	public static Properties config = TestBase.loadConfig();
	public static String fileupload_document_path = System.getProperty("user.dir")+ config.getProperty("FileUpload");

	// **************Methods starts ********************

	// click On New Button On Home Page
	public void clickOnNewButton() throws Exception {
		CommonMethods.mouseHover("newButtonLeadPage_XPATH");
		CommonMethods.highlightelement("newButtonLeadPage_XPATH");

	}

	// click On toggle button on details page
	public void clickToggleButton() throws Exception {

		CommonMethods.highlightelement("toggleButton_XPATH");
		CommonMethods.Click("toggleButton_XPATH");
	}

	// click On Attachments Tab
	public void clickOnAttachementstab() throws Exception {

		CommonMethods.highlightelement("attachementsTabLeadPage_XPATH");
		CommonMethods.mouseClick("attachementsTabLeadPage_XPATH");

	}

	// click On History Tab
	public void clickOnHistoryTab() throws Exception {

		CommonMethods.mouseHover("historyTabLeadPage_XPATH");
		CommonMethods.highlightelement("historyTabLeadPage_XPATH");
		CommonMethods.Click("historyTabLeadPage_XPATH");

	}

	// click On Activities Tab
	public void clickOnActivitiestab() throws Exception {

		CommonMethods.highlightelement("activitiesTabLeadPage_XPATH");
		CommonMethods.mouseClick("activitiesTabLeadPage_XPATH");

	}

	// click On New Attachments Document Link
	public void clickOnNewAttachementsLink() throws Exception {

		// Mouse hover on New Attachment
		CommonMethods.mouseHover("attachementNewDocumentLinkLeadPage_XPATH");
		CommonMethods.highlightelement("attachementNewDocumentLinkLeadPage_XPATH");
		Thread.sleep(2000);

		// click on default attachment
		CommonMethods.highlightelement("defaultattachementLeadPage_XPATH");
		CommonMethods.Click("defaultattachementLeadPage_XPATH");

	}

	// click On New Activity Link
	public void clickOnNewActivityLink() throws Exception {

		CommonMethods.highlightelement("newActivitiesLinkLeadPage_XPATH");
		CommonMethods.Click("newActivitiesLinkLeadPage_XPATH");

	}

	// click On New Task Link
	public void clickOnNewTaskLink() throws Exception {

		CommonMethods.highlightelement("newTaskLinkLeadPage_XPATH");
		CommonMethods.Click("newTaskLinkLeadPage_XPATH");

	}

	// click On Edit Button Lead
	public void clickOnEditButton() throws Exception {

		CommonMethods.highlightelement("EditLeadLeadPage_XPATH");
		CommonMethods.Click("EditLeadLeadPage_XPATH");

		CommonMethods.highlightelement("EditLeadButtonLeadPage_XPATH");
		CommonMethods.Click("EditLeadButtonLeadPage_XPATH");

	}

	public String generateRandomFirstName() {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		String firstname = String.valueOf(id);
		String text = "lead";
		String actualFirstName = text + firstname;
		return actualFirstName;
	}

	// lead creation
	public void createLead1(String Name,String FirstName,String LastName,String Mobile,
			String Email,String DateofBirth,String PAN,String Address,String Product,
			String SubProduct,String Branch,String LeadSource,String Subsource,
			String LeadDescription,
			String sheetname,int row) throws Exception {

		// Enter Name
		CommonMethods.selectByText("nameDropdownLeadPage_XPATH", Name);

		// Enter First Name
		String firstName = generateRandomFirstName();
		CommonMethods.sendkeys("firstNameTextBoxLeadPage_XPATH", firstName);
		excel.setCellData(sheetname, "FirstName", row, firstName);
		excel.setCellData("verifyEditLead", "FirstName", row, firstName);
		
		// Enter Last Name
		CommonMethods.sendkeys("lastNameTextBoxLeadPage_XPATH", LastName);
		excel.setCellData("verifyEditLead", "LastName", row, LastName);

		// Enter Mobile Number
		String mobilenumber = CommonMethods.generateRandomMobileNumber();
		CommonMethods.sendkeys("mobileTextBoxLeadPage_XPATH", mobilenumber);
		excel.setCellData(sheetname, "Mobile", row, mobilenumber);
		excel.setCellData("verifyEditLead", "Mobile", row, mobilenumber);
		
		// Enter Email
		String email = CommonMethods.generateRandomEmail();
		CommonMethods.sendkeys("emailTextBoxLeadPage_XPATH", email);
		excel.setCellData(sheetname, "Email", row, email);
		excel.setCellData("verifyEditLead", "Email", row, email);
		
		// ENter Date of Birth
		CommonMethods.sendkeys("dobTextBox_XPATH", DateofBirth);
		excel.setCellData("verifyEditLead", "DateofBirth", row, DateofBirth);

		// Enter PAN Number
		String pannumber = CommonMethods.generatePANNumber();
		CommonMethods.sendkeys("PANTextBox_XPATH", pannumber);
		excel.setCellData(sheetname, "PAN", row, pannumber);
		excel.setCellData("verifyEditLead", "PAN", row, pannumber);
		
		// Enter Address
		
		CommonMethods.sendkeys("addressTextBoxLeadPage_XPATH", Address);
		excel.setCellData("verifyEditLead", "Address",row, Address);
		Thread.sleep(2000);

		// Select Product
		CommonMethods.selectByText("productDropdown_XPATH", Product);
		excel.setCellData("verifyEditLead", "Product", row, Product);
		Thread.sleep(2000);

		// Select Sub Product
		CommonMethods.selectByText("subProductDropdownLeadPage_XPATH", SubProduct);
		excel.setCellData("verifyEditLead", "SubProduct", row, SubProduct);
		Thread.sleep(2000);

		//driver.manage().window().maximize();
		// Enter Branch
		CommonMethods.Click("branchSearchButtonLeadPage_XPATH");
		CommonMethods.sendkeys("branchTextBoxBoxLeadPage_XPATH", Branch);
		excel.setCellData("verifyEditLead", "Branch", row, Branch);
		
		CommonMethods.Click("arrowLinkLeadPage_XPATH");
		Thread.sleep(3000);
		try {
			// CommonMethods.Click("selectbranchLeadPage_XPATH");
			CommonMethods.clickelementbyjavascript("selectbranchLeadPage_XPATH");
		} catch (StaleElementReferenceException e) {
			CommonMethods.clickelementbyjavascript("selectbranchLeadPage_XPATH");
			// CommonMethods.Click("selectbranchLeadPage_XPATH");
		}

		Thread.sleep(2000);
		// Select lead Source
		CommonMethods.selectByText("leadSourceDropdownLeadPage_XPATH", LeadSource);
		excel.setCellData("verifyEditLead", "LeadSource", row, LeadSource);

		// Select lead sub source
		CommonMethods.Click("subSourceSearchButtonLeadPage_XPATH");
		//String Subsource=ExcelUtils.getCellData(sheetname,"Subsource",1);
		
		CommonMethods.sendkeys("branchTextBoxBoxLeadPage_XPATH", Subsource);
		excel.setCellData("verifyEditLead", "Subsource", row, Subsource);
		CommonMethods.Click("arrowLinkSubSourceLeadPage_XPATH");

		Thread.sleep(3000);
		try {
			CommonMethods.clickelementbyjavascript("selectSubSourceLeadPage_XPATH");
			// CommonMethods.Click("selectSubSourceLeadPage_XPATH");
		} catch (StaleElementReferenceException e) {
			CommonMethods.clickelementbyjavascript("selectSubSourceLeadPage_XPATH");
			// CommonMethods.Click("selectSubSourceLeadPage_XPATH");
		}
		Thread.sleep(2000);

		// Enter lead description
		CommonMethods.scrollByVisibilityofElement("leadDescriptionLeadPage_XPATH");
		excel.setCellData("verifyEditLead", "LeadDescription",row, LeadDescription);
		CommonMethods.sendkeys("leadDescriptionLeadPage_XPATH", LeadDescription);

		Thread.sleep(2000);

		// click on save Button
		CommonMethods.Click("saveButtonLeadPage_XPATH");
		Thread.sleep(2000);

		// Get Lead iD NUmbers
		String leadid = CommonMethods.getElementText("leadIDNumberLeadPage_XPATH");
		//ExcelUtils.writeToExcel(sheetname, 1, 15, leadid);
		excel.setCellData(sheetname, "LeadID", row, leadid);
		updateLeadsAllSheet(leadid,mobilenumber,email,pannumber, row);

	}
	
public void updateLeadsAllSheet(String leadid,String mobilenumber,String email,String  pannumber,int row) throws Exception {
		
	    excel.setCellData("verifyLeadSearch", "LeadID", row, leadid);
	    excel.setCellData("verifyLeadSearch", "Mobile", row, mobilenumber);
	    excel.setCellData("verifyLeadSearch", "Email", row, email);
		excel.setCellData("verifyLeadSearch", "PAN", row, pannumber);
		excel.setCellData("verifyEditLead", "LeadID", row, leadid);
		excel.setCellData("verifyDocumentAttachementOnLead", "LeadID", row, leadid);
		excel.setCellData("verifyAppointmentCreatedOnLead", "LeadID", row, leadid);
		excel.setCellData("verifyTaskCreatedOnLead", "LeadID", row, leadid);
		excel.setCellData("verifyLeadHistoryPage", "LeadID", row, leadid);
		excel.setCellData("verifyCategoryViewLead", "LeadID", row, leadid);
		excel.setCellData("verifySummaryPage", "LeadID", row, leadid);
		
	}
	
	
	
	
	
	
	
	
	
	
	

	// click On Views Arrow Button
	public void clickOnArrowButton() throws Exception {

		CommonMethods.highlightelement("arrowbuttonViewsLeadPage_XPATH");
		Thread.sleep(2000);
		CommonMethods.clickelementbyjavascript("arrowbuttonViewsLeadPage_XPATH");

	}
	// verify category view lead

	public void verifyCategoryViewLead(String LeadID) throws Exception {
		//String actualLeadID = excel.getCellData(sheetname, "LeadID", 1);
		CommonMethods.selectByText("categoryViewDropdownLeadPage_XPATH", "Lead");
		CommonMethods.selectByText("StatuscodeViewDropdownLeadPage_XPATH", "Assigned to Me");
		CommonMethods.Click("arrowbuttonViewsLeadPage_XPATH");

		Thread.sleep(4000);

		String expleadidViews = CommonMethods.getElementText("leadIDViewsLeadPage_XPATH");
		Assert.assertEquals(LeadID, expleadidViews, "Category View Lead Mismatched");

	}

	// verify lead creation page open on click New lead + icon
	public void verifyleadCreationPage() throws IOException {
		String actualtitle = "Lead - CRMnext - Smart.Easy.Complete";

		CommonMethods.verifyPageTitle(actualtitle);
		

	}

	// to verify document is getting attached under attachements

	public void verifyDocumentsAttachedOnDetailsPage() throws Exception {

		// Switch to window
		CommonMethods.switchwindow();
		Thread.sleep(4000);

		// Maximize window
		driver.manage().window().maximize();
		Thread.sleep(3000);

		// click on Search Icon Folder NAme
		CommonMethods.Click("searchIconattachementLeadPage_XPATH");

		// Enter Text in search Text Box
		CommonMethods.sendkeys("inputSearchTextBox_XPATH", "Axis Bank");
		Thread.sleep(2000);
		CommonMethods.KeysEnter("inputSearchTextBox_XPATH");

		CommonMethods.clickelementbyjavascript("selectfolderattachementLeadPage_XPATH");
		Thread.sleep(2000);

		// CLick On Attachment link
		CommonMethods.mouseClick("attachementLeadPage_XPATH");

		// upload file on Task page
		CommonMethods.fileupload(fileupload_document_path);

		// ENter Description
		CommonMethods.scrollByVisibilityofElement("dmsDescriptionLeadPage_XPATH");
		CommonMethods.sendkeys("dmsDescriptionLeadPage_XPATH", "Document Attached");
		Thread.sleep(3000);

		// click On Save Button
		CommonMethods.Click("saveButton_XPATH");

		Thread.sleep(5000);

		// switch to parent window
		CommonMethods.switchtoparentwindow();

		
		// verify Document is attachment
		CommonMethods.isElementDisplayed("documentsAttachedLeadPage_XPATH");
		
	
	}

	// to verify lead History details

	public void verifyLeadHistoryDetails() throws Exception {

		// verify lead status code displayed
		CommonMethods.isElementDisplayed("leadStatusCodehistoryTabLeadPage_XPATH");

		// verify last modified by displayed
		CommonMethods.isElementDisplayed("lastmodifiedByhistoryTabLeadPage_XPATH");

		// verify rolename displayed
		CommonMethods.isElementDisplayed("roleNamehistoryTabLeadPage_XPATH");

		// verify last modified on displayed
		CommonMethods.isElementDisplayed("lastModifiedOnhistoryTabLeadPage_XPATH");

		// verify comments tab displayed
		CommonMethods.isElementDisplayed("commentshistoryTabLeadPage_XPATH");

		// get Text leadStatusCode
		CommonMethods.getElementText("leadStatusCodeLeadPage_XPATH");

		// get Text lastmodifiedBy

		CommonMethods.getElementText("lastmodifiedByLeadPage_XPATH");

		// get Text roleName
		CommonMethods.getElementText("roleNameLeadPage_XPATH");

		// get Text lastModifiedOn
		CommonMethods.getElementText("lastModifiedOnLeadPage_XPATH");

		
	}

	// get leadnumber on leads Page
	public String leadIdNumber() throws Exception {

		// String expleadId=leadIDNumber.getText();

		String expleadId = CommonMethods.getElementText("leadIDNumberLeadPage_XPATH");
		return expleadId;

	}

	// get Mobile Number On Leads Page
	public String verifymobileNumberOnLeadPage() throws Exception {

		// String expmobilenumber=mobileNumber.getText();
		String expmobilenumber = CommonMethods.getElementText("mobileNumberLeadPage_XPATH");

		return expmobilenumber;

	}

	// verify Edit Leads Page

	public void verifyEditLeadPage(String FirstName,String LastName,String Mobile,
			String Email,String PAN,String DateofBirth,String Address,String Product,
			String SubProduct,String Branch,String LeadSource,String Subsource,
			String LeadDescription,String sheetname,int row) throws Exception {

		// firstname
		String actualfirstname = CommonMethods.getAttribute("firstNameTextBoxLeadPage_XPATH");
		//String expfirstname = excel.getCellData(sheetname, "FirstName", row);
		Assert.assertEquals(actualfirstname, FirstName, "Mismatched firstname");

		// lastname

		String actuallastname = CommonMethods.getAttribute("lastNameTextBoxLeadPage_XPATH");
		//String explastname = excel.getCellData(sheetname, "LastName", row);
		Assert.assertEquals(actuallastname, LastName, "Mismatched lastname");

		// Mobile

		String actualmobile = CommonMethods.getAttribute("mobileTextBoxLeadPage_XPATH");
		//String expmobile = excel.getCellData(sheetname, "Mobile", row);
		Assert.assertEquals(actualmobile, Mobile, "Mismatched Mobile");

		// Email
		String actualemail = CommonMethods.getAttribute("emailTextBoxLeadPage_XPATH");
		//String expemail = excel.getCellData(sheetname, "Email", row);
		Assert.assertEquals(actualemail, Email, "Mismatched Email");

		// PAN
		String actualPAN = CommonMethods.getAttribute("PANTextBox_XPATH");
		//String expPAN = excel.getCellData(sheetname, "PAN", row);
		Assert.assertEquals(actualPAN, PAN, "Mismatched PAN");

		// Date of Birth
		String actualDOB = CommonMethods.getAttribute("dobTextBox_XPATH");
		//String expDOB = excel.getCellData(sheetname, "DateofBirth", row);
		Assert.assertEquals(actualDOB, DateofBirth, "Mismatched DateofBirth");

		// Address
		String actualAddress = CommonMethods.getAttribute("addressTextBoxLeadPage_XPATH");
		//String expAddress = excel.getCellData(sheetname, "Address", row);
		Assert.assertEquals(actualAddress, Address, "Mismatched Address");

		// product

		String actualproduct = CommonMethods.getFirstSelectedOption("productDropdown_XPATH");
		//String expproduct = excel.getCellData(sheetname, "Product", row);
		Assert.assertEquals(actualproduct, Product, "Mismatched Product");

		// sub product

		String actualsubproduct = CommonMethods.getFirstSelectedOption("subProductDropdown_XPATH");
		//String expsubproduct = excel.getCellData(sheetname, "SubProduct", row);
		Assert.assertEquals(actualsubproduct, SubProduct, "Mismatched SubProduct");

		// territory/branch
		String actualbranch = CommonMethods.getAttribute("branchinputTextBoxBoxLeadPage_XPATH");
		//String expbranch = excel.getCellData(sheetname, "Branch", row);
		Assert.assertEquals(actualbranch, Branch, "Mismatched branch");

		// leadsource

		String actualleadsource = CommonMethods.getFirstSelectedOption("leadSourceDropdownLeadPage_XPATH");
		//String expleadsource = excel.getCellData(sheetname, "LeadSource", row);
		Assert.assertEquals(actualleadsource, LeadSource, "Mismatched LeadSource");

		// sub source

		String actualsubsource = CommonMethods.getAttribute("subSourceTextBoxLeadPage_XPATH");
		//String expsubsource = excel.getCellData(sheetname, "Subsource", row);
		Assert.assertEquals(actualsubsource, Subsource, "Mismatched Subsource");

		// lead description

		String actualdescription = CommonMethods.getAttribute("leadDescriptionLeadPage_XPATH");
		//String expsdescription = excel.getCellData(sheetname, "LeadDescription", row);
		Assert.assertEquals(actualdescription, LeadDescription, "Mismatched description");

	}

	// *******************Pagination********************
	public void verifyOrderBy() throws Exception {

		boolean flag = CommonMethods.isElementPresent("viewsSortLeadPage_XPATH");
		boolean flagassc = CommonMethods.isElementPresent("viewsAsscendingSortLeadPage_XPATH");

		if (flag == true) {
			CommonMethods.mouseClick("viewsSortLeadPage_XPATH");
			Thread.sleep(2000);

			verifyLeadIDAscendingOrder();
		}

		else if (flagassc == true) {

			verifyLeadIDAscendingOrder();
		}

		else {
			// CommonMethods.mouseClick("viewsDescendingSortLeadPage_XPATH");
			Thread.sleep(2000);
			// CommonMethods.mouseClick("viewsSortLeadPage_XPATH");

			Thread.sleep(2000);

			verifyLeadIDDescendingOrder();
		}

	}
	// verify Lead ID column is in ascending order

	public void verifyLeadIDAscendingOrder() {

		// Actual List
		ArrayList<String> ActualList = new ArrayList<String>();

		List<WebElement> elementList = driver.findElements(By.xpath("//a[starts-with(@data-autoid,'LeadID_')]/span"));

		for (WebElement we : elementList) {

			ActualList.add(we.getText());

			System.out.println("Actual List: " + ActualList);
			ArrayList<String> ExpectedList = new ArrayList<String>();

			// COPY actual list to another list
			ExpectedList.addAll(ActualList);
			Collections.sort(ExpectedList);

			System.out.println("Expected List order:  " + ExpectedList);

			Assert.assertEquals(ActualList, ExpectedList, "list are sorted in ascending order: ");
		}

	}

	// verify Lead ID column is in descending order
	public void verifyLeadIDDescendingOrder() {

		// Actual List
		ArrayList<String> ActualList = new ArrayList<String>();

		List<WebElement> elementList = driver.findElements(By.xpath("//a[starts-with(@data-autoid,'LeadID_')]/span"));

		for (WebElement we : elementList) {

			ActualList.add(we.getText());

			System.out.println("Actual List: " + ActualList);
			ArrayList<String> sortlist = new ArrayList<String>();
			ArrayList<String> ExpectedList = new ArrayList<String>();

			// COPY actual list to another list
			sortlist.addAll(ActualList);
			Collections.sort(sortlist);
			ExpectedList.addAll(sortlist);
			Collections.reverse(ExpectedList);

			System.out.println("Expected List order:  " + ExpectedList);

			// Assert.assertEquals(" list are sorted in assending order: ", ExpectedList,
			// ActualList);
			Assert.assertEquals(ActualList, ExpectedList, "list are in descending order: ");
		}

	}
	// verify Pagination Dropdown values

	public void verifyPaginationDropdown() throws Exception {
		// scroll to dropdown
		CommonMethods.scrollByVisibilityofElement("pagelistingdropdownRetailAppointmentPage_XPATH");

		// select value from dropdown
		CommonMethods.selectByText("pagelistingdropdownRetailAppointmentPage_XPATH", "10");

		// verify after selecting value as 10 from dropdown its showing 10 records
		String actualshowrecordtext = "Showing 1-10 Records";
		String expshowrecordtext = CommonMethods.getElementText("ShowRecordTextRetailAppointmentPage_XPATH");
		System.out.println(expshowrecordtext);

		// Asser actual expected msg
		Assert.assertEquals(actualshowrecordtext, expshowrecordtext, "Dropdown Text mismatched");

		// get List count of elements
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='crm-grid-row relative']"));
		int actualcount10 = ele.size();
		System.out.println("Row count after selecting dropdown Value as 10 from Pagination: " + ele.size());
		int expcount = 10;

		// verfiy actual exp count
		Assert.assertEquals(expcount, actualcount10, "Count mismatched");

	}

	// verify Go To pagination TextBox

	public void verifypaginationGoToTextBox() throws Exception {

		Thread.sleep(4000);

		// scroll to dropdown
		CommonMethods.scrollByVisibilityofElement("pageGoToTextboxRetailAppointmentPage_XPATH");

		// enter value in textbox
		CommonMethods.sendkeys("pageGoToTextboxRetailAppointmentPage_XPATH", "2");

		Thread.sleep(4000);
		// Enter
		CommonMethods.KeysEnter("pageGoToTextboxRetailAppointmentPage_XPATH");

		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[@data-autoid='pagination_2']")).isEnabled());

	}
}
