package com.crm.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;

import com.crm.pages.CustomerPage;

public class RetailAppointmentPage extends TestBase {

	public static Logger log = LoggerFactory.getLogger(RetailAppointmentPage.class);

	public RetailAppointmentPage(WebDriver driver) {
		this.driver = driver;
		
	}

	public static Properties config = TestBase.loadConfig();
	public static String fileupload_appointment_path = System.getProperty("user.dir")+ config.getProperty("FileUpload");

	//public static int num = CommonMethods.generateRandomNumber();
	//public static String s = String.valueOf(num);
	//public static String value = "appointment";
	//public static String subject = value.concat(s);
	
	//public static String subject=CommonMethods.generateRandomAppointmentSubject();
	
	// *********Object Repository Retail Appointment Page***************

	

	// **************Methods********************

	// click On New Button On Customer Page
	public void clickOnNewButton() throws Exception {
		
		
		CommonMethods.mouseHover("newButtonRetailAppointmentPage_XPATH");
		CommonMethods.highlightelement("newButtonRetailAppointmentPage_XPATH");

	}

//	public String getactualSubject() {
//		log.info("Data sucessfully extracted on " + subject);
//
//		return subject;
//	}

	
	// create appointment/activity
	public void createAppointment1(String AppointmentStatus,String CustomerType,String TypeOfCustomer,String TypeofContact,
			String Subject,String Details,String sheetname,int row) throws Exception {
		// Switch to window
		CommonMethods.switchToWindow();

		// maximize window
		CommonMethods.maximizeWindow();
		
		//select activity status
		 CommonMethods.selectByText("activityStatusdropdownRetailAppointmentPage_XPATH", AppointmentStatus);
		 Thread.sleep(200);
		//Select CUstomer Type
		CommonMethods.selectByText("CustomerType_XPATH", CustomerType);
		
         Thread.sleep(3000);
		// Click on Type of Customer Search
		CommonMethods.Click("typeCustomerPickerSearchRetailAppointmentPage_XPATH");

		// Enter type of Customer
		CommonMethods.sendkeys("searchTextBoxRetailAppointmentPage_XPATH", TypeOfCustomer);
         Thread.sleep(2000);
		// click on Filter arrow button
		CommonMethods.Click("filterbuttonRetailAppointmentPage_XPATH");
		 Thread.sleep(4000);
		for (int i = 0; i <= 3; i++) {
		// Click on customer checkbox
		try {

			CommonMethods.clickelementbyjavascript("customercheckboxRetailAppointmentPage_XPATH");
			break;
		} catch (StaleElementReferenceException e) {
			
			System.out.println(e.getMessage());
		}
		}
		// click on Ok Button
		
		for(int i = 0; i <= 3; i++) {
		try {
			//CommonMethods.Click("okButtonRetailAppointmentPage_XPATH");
			CommonMethods.clickelementbyjavascript("okButtonRetailAppointmentPage_XPATH");
			break;
		} catch (StaleElementReferenceException e) {
			//CommonMethods.Click("okButtonRetailAppointmentPage_XPATH");
			System.out.println(e.getMessage());
		}
		}
		// Select Type of Contact
		CommonMethods.selectByText("typeofcontactdropdownRetailAppointmentPage_XPATH",TypeofContact);

		Thread.sleep(1000);
		// Enter subject
		String subject=CommonMethods.generateRandomAppointmentSubject();
		CommonMethods.sendkeys("subjectTextBoxRetailAppointmentPage_XPATH", subject);

		// write appointment subject to excel sheet
          excel.setCellData(sheetname, "Subject", row, subject);
          
		// ENter Details
		CommonMethods.scrollByVisibilityofElement("detailsTextBoxRetailAppointmentPage_XPATH");
		CommonMethods.sendkeys("detailsTextBoxRetailAppointmentPage_XPATH", Details);

		// upload file attachement
		CommonMethods.clickelementbyjavascript("attachementRetailAppointmentPage_XPATH");
		Thread.sleep(1000);

		CommonMethods.fileupload(fileupload_appointment_path);
		Thread.sleep(1000);

		// Click on save button
		CommonMethods.Click("saveButtonRetailAppointmentPage_XPATH");
		Thread.sleep(3000);

		// switch to parent window
		CommonMethods.switchtoparentwindow();
		Thread.sleep(3000);

		String exptitle = "Activities - CRMnext - Smart.Easy.Complete";

		if (driver.getTitle().equalsIgnoreCase(exptitle)) {

			CommonMethods.selectByText("activityViewsdropdownRetailAppointmentPage_XPATH", "Appointment");
			Thread.sleep(3000);
			// click on Arrow Button
			clickOnArrowButton();
			Thread.sleep(3000);
			
			clickDueDateViews();
			Thread.sleep(1000);
			
			CommonMethods.highlightelement("viewsSearchIcon_XPATH");
			CommonMethods.Click("viewsSearchIcon_XPATH");
			
			Thread.sleep(1000);
			CommonMethods.selectByText("viewsSearchByDropDown_XPATH", "Subject");
			Thread.sleep(1000);
			CommonMethods.sendkeys("viewsSearchInputTextBox_XPATH", subject);
			Thread.sleep(1000);
			
			String activityID = CommonMethods.getElementText("activityIDRetailAppointmentPage_XPATH");
			// write appointment ID to excel sheet
	          excel.setCellData(sheetname, "ActivityID", row, activityID);
			String appointmentStatus = CommonMethods.getElementText("appointmentStatusRetailAppointmentPage_XPATH");
			String appointmentSubject = CommonMethods.getElementText("appointmentSubjectRetailAppointmentPage_XPATH");
			String appointmentDuedate = CommonMethods.getElementText("appointmentDuedateRetailAppointmentPage_XPATH");

			log.info("Activity is created with Activity ID" + activityID + "Activity Status" + appointmentStatus
					+ "Subject " + appointmentSubject + "Activity Due Date" + appointmentDuedate);
			Thread.sleep(3000);
			
			//update all sheets
			updateAppointmentrAllSheet(subject,activityID);
			
			
			CustomListener.extentInfo("Appointment ID ", " : " + activityID);
			CustomListener.extentInfo("Appointment Subject ", " : " + appointmentSubject);
			CustomListener.extentInfo("Appointment Status ", " : " + appointmentStatus);
			CustomListener.extentInfo("Appointment Due Date  ", " : " + appointmentDuedate);
			//ScreenShot.takeScreenShot("Appointment Creation");
		} else {
			Thread.sleep(4000);

			String appointmentSubject = CommonMethods.getElementText("appointmentSubjectRetailAppointmentPage_XPATH");
			String appointmentStatus = CommonMethods.getElementText("appointmentStatusRetailAppointmentPage_XPATH");
			String appointmentDuedateretailcustomerpage = CommonMethods
					.getElementText("appointmentDuedateOnRetailsCustomerPage_XPATH");
			log.info("Activity is created with Subject " + appointmentSubject + "Appointment Status "
					+ appointmentStatus + "Appointment Status" + appointmentStatus + "Appointment Due Date"
					+ appointmentDuedateretailcustomerpage);
			CustomListener.extentInfo("Appointment Subject ", " : " + appointmentSubject);
			CustomListener.extentInfo("Appointment Status ", " : " + appointmentStatus);
			CustomListener.extentInfo("Appointment Due Date  ", " : " + appointmentDuedateretailcustomerpage);

			
		}
	}
     public void updateAppointmentrAllSheet(String subject,String activityID) throws Exception {
		
    	 excel.setCellData("verifyCreatedActivityClosed", "Subject", 1, subject);
    	 excel.setCellData("verifyCreatedActivityClosed", "ActivityID", 1, activityID);
    	 excel.setCellData("verifyCreatedActivityEdited", "Subject", 1, subject);
    	 excel.setCellData("verifyCreatedActivityEdited", "ActivityID", 1, activityID);
    	 excel.setCellData("verifyScheduledAppointment", "Subject", 1, subject);
    	 excel.setCellData("verifyScheduledAppointment", "ActivityID", 1, activityID);
    	 excel.setCellData("verfiyCategoryViewAppointment", "Subject", 1, subject);
    	 excel.setCellData("verfiyCategoryViewAppointment", "ActivityID", 1, activityID);
    	// excel.setCellData("verifySummaryPage", "Subject", 1, subject);
	}
	//search activity
	public void searchActivity(String ActivityID) throws Exception {
		
		CommonMethods.highlightelement("viewsSearchIcon_XPATH");
		CommonMethods.Click("viewsSearchIcon_XPATH");
		
		Thread.sleep(1000);
		CommonMethods.selectByText("viewsSearchByDropDown_XPATH", "Activity ID");
		Thread.sleep(1000);
		CommonMethods.sendkeys("viewsSearchInputTextBox_XPATH", ActivityID);
		Thread.sleep(1000);
	}
	// click On Arrow Button on views

