package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;

import com.crm.utilities.ScreenShot;


public class OffersAndLeadPage extends TestBase {

	public static Logger log = LoggerFactory.getLogger(OffersAndLeadPage.class);

	// Constructor
	public OffersAndLeadPage(WebDriver driver) {

		this.driver = driver;

	}

	// **************Methods********************

	// click On New Button On Home Page
	public void clickOnNewButton() throws Exception {

		CommonMethods.mouseHover("newButton_XPATH");
		CommonMethods.highlightelement("newButton_XPATH");
		CommonMethods.Click("newButton_XPATH");

	}

	// click on New Offer Link
	public void clickNewOfferLink() throws Exception {

		CommonMethods.highlightelement("newOffer_XPATH");
		CommonMethods.mouseHover("newOffer_XPATH");
		Thread.sleep(2000);
		CommonMethods.highlightelement("newOfferlayout_XPATH");
		CommonMethods.Click("newOfferlayout_XPATH");

	}

	// click on New Lead Link
	public void clickNewLeadLink() throws Exception {

		CommonMethods.highlightelement("newLead_XPATH");
		CommonMethods.mouseHover("newLead_XPATH");
		Thread.sleep(2000);
		CommonMethods.highlightelement("newLeadlayout_XPATH");
		CommonMethods.Click("newLeadlayout_XPATH");
	}

	// create New Offer on existing Customer
	public void createNewOffer1(String SubProduct,String HotRating,String sheetname,int row) throws Exception {

		// click On New Offer Link
		clickNewOfferLink();
		Thread.sleep(3000);

		// switch window
		CommonMethods.switchToWindow();

		// maximize window
		CommonMethods.maximizeWindow();

		// Select Sub Product
		CommonMethods.selectByText("offerSubProduct_XPATH",SubProduct);

		// select Hot rating Value
		CommonMethods.selectByText("offerHotRating_XPATH",HotRating);

		// click on save button
		CommonMethods.Click("saveButtonOffersleadPage_XPATH");

		Thread.sleep(10000);

		// Switch to parent window
		CommonMethods.switchtoparentwindow();

		// get Offer name created
		String offername = CommonMethods.getElementText("offerCreated_XPATH");
		log.info("New Offer Created On Existing Customer with Offer Name:" + offername);
		CustomListener.extentInfo("New Offer Created On Existing Customer with Offer Name:", " : " + offername);
		//ScreenShot.takeScreenShot("Offer Creation");
	}

//create Lead
	public void createNewLead1(String Product,String Subproduct,String Branch,String LeadSource,
			String Description, String sheetname,int row) throws Exception {

		// Click On New lead From Layout
		clickNewLeadLink();
		Thread.sleep(2000);

		// Switch to window
		CommonMethods.switchToWindow();

		// Maximize window
		CommonMethods.maximizeWindow();
		Thread.sleep(2000);
		CommonMethods.scrollDown(400);
		// Enter Product
		CommonMethods.sendkeys("productLead_XPATH", Product);
		CommonMethods.Click("table_XPATH");

		// Enter SUb Product
		CommonMethods.selectByText("subProductDropdown_XPATH", Subproduct);
		Thread.sleep(3000);

		// Enter Branch
		CommonMethods.scrollByVisibilityofElement("branchLead_XPATH");
		CommonMethods.sendkeys("branchLead_XPATH", Branch);
		CommonMethods.Click("table_XPATH");

		// Enter Lead source
		CommonMethods.selectByText("leadSourcedropdown_XPATH",LeadSource);

		CommonMethods.scrollDown(400);
		Thread.sleep(3000);

		// Enter Lead Description
		CommonMethods.sendkeys("leadDescription_XPATH", Description);

		// click on save
		CommonMethods.Click("saveButtonOffersleadPage_XPATH");
		Thread.sleep(5000);

		// switch to parent window
		CommonMethods.switchtoparentwindow();

		// get Lead created text
		CommonMethods.getElementText("leadCreated_XPATH");

		//ScreenShot.takeScreenShot("Lead Creation");

	}

	// click on New Offer Link
	public void clickEditOfferButton() throws Exception {

		CommonMethods.highlightelement("EditOfferButton_XPATH");
		CommonMethods.Click("EditOfferButton_XPATH");
	}

	// verify Offer Page opens from layout
	public void verifyOfferPageOpen() throws IOException {
		String actualtitle = "Offer - CRMnext - Smart.Easy.Complete";

		CommonMethods.verifyPageTitle(actualtitle);
		ScreenShot.screenshot("Offer Page Opens");
	}

	// create offer

	public void createOffer(String Priority,String Product,String SubProduct,String sheetname,int row) throws Exception   {
		
		
		//select Priority
		
		CommonMethods.selectByText("priorityDropdown_XPATH", sheetname, "Priority", 1);
		
		//ENter Product
		CommonMethods.scrollByVisibilityofElement("offerProduct_XPATH");
		Thread.sleep(2000);
		CommonMethods.sendkeys("offerProduct_XPATH", Product);
		CommonMethods.PickerSelect("pickerSelect_XPATH",Product);
		
		Thread.sleep(2000);
		//select subproduct
		CommonMethods.selectByText("offerSubProduct_XPATH", SubProduct);
		
		//select valid from date 
		CommonMethods.scrollByVisibilityofElement("offerFromDate_XPATH");
		CommonMethods.Click("offerFromDate_XPATH");
		
		//select to date
		
		CommonMethods.Click("offerTillDate_XPATH");
		
		//click on save button
		
		CommonMethods.Click("saveButtonOffersleadPage_XPATH");
		
		Thread.sleep(3000);
		updateOffersAllSheet(Priority,Product,SubProduct,row);
	}
	
      public void updateOffersAllSheet(String Priority,String Product,String SubProduct,int row) throws Exception {
		
		excel.setCellData("VerifyEditOnOffers", "Priority", row, Priority);
		excel.setCellData("VerifyEditOnOffers", "SubProduct", row, SubProduct);
		excel.setCellData("VerifyEditOnOffers", "Product", row, Product);
		
	}
	
	
	//click on Home Bread crumb
	public void clickHome() {
		CommonMethods.Click("HomeBreadcrumbTab_XPATH");
	}
	
	
	
	
	
	//verify Edit Offer 
	public void verifyEditOfferPage(String Priority,String Product,String SubProduct) throws Exception {
		
		//offer priority
	      String actualprioritytext= CommonMethods. getFirstSelectedOption("priorityDropdown_XPATH");
	     // String expprioritytext = ExcelUtils.getCellData(sheetname, "Priority", 1);
	      Assert.assertEquals(actualprioritytext, Priority, "Mismatched Priority");
	      
		
		 //product
	     // String actualproduct=productTextBox.getAttribute("value");
	      String actualproduct=CommonMethods.getAttribute("productTextBox_XPATH");
	    //  String expproduct=ExcelUtils.getCellData(sheetname, "Product", 1);
	      Assert.assertEquals(actualproduct, Product, "Mismatched product");
	      
		//offer subproduct
	      String actualoffersubproducttext= CommonMethods. getFirstSelectedOption("offerSubProduct_XPATH");
	     // String expsubproduct = ExcelUtils.getCellData(sheetname, "SubProduct", 1);
	      Assert.assertEquals(actualoffersubproducttext, SubProduct, "Mismatched sub product");
	      
	      
	      Thread.sleep(2000);
			//ScreenShot.screenshot("Edit Offer Page");
	      
		
	}
}
