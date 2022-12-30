package com.crm.pages;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;

public class RegistrationPage extends TestBase {

	public static Logger log = LoggerFactory.getLogger(RegistrationPage.class);

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;

	}

	// String sheetname="ServiceRequest";
	public static Properties config = TestBase.loadConfig();
	// public static String fileupload_attachement=System.getProperty("user.dir")
	// +"\\src\\main\\resources\\testdata\\Test.docx";
	public static String fileupload_attachement = System.getProperty("user.dir") + config.getProperty("FileUpload");
	// Object Repository of Registration Page

	// PageMethods--starts

	public String verifyregistrationPageTitle() {
		return driver.getTitle();

	}

	public void createSRStandardWorkFLow(String LodgementUserTeam, String Product,
			String AccountCardDetailSearchParameter, String Function, String SubFunction, String SubSubFunction,
			String NatureofQuery, String LodgementRemarks, String sheetname, int row) throws Exception {

		// Select Lodgement Team
		CommonMethods.Click("lodgementTeamSearchicon_XPATH");
		CommonMethods.sendkeys("inputSearchTextBox_XPATH", LodgementUserTeam);

		// String LodgementUserTeamext=ExcelUtils.getCellData(servicerequest_sheetname,
		// "LodgementUserTeam",1);
		// Click On Filter arrow button
		CommonMethods.Click("filterarrowbutton_XPATH");

		try {
			WebElement elementtext = driver
					.findElement(By.xpath("//div[contains(text(),'" + LodgementUserTeam + "')]"));
			Thread.sleep(2000);
			elementtext.click();
		} catch (StaleElementReferenceException e) {
			WebElement elementtext = driver
					.findElement(By.xpath("//div[contains(text(),'" + LodgementUserTeam + "')]"));
			Thread.sleep(2000);
			elementtext.click();
		}
		Thread.sleep(2000);

		// Select Product

		CommonMethods.Click("selectproductarrowbutton_XPATH");
		// String product=ExcelUtils.getCellData(servicerequest_sheetname, "Product",
		// 1);
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + Product + "')]"));
		Thread.sleep(2000);
		element.click();
		Thread.sleep(5000);
		// Select accountCardDetailSearchParatameter
		CommonMethods.selectByText("accountCardDetailSearchParatameterDropdown_XPATH",
				AccountCardDetailSearchParameter);

		CommonMethods.scrollDown(400);

		// Enter Function
		CommonMethods.sendkeys("function_XPATH", Function);
		// String Functiontext=ExcelUtils.getCellData(servicerequest_sheetname,
		// "Function", 1);
		CommonMethods.PickerSelect(Function);
		// Thread.sleep(2000);

		// Enter SubFunction
		CommonMethods.sendkeys("subFunction_XPATH", SubFunction);
		// String SubFunctiontext=ExcelUtils.getCellData(servicerequest_sheetname,
		// "SubFunction", 1);
		CommonMethods.PickerSelect(SubFunction);
		// Thread.sleep(2000);

		// Enter SubSubFunction
		CommonMethods.sendkeys("subsubFunction_XPATH", SubSubFunction);
		// String SubSubFunctiontext=ExcelUtils.getCellData(servicerequest_sheetname,
		// "SubSubFunction", 1);
		CommonMethods.PickerSelect(SubSubFunction);
		Thread.sleep(2000);

		// Select Nature of query
		CommonMethods.sendkeys("NatureOfQuery_XPATH", NatureofQuery);
		// String NatureOfQueryText=ExcelUtils.getCellData(servicerequest_sheetname,
		// "NatureofQuery", 1);
		CommonMethods.PickerSelect(NatureofQuery);
		Thread.sleep(1000);
		CommonMethods.scrollDown(700);

		// Enter Lodgement Remarks
		CommonMethods.sendkeys("remarkfield_XPATH", LodgementRemarks);
		Thread.sleep(1000);

		// Click on save and proceed button
		CommonMethods.Click("saveandProceedButton_XPATH");

		Thread.sleep(10000);
		// Get Sr Number from details page
		String SRNumbertext = CommonMethods.getElementText("SRNumber_XPATH");
		// ExcelUtils.writeToExcel(servicerequest_sheetname, 1, 9, SRNumbertext);
		excel.setCellData(sheetname, "SRNumber", row, SRNumbertext);

		String assignedToText = CommonMethods.getElementText("assignedTotext_XPATH");
		// ExcelUtils.writeToExcel(servicerequest_sheetname, 1, 10, assignedToText);
		// ExcelUtils.writeToExcel(servicerequest_sheetname, "AssignedTo", 1,
		// assignedToText);
		excel.setCellData(sheetname, "AssignedTo", row, assignedToText);

	}

	// create SR for Standard Work FLow for phone banking

	public void createStandardSRPhoneBaking(String Product, String AccountCardDetailSearchParameter, String Function,
			String SubFunction, String SubSubFunction, String NatureofQuery, String LodgementRemarks,
			String Preferrableaddress, String CardAccount, String STATEMENTNOTRECEIVED, String STATEMENTRECEIVEDLATE,
			String TRAVELLING, String CustomerID, String CustomerName, String RBLOCKCARDNO, String sheetname, int row)
			throws Exception {

		CommonMethods.scrollDown(400);
		Thread.sleep(4000);
		// Select Product

		CommonMethods.Click("selectproductarrowbutton_XPATH");
		Thread.sleep(2000);
		
		// String product = ExcelUtils.getCellData(sheetname, "Product",1);
//		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + product + "')]"));
//
//		element.click();

		for (int i = 0; i <= 3; i++) {

			try {

				boolean flag = CommonMethods.productpresent(Product);

				if (flag == true) {
					WebElement ele = driver.findElement(By.xpath("//div[text()= '" + Product + "']"));
					ele.click();

				}

				else {
					WebElement ele = driver.findElement(By.xpath("(//div[@class='react-grid-Cell__value'])[last()]"));
					Thread.sleep(2000);
					CommonMethods.scrollByVisibilityofElement(ele);
					Thread.sleep(2000);
					WebElement ele2 = driver.findElement(By.xpath("//div[text()= '" + Product + "']"));
					ele2.click();

				}
				Thread.sleep(2000);
				break;
			} catch (StaleElementReferenceException e) {

				System.out.println(e.getMessage());
			}
		}
		Thread.sleep(3000);
		// Select accountCardDetailSearchParatameter
		CommonMethods.selectByText("accountCardDetailSearchParatameterDropdown_XPATH",
				AccountCardDetailSearchParameter);

		CommonMethods.scrollDown(400);

		// Enter Function
		CommonMethods.sendkeys("function_XPATH", Function);
		// String Functiontext = ExcelUtils.getCellData(sheetname, "Function", 1);
		CommonMethods.PickerSelect(Function);
		// Thread.sleep(2000);

		// Enter SubFunction
		CommonMethods.sendkeys("subFunction_XPATH", SubFunction);
		// String SubFunctiontext = ExcelUtils.getCellData(sheetname, "SubFunction", 1);
		CommonMethods.PickerSelect(SubFunction);
		// Thread.sleep(2000);

		// Enter SubSubFunction
		CommonMethods.sendkeys("subsubFunction_XPATH", SubSubFunction);
		// String SubSubFunctiontext = ExcelUtils.getCellData(sheetname,
		// "SubSubFunction", 1);
		CommonMethods.PickerSelect(SubSubFunction);
		// Thread.sleep(2000);

		// Select Nature of query
		CommonMethods.sendkeys("NatureOfQuery_XPATH", NatureofQuery);
		// String NatureOfQueryText = ExcelUtils.getCellData(sheetname,
		// "NatureofQuery",1);
		CommonMethods.PickerSelect(NatureofQuery);
		Thread.sleep(1000);
		CommonMethods.scrollDown(700);

		// Enter Lodgement Remarks

		CommonMethods.sendkeys("remarkfield_XPATH", LodgementRemarks);
		// Thread.sleep(2000);

		// Select Preferable Address
		CommonMethods.selectByText("preferableaddressdropdown_XPATH", Preferrableaddress);

		// Select Card Account
		CommonMethods.selectByText("cardAccountdropdown_XPATH", CardAccount);

		// Enter Missed payment date
		CommonMethods.Click("missedpayementdate_XPATH");

		// Enter Statement not Received
		CommonMethods.sendkeys("statementNotReceivedtextbox_XPATH", STATEMENTNOTRECEIVED);

		// Enter Statement Received Late
		Thread.sleep(1000);
		CommonMethods.scrollByVisibilityofElement("statementReceivedLatetextbox_XPATH");
		CommonMethods.sendkeys("statementReceivedLatetextbox_XPATH", STATEMENTRECEIVEDLATE);

		// Enter Travelling
		CommonMethods.sendkeys("Travellingtextbox_XPATH", TRAVELLING);

		// Enter Customer ID
		String customerID = CommonMethods.generateRandomCustomerID();
		// ExcelUtils.writeToExcel(sheetname, 2, 18, customerID);
		excel.setCellData(sheetname, "CustomerID", row, customerID);
		CommonMethods.sendkeys("CustomerIDtextbox_XPATH", customerID);

		// Enter Customer Name
		CommonMethods.sendkeys("CustomerNametextbox_XPATH", CustomerName);

		CommonMethods.scrollByVisibilityofElement("RBlockCardNotextbox_XPATH");
		// Enter RBLOCKCARDNO
		CommonMethods.sendkeys("RBlockCardNotextbox_XPATH", RBLOCKCARDNO);

		// Attach KYC Document
		CommonMethods.clickelementbyjavascript("uploadKYCDocument_XPATH");
		Thread.sleep(3000);
		CommonMethods.fileupload(fileupload_attachement);
		Thread.sleep(3000);

		// Click on save and proceed button
		CommonMethods.Click("saveandProceedButton_XPATH");

		Thread.sleep(20000);
		// Get Sr Number from details page
		String SRNumbertext = CommonMethods.getElementText("SRNumber_XPATH");
		// ExcelUtils.writeToExcel(sheetname, 2, 9, SRNumbertext);
		excel.setCellData(sheetname, "SRNumber", row, SRNumbertext);
	}

	// check fields

	public void checkfields(String sheetname, int rowcountfields) throws IOException {

	}

	// create Sr for dpdis
	public void createDPDISServiceRequest(String Function, String SubFunction, String SubSubFunction,
			String NatureOfQuery, String TypeofRequest, String SlipSeries, String DigitallySignedDocument,
			String SerialNo, String LodgementRemarks, String sheetname, int row) throws Exception {

		CommonMethods.scrollDown(100);

		// Enter Function
		CommonMethods.sendkeys("function_XPATH", Function);

		CommonMethods.PickerSelect(Function);

		// Enter Sub Function
		CommonMethods.sendkeys("subFunction_XPATH", SubFunction);

		CommonMethods.PickerSelect(SubFunction);

		// Enter Sub SubFunction
		CommonMethods.sendkeys("subsubFunction_XPATH", SubSubFunction);

		CommonMethods.PickerSelect(SubSubFunction);

		// Select Nature of query
		CommonMethods.selectByText("NatureOfQuerydropdown_XPATH", NatureOfQuery);

		// Enter Type Of Request
		CommonMethods.sendkeys("typeofrequest_XPATH", TypeofRequest);
		CommonMethods.PickerSelect(TypeofRequest);

		// Enter client id
		String clientid = CommonMethods.generateRandomclientID();
		excel.setCellData(sheetname, "ClientID", row, clientid);
		CommonMethods.scrollByVisibilityofElement("clientID_XPATH");
		CommonMethods.sendkeys("clientID_XPATH", clientid);

		// Enter Slip Series
		CommonMethods.sendkeys("slipseries_XPATH", SlipSeries);

		// Select Digitally signed document
		CommonMethods.selectByText("digitallysigneddocument_XPATH", DigitallySignedDocument);

		// Enter Serial No
		CommonMethods.sendkeys("serialNo_XPATH", SerialNo);

		// Enter Lodgement Remarks
		CommonMethods.sendkeys("dpdislodgementremarks_XPATH", LodgementRemarks);

		// Enter customer id
		String customerid = CommonMethods.generateRandomCustomerID();
		excel.setCellData(sheetname, "CustomerID", row, customerid);
		CommonMethods.sendkeys("dpdiscustomerID_XPATH", customerid);

		// Click on save and proceed button
		CommonMethods.Click("saveandProceedButton_XPATH");

		Thread.sleep(20000);
		String SRNumbertext = CommonMethods.getElementText("SRNumber_XPATH");

		excel.setCellData(sheetname, "SRNumber", row, SRNumbertext);

		// write Internal Expected CLosure Date to excel
		String Internalexpectedclosuredate = dateTimesplit();
		excel.setCellData(sheetname, "Date", row, Internalexpectedclosuredate);

	}

	// Select internal exp closure date

	public String dateTimesplit() throws Exception {

		String s1 = CommonMethods.getElementText("InternalExpclosuredateUI_XPATH");
		String[] words = s1.split("\\s");
		String dateText = words[0];
		return dateText;

	}

	// generate random corporate name
	public String enterRandomCorporateName() {
		// int num=generateRandomNumber();
		Random random = new Random();
		// int randomInt = random.nextInt(1000000000);
		String id = String.format("%04d", random.nextInt(10000));
		String corporateid = String.valueOf(id);
		String corporatename = "corporatename" + corporateid;

		return corporatename;
	}