	public void clickOnArrowButton() throws Exception {
		Thread.sleep(1000);

		//CommonMethods.highlightelement("arrowbuttonRetailAppointmentPage_XPATH");
		Thread.sleep(2000);
		//CommonMethods.clickelementbyjavascript("arrowbuttonRetailAppointmentPage_XPATH");
         CommonMethods.mouseClick("arrowbuttonRetailAppointmentPage_XPATH");
	}

	// Mouse Hover On Appointment Close Edit link in Views
	public void movehoverOnCLoseEditLink() throws Exception {

		CommonMethods.mouseHover("appointmentClosEditLinkRetailAppointmentPage_XPATH");

		CommonMethods.highlightelement("appointmentClosEditLinkRetailAppointmentPage_XPATH");
	}
	
	
	// click on Edit Link under Views

	public void clickOnEditViewLink() throws Exception {
		
		CommonMethods.highlightelement("EditViewLinkRetailAppointmentPage_XPATH");
		CommonMethods.Click("EditViewLinkRetailAppointmentPage_XPATH");
	}

	// click on Close Link under Views
	public void clickOnCLoseViewLink() throws Exception {
		
		CommonMethods.highlightelement("CloseViewLinkRetailAppointmentPage_XPATH");
		CommonMethods.Click("CloseViewLinkRetailAppointmentPage_XPATH");
	}

	// Edit Appointment
	public void editAppointment(String AppointmentStatus,String Details,String sheetname,int row) throws Exception {
		
		//Switch to window
		CommonMethods.switchwindow();
		Thread.sleep(3000);
		
		//maximize window
		driver.manage().window().maximize();

		// Edit activity Status
		
       // CommonMethods.selectByText("activityStatusdropdownRetailAppointmentPage_XPATH", "In Progress");
        CommonMethods.selectByText("activityStatusdropdownRetailAppointmentPage_XPATH", AppointmentStatus);
		Thread.sleep(2000);
		CommonMethods.scrollDown(200);

		//Enter details as edited
		String details =excel.getCellData(sheetname, "Details", row);
		String detailstext=details.concat("Edited");
		CommonMethods.sendkeys("detailsTextBoxRetailAppointmentPage_XPATH", detailstext);
		
		Thread.sleep(2000);
		
		//CLick On Save Button
		CommonMethods.Click("saveButton_XPATH");
		

		// switch to parent window
		CommonMethods.switchtoparentwindow();
		Thread.sleep(3000);

		
		//click on Arrow Button in Views
		clickOnArrowButton();

		Thread.sleep(3000);
		//get Text of appointment status
		String appointmentstatus=CommonMethods.getElementText("appointmentStatusRetailAppointmentPage_XPATH");
				
		//verify Appointment is Edited
		Assert.assertEquals("In Progress", appointmentstatus, "Appointment is not Edited");

		log.info("Appointment status:" + appointmentstatus);
		CustomListener.extentInfo("Appointment status:" ,appointmentstatus);
		

		
	}