//		public void verifystandardCTST(String sheetname,int rownum) throws Exception {
//			
//				
//
//				//Select Lodgement Team
//				CommonMethods.Click("lodgementTeamSearchicon_XPATH");
//	            CommonMethods.sendkeys("inputSearchTextBox_XPATH", sheetname, "LodgementUserTeam", rownum);
//				
//				String LodgementUserTeamext=ExcelUtils.getCellData(sheetname, "LodgementUserTeam",rownum);
//				//Click On Filter arrow button
//				CommonMethods.Click("filterarrowbutton_XPATH");
//				
//				try {
//				WebElement elementtext = driver.findElement(By.xpath("//div[contains(text(),'" + LodgementUserTeamext + "')]"));
//				Thread.sleep(2000);
//				elementtext.click();
//				}
//				catch(StaleElementReferenceException e) {
//					WebElement elementtext = driver.findElement(By.xpath("//div[contains(text(),'" + LodgementUserTeamext + "')]"));
//					Thread.sleep(2000);
//					elementtext.click();
//				}
//				Thread.sleep(2000);
//				
//				CommonMethods.scrollDown(300);
//				
//				//Select Product
//				CommonMethods.Click("selectproductarrowbutton_XPATH");
//				String product=ExcelUtils.getCellData(sheetname, "Product", rownum);
//				WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + product + "')]"));
//				element.click();
//				
//				//Select accountCardDetailSearchParatameter
//				CommonMethods.selectByText("accountCardDetailSearchParatameterDropdown_XPATH", sheetname, "AccountCardDetailSearchParameter",rownum);
//				Thread.sleep(2000);
//				
//				 //ENter CIF ID
//				  CommonMethods.sendkeys("inputCIFID_XPATH", sheetname, "CIFID", rownum);
//				  Thread.sleep(2000);
//				  CommonMethods. enterTAB("inputCIFID_XPATH");
//				  
//				  
//				CommonMethods.scrollDown(400);
//				
//				
//				//Enter Function
//				CommonMethods.sendkeys("function_XPATH", sheetname, "Function", rownum);
//				String Functiontext=ExcelUtils.getCellData(sheetname, "Function", rownum);
//				
//				
//				CommonMethods.PickerSelect(Functiontext);
//				Thread.sleep(2000);
//				
//				//Enter SubFunction
//				CommonMethods.sendkeys("subFunction_XPATH",sheetname, "SubFunction", rownum);
//				String SubFunctiontext=ExcelUtils.getCellData(sheetname, "SubFunction", rownum);
//				CommonMethods.PickerSelect(SubFunctiontext);
//				Thread.sleep(2000);
//				
//				//Enter SubSubFunction
//				CommonMethods.sendkeys("subsubFunction_XPATH", sheetname, "SubSubFunction", rownum);
//				String SubSubFunctiontext=ExcelUtils.getCellData(sheetname, "SubSubFunction", rownum);
//				CommonMethods.PickerSelect(SubSubFunctiontext);
//				Thread.sleep(10000);
//				
//				//Select Nature of query
//				CommonMethods.sendkeys("NatureOfQuery_XPATH", sheetname, "NatureofQuery", rownum);
//				String NatureOfQueryText=ExcelUtils.getCellData(sheetname, "NatureofQuery",rownum);
//				CommonMethods.PickerSelect(NatureOfQueryText);
//				Thread.sleep(3000);
//				
//				//CommonMethods.scrollDown(700);
//				CommonMethods.scrollByVisibilityofElement("remarkfield_XPATH");
//				//Enter Lodgement Remarks
//				CommonMethods.sendkeys("remarkfield_XPATH", sheetname, "LodgementRemarks", rownum);
//				  Thread.sleep(2000);
//				  
//
//				 //ENter CIF ID
//				//  CommonMethods.sendkeys("inputCIFID_XPATH", sheetname, "CIFID", rownum);
//				  Thread.sleep(2000);
//				 // CommonMethods. enterTAB("inputCIFID_XPATH");
//			
//				  //Thread.sleep(2000);
//		}
//		

	// create SR for CMS Process

	public String createSRCMSFlow(String Product, String Function, String SubFunction, String SubSubFunction,
			String NatureofQuery, String BriefDescription, String ProductType, String PickUpFrequency,
			String PickUpAddress, String sheetname, int row) throws Exception {

		// Enter Product
		CommonMethods.sendkeys("Product_XPATH", Product);
		// String productText = ExcelUtils.getCellData(sheetname, "Product", 1);
		CommonMethods.PickerSelect(Product);

		// Enter Function
		CommonMethods.sendkeys("function_XPATH", Function);
		// String Functiontext = ExcelUtils.getCellData(sheetname, "Function", 1);
		CommonMethods.PickerSelect(Function);

		// Enter SubFunction
		CommonMethods.sendkeys("subFunction_XPATH", SubFunction);
		// String SubFunctiontext = ExcelUtils.getCellData(sheetname, "SubFunction", 1);
		CommonMethods.PickerSelect(SubFunction);

		// Enter SubSubFunction
		CommonMethods.sendkeys("subsubFunction_XPATH", SubSubFunction);
		// String SubSubFunctiontext = ExcelUtils.getCellData(sheetname,
		// "SubSubFunction", 1);
		CommonMethods.PickerSelect(SubSubFunction);

		// Select Nature of query
		CommonMethods.selectByText("NatureOfQuerydropdown_XPATH", NatureofQuery);

		// Enter Brief Description
		CommonMethods.scrollByVisibilityofElement("remarkfield_XPATH");
		CommonMethods.sendkeys("remarkfield_XPATH", BriefDescription);

		// Enter Client Code
		String clientCode = CommonMethods.generateRandomclientID();
		// ExcelUtils.writeToExcel(sheetname, 1, 7, clientCode);
		excel.setCellData(sheetname, "ClientCode", row, clientCode);
		CommonMethods.scrollByVisibilityofElement("clientcode_XPATH");
		CommonMethods.sendkeys("clientcode_XPATH", clientCode);

		// Select Product Type

		CommonMethods.selectByText("productTypeDropdown_XPATH", ProductType);

		// select Pick Up Frequency
		CommonMethods.selectByText("pickUpFrequencyDropdown_XPATH", PickUpFrequency);

		// ENter Pick Up Address
		CommonMethods.sendkeys("pickUpAddress_XPATH", PickUpAddress);

		// Enter Corporate Name
		String corporatenametext = enterRandomCorporateName();
		// ExcelUtils.writeToExcel(sheetname, 1, 11, corporatenametext);
		excel.setCellData(sheetname, "CorporateName", row, corporatenametext);
		CommonMethods.sendkeys("corporateName_XPATH", corporatenametext);

		// Enter CIF ID
		String cifidtext = CommonMethods.generateRandomCustomerID();
		// ExcelUtils.writeToExcel(sheetname, 1, 12, cifidtext);
		excel.setCellData(sheetname, "CIFID", row, cifidtext);
		CommonMethods.sendkeys("CIFID_XPATH", cifidtext);

		// Enter Communication Email ID
		String emailidtext = CommonMethods.generateRandomEmail();
		// ExcelUtils.writeToExcel(sheetname, 1, 13, emailidtext);
		excel.setCellData(sheetname, "CommunicationEmailId", 1, emailidtext);

		CommonMethods.sendkeys("communicationemailId_XPATH", emailidtext);
		// Click on save and proceed button

		CommonMethods.Click("saveandProceedButton_XPATH");

		Thread.sleep(20000);

		String SRNumbertext = CommonMethods.getElementText("SRNumber_XPATH");
		excel.setCellData(sheetname, "SRNumber", row, SRNumbertext);
		return SRNumbertext;

	}

	// Create SR For CMS EDS flow
	public void createCMSSRForEDS(String Product, String Function, String SubFunction, String SubSubFunction,
			String Natureofquery, String BriefQueryComplaintDescription, String ClientCode, String sheetname, int row)
			throws Exception {

		// Select Product
		CommonMethods.sendkeys("Product_XPATH", Product);
		CommonMethods.PickerSelect(Product);
		Thread.sleep(2000);

		// Enter Function
		CommonMethods.sendkeys("function_XPATH", Function);
		CommonMethods.PickerSelect(Function);
		Thread.sleep(2000);

		// Enter SubFunction

		CommonMethods.sendkeys("subFunction_XPATH", SubFunction);
		CommonMethods.PickerSelect(SubFunction);
		Thread.sleep(2000);

		// Enter SubSubFunction

		CommonMethods.sendkeys("subsubFunction_XPATH", SubSubFunction);
		CommonMethods.PickerSelect(SubSubFunction);
		Thread.sleep(2000);

		// Select Nature of query
		CommonMethods.selectByText("NatureOfQuerydropdown_XPATH", Natureofquery);

		CommonMethods.scrollDown(200);

		// Enter Brief Query Complaint Description

		CommonMethods.sendkeys("remarkfield_XPATH", BriefQueryComplaintDescription);
		Thread.sleep(2000);

		//CommonMethods.scrollDown(100);
        CommonMethods.scrollByVisibilityofElement("clientcode_XPATH");
		Thread.sleep(2000);

		// Enter Client code
		CommonMethods.sendkeys("clientcode_XPATH", ClientCode);
		// CommonMethods.sendkeys("clientcode_XPATH", "EX1234");
		Thread.sleep(5000);

		// Enter Tab
		CommonMethods.enterTAB("clientcode_XPATH");
		Thread.sleep(5000);

		// Click on pick up code

		CommonMethods.clickelementbyjavascript("FetchAndSelectPickupCode_XPATH");
		Thread.sleep(3000);

		CommonMethods.scrollDown(200);

		// select1st pickup code from list

		CommonMethods.clickelementbyjavascript("selectfirstpickupcode_XPATH");

		CommonMethods.scrollDown(100);

		// Select email id from list
		CommonMethods.clickelementbyjavascript("FetchAndSelectEmailID_XPATH");
		Thread.sleep(3000);

		CommonMethods.scrollDown(100);
		// select 1st email id from list
		CommonMethods.clickelementbyjavascript("firstemailfromlist_XPATH");

		CommonMethods.scrollDown(100);
	}

	// Select Product type from the list
	public void selectProductFields(String ProductType) throws Exception {
		CommonMethods.selectByText("productTypeDropdown_XPATH", ProductType);
		Thread.sleep(3000);

	}

	// Select pickup frequency and pick up address

	public void selectPickupFrequency(String PickupFrequency) throws Exception {

		// Select pick up frequency
		CommonMethods.selectByText("pickUpFrequencyDropdown_XPATH", PickupFrequency);
		Thread.sleep(3000);
		// Enter Pick up address
		// CommonMethods.sendkeys("pickUpAddress_XPATH", "Vishnu prasad complex,virar
		// west,pin-401303");

	}

	// Select pick up address

	public void selectPickupAddress(String PickUpAddress) throws Exception {

		// Enter Pick up address
		CommonMethods.sendkeys("pickUpAddress_XPATH", PickUpAddress);

	}

	// Click on save and proceed button
	public void createSREDS(String sheetname, int row) throws Exception {

		// click on save and proceed
		CommonMethods.Click("saveandProceedButton_XPATH");
		Thread.sleep(10000);

		// Click on ignore and create
		CommonMethods.clickelementbyjavascript("ignore&create_XPATH");

		// Get SR number in the excel sheet
		String SRNumbertext = CommonMethods.getElementText("SRNumber_XPATH");
		excel.setCellData(sheetname, "SRNumber", row, SRNumbertext);

	}

	// create SR for dedupe rule
	public void createSRDedupeRule(String Product, String Function, String SubFunction, String SubSubFunction,
			String NatureofQuery, String BriefDescription, String ClientCode, String ProductType,
			String PickUpFrequency, String PickUpAddress, String sheetname, int row) throws Exception {

		// Enter Product
		CommonMethods.sendkeys("Product_XPATH", Product);

		CommonMethods.PickerSelect(Product);

		// Enter Function
		CommonMethods.sendkeys("function_XPATH", Function);

		CommonMethods.PickerSelect(Function);

		// Enter SubFunction
		CommonMethods.sendkeys("subFunction_XPATH", SubFunction);

		CommonMethods.PickerSelect(SubFunction);

		// Enter SubSubFunction
		CommonMethods.sendkeys("subsubFunction_XPATH", SubSubFunction);

		CommonMethods.PickerSelect(SubSubFunction);

		// Select Nature of query
		CommonMethods.selectByText("NatureOfQuerydropdown_XPATH", NatureofQuery);

		// Enter Brief Description
		CommonMethods.scrollByVisibilityofElement("remarkfield_XPATH");
		CommonMethods.sendkeys("remarkfield_XPATH", BriefDescription);

		// Enter Client Code

		CommonMethods.scrollByVisibilityofElement("clientcode_XPATH");
		String clientcode = excel.getCellData(sheetname, "ClientCode", row);
		CommonMethods.sendkeys("clientcode_XPATH", clientcode);

		// Select Product Type
		CommonMethods.selectByText("productTypeDropdown_XPATH", ProductType);

		// select Pick Up Frequency
		CommonMethods.selectByText("pickUpFrequencyDropdown_XPATH", PickUpFrequency);

		// ENter Pick Up Address

		CommonMethods.sendkeys("pickUpAddress_XPATH", PickUpAddress);

		// Click on save and proceed button

		CommonMethods.Click("saveandProceedButton_XPATH");

		Thread.sleep(3000);

		// Verify Dedupe msg

		CommonMethods.isElementDisplayed("dedupeDuplicateRecordListing_XPATH");
		// boolean
		// duplicaterecordlistingtext=dedupeDuplicateRecordListing.isDisplayed();
		// Assert.assertTrue(duplicaterecordlistingtext, "Duplicate Record Listing not
		// Present");

		// verify Dedupe Text msg
		// CommonMethods.getElementText(dedupemsg,"Dedupe Msg");

		CommonMethods.getElementText("dedupemsg_XPATH");
		// Assert.assertEquals(msg, "Dedupe Msg", "Message not present");

		// close pop up
		CommonMethods.Click("closeDedupePopup_XPATH");

	}

}