	// close Appointment

	public void closeAppointment(String CustomerName,String MobileNumber,String ProductPitched,String JointCall,
			String CommunicationSent,String sheetname,int row) throws Exception {
		//Switch to window
		CommonMethods.switchwindow();
		Thread.sleep(3000);
		
		//maximize window
		driver.manage().window().maximize();
		
		
		
			Thread.sleep(2000);
		Thread.sleep(2000);
		CommonMethods.scrollDown(600);
		//Enter Customer Name
		CommonMethods.sendkeys("customerNametextboxRetailAppointmentPage_XPATH", sheetname, "CustomerName", 1);
		
		//Enter Mobile Number
		String mobileno=CommonMethods.generateRandomMobileNumber();
		CommonMethods.sendkeys("MobileNumbertextboxRetailAppointmentPage_XPATH", mobileno);
		//ExcelUtils.writeToExcel(sheetname, "MobileNumber", 1, mobileno);
		excel.setCellData(sheetname, "MobileNumber", row, mobileno);
		
		//Select Product Pitched
		CommonMethods.selectByText("ProductpitchedDropdownRetailAppointmentPage_XPATH",ProductPitched);
		
		//Select JointCall
		CommonMethods.selectByText("JointCallDropdownRetailAppointmentPage_XPATH", JointCall);
		
		
		//Select Communication sent
		CommonMethods.selectByText("CommunicationsentDropdownRetailAppointmentPage_XPATH", CommunicationSent);
				
				
		//click on Save Button
		CommonMethods.Click("saveButton_XPATH");
		

		// switch to parent window
		CommonMethods.switchtoparentwindow();
		Thread.sleep(2000);
		
		//click on views arrow button
		clickOnArrowButton();

		Thread.sleep(4000);
		
		//get Text of appointment status
		String appointmentstatus=CommonMethods.getElementText("appointmentStatusRetailAppointmentPage_XPATH");
		
		//verify Appointment is closed
		Assert.assertEquals("Meeting Done", appointmentstatus, "Appointment is not closed");
		
		
	}
	public String createAppointmentForSubordinateActivities(String appointment_sheet) throws Exception {
         //switch to window
		CommonMethods.switchwindow();
		Thread.sleep(3000);
		//maximize window
        CommonMethods.maximizeWindow();
		// select assigned to
		
		CommonMethods.Click("assignedToPickerSearchRetailAppointmentPage_XPATH");
		Thread.sleep(2000);
		
		CommonMethods.sendkeys("searchTextBoxRetailAppointmentPage_XPATH", appointment_sheet, "AssignedTo", 2);
		
		String assignedTo = excel.getCellData(appointment_sheet, "AssignedTo", 2);
		//System.out.println("Assigned To Selected: " + assignedTo);
		CommonMethods.Click("assignedTofilterbuttonRetailAppointmentPage_XPATH");
		
		Thread.sleep(2000);
		CommonMethods.Click("assignedToshortNameRetailAppointmentPage_XPATH");
		
		

		Thread.sleep(2000);

		// Enter Type of Customer
		CommonMethods.Click("typeCustomerPickerSearchRetailAppointmentPage_XPATH");
		
		
		Thread.sleep(2000);
		CommonMethods.sendkeys("searchTextBoxRetailAppointmentPage_XPATH", appointment_sheet, "TypeOfCustomer", 2);
		
		
		String typeofcustomer = excel.getCellData(appointment_sheet, "TypeOfCustomer", 2);
		
		System.out.println("Type of Customer Selected: " + typeofcustomer);
		CommonMethods.Click("filterbuttonRetailAppointmentPage_XPATH");
		
		Thread.sleep(3000);
        CommonMethods.clickelementbyjavascript("customercheckboxRetailAppointmentPage_XPATH");
		
		Thread.sleep(3000);
		CommonMethods.Click("okButtonRetailAppointmentPage_XPATH");
		
		String subject=CommonMethods.generateRandomAppointmentSubject();
		
		CommonMethods.sendkeys("subjectTextBoxRetailAppointmentPage_XPATH", subject);
		
		excel.writeToExcel(appointment_sheet, 2, 1, subject);

		Thread.sleep(3000);
          CommonMethods.scrollByVisibilityofElement("detailsTextBoxRetailAppointmentPage_XPATH");
		
		// enter Details
		
		CommonMethods.sendkeys("detailsTextBoxRetailAppointmentPage_XPATH", appointment_sheet, "Details", 2);
		String details = excel.getCellData(appointment_sheet, "Details", 2);
		System.out.println("Details Entered: " + details);
		
		Thread.sleep(3000);
		
		CommonMethods.clickelementbyjavascript("attachementRetailAppointmentPage_XPATH");
		Thread.sleep(3000);

		// upload file on appointments page
		CommonMethods.fileupload(fileupload_appointment_path);
		Thread.sleep(2000);
		CommonMethods.Click("saveButtonRetailAppointmentPage_XPATH");
	
		Thread.sleep(10000);

		// switch to parent window
		CommonMethods.switchtoparentwindow();
		Thread.sleep(8000);

		String exptitle = "Activities - CRMnext - Smart.Easy.Complete";
		// boolean value=activitiesPage.isDisplayed();
		// boolean value1=RetailsCustomerPage.isDisplayed();
		// click On Arrow Button on Views Page
		if (driver.getTitle().equalsIgnoreCase(exptitle)) {

			CommonMethods.selectByText("activityViewsdropdownRetailAppointmentPage_XPATH", "Appointment");
			Thread.sleep(3000);
			// click on Arrow Button
			clickOnArrowButton();

			String activityID = CommonMethods.getElementText("activityIDRetailAppointmentPage_XPATH");
			String appointmentStatus = CommonMethods.getElementText("appointmentStatusRetailAppointmentPage_XPATH");
			String appointmentSubject = CommonMethods.getElementText("appointmentSubjectRetailAppointmentPage_XPATH");
			String appointmentDuedate = CommonMethods.getElementText("appointmentDuedateRetailAppointmentPage_XPATH");

			log.info("Activity is created with Activity ID" + activityID + "Activity Status" + appointmentStatus
					+ "Subject " + appointmentSubject + "Activity Due Date" + appointmentDuedate);
			CustomListener.extentInfo("Appointment ID ", " : " + activityID);
			CustomListener.extentInfo("Appointment Subject ", " : " + appointmentSubject);
			CustomListener.extentInfo("Appointment Status ", " : " + appointmentStatus);
			CustomListener.extentInfo("Appointment Due Date  ", " : " + appointmentDuedate);
			ScreenShot.takeScreenShot("Appointment Creation");
		} else {
			Thread.sleep(4000);

			String appointmentSubject = CommonMethods.getElementText("appointmentSubjectRetailAppointmentPage_XPATH");
			String appointmentStatus = CommonMethods.getElementText("appointmentStatusRetailAppointmentPage_XPATH");
			String appointmentDuedateretailcustomerpage = CommonMethods
					.getElementText("appointmentDuedateOnRetailsCustomerPage_XPATH");
			log.info("Activity is created with Subject " + appointmentSubject + "Appointment Status "
					+ appointmentStatus + "Appointment Status" + appointmentStatus + "Appointment Due Date"
					+ appointmentDuedateretailcustomerpage);
			CustomListener.extentInfo("Appointment Subject ", " : " + appointmentSubject);
			CustomListener.extentInfo("Appointment Status ", " : " + appointmentStatus);
			CustomListener.extentInfo("Appointment Due Date  ", " : " + appointmentDuedateretailcustomerpage);

		
		}
		return CommonMethods.getElementText("appointmentSubjectRetailAppointmentPage_XPATH");
	}

	public String getAppointmentSubject(String sheetname) throws IOException {

		// String appointmentsubject= appointmentSubject.getText();
		String appointmentsubject = excel.getCellData(sheetname, "Subject", 1);
		return appointmentsubject;

	}

	// pagination

	// for dropdown check values
	public void verifyPaginationDropDownValues() throws Exception {
		// select value from dropdown as 10

		// scroll to dropdown
		CommonMethods.scrollByVisibilityofElement("pagelistingdropdownRetailAppointmentPage_XPATH");

		// select value from dropdown
		CommonMethods.selectByText("pagelistingdropdownRetailAppointmentPage_XPATH", "10");

		// verify after selecting value as 10 from dropdown its showing 10 records
		String actualshowrecordtext = "Showing 1-10 Records";
		String expshowrecordtext = CommonMethods.getElementText("ShowRecordTextRetailAppointmentPage_XPATH");
		System.out.println(expshowrecordtext);

		// Assert actual expected msg
		Assert.assertEquals(actualshowrecordtext, expshowrecordtext, "Dropdown Text mismatched");

		// get List count of elements
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='crm-grid-row relative']"));
		int actualcount = ele.size();
		System.out.println("Row count after selecting dropdown Value as 10 from Pagination: " + ele.size());
		int expcount = 10;

		// Verify actual exp count
		Assert.assertEquals(expcount, actualcount, "Count mismatched");

	}

	// select value from pagination dropdown as 10
	public void verifypaginationdropdownvalues10() throws Exception {
		CommonMethods.scrollByVisibilityofElement("pagelistingdropdownRetailAppointmentPage_XPATH");
		Thread.sleep(4000);
		CommonMethods.selectByText("pagelistingdropdownRetailAppointmentPage_XPATH", "10");

		Thread.sleep(4000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='crm-grid-row relative']"));
		int actualcount10 = ele.size();
		System.out.println("Row count after selecting dropdown Value as 10 from Pagination: " + ele.size());
		// System.out.println(ele.size());

		int expcount = 10;
		Assert.assertEquals(expcount, actualcount10, "Count mismatched");

	}

	public void verifypaginationdropdownvalue20() throws Exception {

		
		
		CommonMethods.scrollByVisibilityofElement("pagelistingdropdownRetailAppointmentPage_XPATH");
		Thread.sleep(4000);
		CommonMethods.selectByText("pagelistingdropdownRetailAppointmentPage_XPATH", "20");
		

		Thread.sleep(4000);
		List<WebElement> ele = driver.findElements(By.xpath("//div[@class='crm-grid-row relative']"));
		int actualcount10 = ele.size();
		System.out.println("Row count after selecting dropdown Value as 20 from Pagination: " + ele.size());
		// System.out.println(ele.size());

		int expcount = 20;
		Assert.assertEquals(expcount, actualcount10, "Count mismatched");
	}

	// verify Go To pagination TextBox

	public void verifypaginationGoToTextBox() throws Exception {

		Thread.sleep(2000);

		// scroll to dropdown
		CommonMethods.scrollByVisibilityofElement("pageGoToTextboxRetailAppointmentPage_XPATH");

		// enter value in textbox
		CommonMethods.sendkeys("pageGoToTextboxRetailAppointmentPage_XPATH", "2");

		Thread.sleep(2000);
		// Enter
		CommonMethods.KeysEnter("pageGoToTextboxRetailAppointmentPage_XPATH");

		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[@data-autoid='pagination_2']")).isEnabled());

	}

	// select value from dropdown from views

	public void verifyMyActivitiesView(String appointmentsubject) throws Exception {
		
		//String actualvalue;
		CommonMethods.selectByText("categorydDropdownRetailAppointmentPage_XPATH", "Appointment");
		
		Thread.sleep(2000);
		CommonMethods.selectByText("viewIdDropdownRetailAppointmentPage_XPATH", "My Activites");
		
		Thread.sleep(2000);
		CommonMethods.Click("arrowbuttonRetailAppointmentPage_XPATH");
		
		//select pagination dropdown values as 10
		//CommonMethods.scrollByVisibilityofElement("pagelistingdropdownRetailAppointmentPage_XPATH");
		Thread.sleep(4000);
		CommonMethods.selectByText("pagelistingdropdownRetailAppointmentPage_XPATH", "10");
		Thread.sleep(2000);
		
		//select sorter for due date
		clickDueDateViews();
		Thread.sleep(2000);
		
		
		//verify appointment present in views
		List<WebElement> listele=driver.findElements(By.xpath("//a[starts-with(@data-autoid, 'Subject_')]/span[2]"));
		for(WebElement e:listele) {
			System.out.println("Appointmenttext: " + e.getText());
			if(e.getText().contains(appointmentsubject)) {
				
				//actualvalue= e.getText();
				System.out.println(e.getText());
     		   break;
     	   }
			log.info("Created appointment  present in My Activities Views = "  + e.getText());	
			CustomListener.extentInfo("Created appointment  present in My Activities Views =  ", e.getText());
		}
		
	}

	


	// verify lead creation page open on click New lead + icon
	public void verifyAppointmentCreationPageOpen() throws IOException, InterruptedException {
		
		//switch to window
       CommonMethods.switchToWindow();
       //maximize window
       CommonMethods.maximizeWindow();
       
       Thread.sleep(4000);
		String actualtitle = "Retail Appointment -";

		CommonMethods.verifyPageTitle(actualtitle);
		
		ScreenShot.screenshot("AppointmentPage Opens");
		
		CommonMethods.switchtoparentwindow();

	}

	
	public void clickDueDateViews() throws IOException, Exception {
		boolean flag=CommonMethods.isElementPresent("viewsSortDueDate_XPATH");
		boolean flagasscending=CommonMethods.isElementPresent("viewsAscendingSortDueDate_XPATH");
		boolean flagdescending=CommonMethods.isElementPresent("viewsDescendingSortDueDate_XPATH");
		
		if (flag==true) {
			CommonMethods.mouseClick("viewsSortDueDate_XPATH");
			Thread.sleep(2000);
			CommonMethods.mouseClick("viewsAscendingSortDueDate_XPATH");
		}
		
		else if(flagasscending==true) {
			
			CommonMethods.mouseClick("viewsAscendingSortDueDate_XPATH");
		}
		
		else {
			System.out.println("Due Date in Descending sort");
		}
	}
	
	
	// Verify Activity ID column working order by
	public void verifyOrderBy() throws Exception {

		boolean flag = CommonMethods.isElementPresent("viewsSortRetailAppointmentPage_XPATH");
		boolean flagassc = CommonMethods.isElementPresent("viewsAsscendingSortRetailAppointmentPage_XPATH");

		if (flag == true) {
			CommonMethods.mouseClick("viewsSortRetailAppointmentPage_XPATH");
			Thread.sleep(2000);

			verifyActivityIDAscendingOrder();
		}

		else if (flagassc == true) {

			verifyActivityIDAscendingOrder();
		}

		else {

			Thread.sleep(2000);

			Thread.sleep(2000);
			//System.out.println("hi");

			verifyActivityIDDescendingOrder();
		}

	}

	// verify Activity ID column is in ascending order

	public void verifyActivityIDAscendingOrder() {

		// Actual List
		ArrayList<String> ActualList = new ArrayList<String>();

		List<WebElement> elementList = driver.findElements(By.xpath("//div[starts-with(@data-autoid,'ActivityNo_')]"));

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

	// verify Activity ID column is in descending order
	public void verifyActivityIDDescendingOrder() {

		// Actual List
		ArrayList<String> ActualList = new ArrayList<String>();

		List<WebElement> elementList = driver.findElements(By.xpath("//div[starts-with(@data-autoid,'ActivityNo_')]"));

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

}
