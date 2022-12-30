package com.crm.pages;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.aventstack.extentreports.model.Test;
import com.crm.base.TestBase;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;


public class ReportsPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(ReportsPage.class);
	public ReportsPage(WebDriver driver) {
		this.driver = driver;

		
	}

	HomePage homepage = new HomePage(driver);
	// Methods starts here

	// verify reports open properly
	public void verifyreportsopenproperly() throws InterruptedException, IOException {
        //Enter report name in search text box
		CommonMethods.sendkeys("searchtextboxReportsPage_XPATH", "dpdis");
		
		//click on filter arrow button
		CommonMethods.Click("filterarrowbuttonReportsPage_XPATH");
		Thread.sleep(4000);
		
		//Expand Reports section
		CommonMethods.Click("cardgroupsectionReportsPage_XPATH");
		Thread.sleep(3000);
		//click on first report link
		CommonMethods.Click("firstreportlistReportsPage_XPATH");
		Thread.sleep(3000);
		//switch to window
		CommonMethods.switchToWindow();
		
		//maximize window
		CommonMethods.maximizeWindow();
		Thread.sleep(3000);
		//verify reports window opens
		CommonMethods.isElementDisplayed("reportsbodyReportsPage_XPATH");
		
		
		Thread.sleep(2000);
		
		//switch to parent window
		CommonMethods.switchtoparentwindow();
		
		
		
	}
	
	
	//verify Execute All Records Reports
	
	public void verifyExecuteAllRecordReport(String srnumber) throws Exception {
	/**	// Enter report name in search text box
		CommonMethods.sendkeys("searchtextboxReportsPage_XPATH", "dpdis");

		// click on filter arrow button
		CommonMethods.Click("filterarrowbuttonReportsPage_XPATH");
		Thread.sleep(2000);

		// Expand Reports section
		CommonMethods.Click("cardgroupsectionReportsPage_XPATH");

		// click on first report link
		CommonMethods.Click("firstreportlistReportsPage_XPATH");

		// switch to window
		CommonMethods.switchToWindow();

		// maximize window
		CommonMethods.maximizeWindow();
		
		//click On Execute Button
		CommonMethods.Click("executebuttonReportsPage_XPATH");
		
		
	**/	
		
		//click On Apply Button
		CommonMethods.Click("ApplybuttonReportsPage_XPATH");
		Thread.sleep(3000);
		
		//verify records are displayed
		CommonMethods.isElementDisplayed("recordsdisplayReportsPage_XPATH");
		
		
		//verify SR created displayed in Reports
		String expsrnumber=CommonMethods.getElementText("srnumberReportsPage_XPATH");
		Assert.assertEquals(srnumber, expsrnumber, "SR number in Reports Page is not as expected");
		
		
		Thread.sleep(2000);
		ScreenShot.screenshot("TC43_Execute All Records Displayed");
		//switch to parent window
	    CommonMethods.switchtoparentwindow();

	}
	
	
	//verify reports added to quick links
	
	public void verifyreportsaddedtoquicklinks() throws Exception {
		// Enter report name in search text box
		CommonMethods.sendkeys("searchtextboxReportsPage_XPATH", "dpdis");

		// click on filter arrow button
		CommonMethods.Click("filterarrowbuttonReportsPage_XPATH");
		Thread.sleep(2000);

		// Expand Reports section
		CommonMethods.Click("cardgroupsectionReportsPage_XPATH");

		// click on first report link
		CommonMethods.Click("firstreportlistReportsPage_XPATH");

		// switch to window
		CommonMethods.switchToWindow();

		// maximize window
		CommonMethods.maximizeWindow();
		if(!CommonMethods.isElementPresent("addToQuickLinksReportsPage_XPATH")){
			CommonMethods.Click("removefromquicklinksReportsPage_XPATH");
		}
		
		Thread.sleep(3000);	
		//click on Add to Quick Link Button
		CommonMethods.Click("addToQuickLinksReportsPage_XPATH");
		//
		Thread.sleep(2000);
		
		//switch to parent window
	    CommonMethods.switchtoparentwindow();
	    Thread.sleep(2000);
       //move to quick link
	    homepage.moveToQuickLink();
	    Thread.sleep(2000);
	    //verify Report added to Quick linkk
	    CommonMethods.isElementDisplayed("reportinquicklink_XPATH");
	    //Assert.assertTrue(driver.findElement(By.xpath(CommonMethods.readPropertyFile("reportinquicklink_XPATH"))).isDisplayed());
	    ScreenShot.screenshot("TC44_Reports Add To Quick Link");
	}
	
	
	//verify list of reports display
	public void listofreportsdisplay() throws IOException, Exception {
	    boolean match=false;
		int listsize=CommonMethods.getElementsListCount("listofreportsdisplayReportsPage_XPATH");
		
		if(listsize > 1) {
			match=true;
			
		}
		Assert.assertTrue(match, "List of reports not display");
		
	}
	
	
	
	//verify list of filters display
	
	public void verifylistoffilters() throws Exception {
		
		//boolean match =false;
		CommonMethods.Click("filterbuttonReportsPage_XPATH");
		
		List<String> listtext=CommonMethods.getElementsTextList("listoffiltersReportsPage_XPATH");
		System.out.println("List of Filters"+ listtext);
	}
	
	public void searchDPDISReport() throws InterruptedException {
		
		// Enter report name in search text box
		CommonMethods.sendkeys("searchtextboxReportsPage_XPATH", "dpdis");

		// click on filter arrow button
		CommonMethods.Click("filterarrowbuttonReportsPage_XPATH");
		Thread.sleep(4000);

		// Expand Reports section
		CommonMethods.Click("cardgroupsectionReportsPage_XPATH");
		Thread.sleep(2000);
		// click on first report link
		CommonMethods.Click("firstreportlistReportsPage_XPATH");
		Thread.sleep(2000);
		// switch to window
		CommonMethods.switchToWindow();
		
		// maximize window
		CommonMethods.maximizeWindow();
		Thread.sleep(4000);
		//click On Execute Button
		CommonMethods.Click("executebuttonReportsPage_XPATH");
		
		//click On Filter Button
		CommonMethods.Click("filterbuttonReportsPage_XPATH");
		
		//click On Clear Filter  Button
		CommonMethods.Click("ClearFilterButtonReportsPage_XPATH");
		
		//click On Apply Button
		//CommonMethods.Click("ApplybuttonReportsPage_XPATH");
	}

	
	// select Created On Filter
	public void selectCreatedOnFilter() throws InterruptedException {
		Thread.sleep(1000);
		//select created on Filter
		CommonMethods.selectByText("createdOnFilterReportsPage_XPATH", "Equal");
		
		//select todays Date
		CommonMethods.Click("CreatedTodaysDateReportsPage_XPATH");

	}
	
	// select Internal Expected Closure Date On Filter
	public void selectInternalExpectedCLosureDateFilter(String sheetname,int row) throws InterruptedException, EncryptedDocumentException, IOException {
		Thread.sleep(1000);
		// select Internal Expected Closure Date Filter
		CommonMethods.selectByText("InternalexpclosuredatefilterReportsPage_XPATH", "Equal");

		String Date=excel.getCellData(sheetname, "Date", row);
		// Enter Internal Expected Closure Date
		CommonMethods.sendkeys("InternalexpclosuredateTextBoxReportsPage_XPATH", Date);

	}
		
	// select SR Number Filter
	public void selectSRNumberFilter(String sheetname,int row) throws InterruptedException, EncryptedDocumentException, IOException {
		Thread.sleep(1000);
		// select SR Number Filter
		CommonMethods.selectByText("SRNumberfilterReportsPage_XPATH", "Equal");
		String SRNumber=excel.getCellData(sheetname, "SRNumber", row);
		// Enter SR Number
		CommonMethods.sendkeys("SRNumberTextBoxReportsPage_XPATH", SRNumber);

	}

	// select Status Code Filter
	public void selectStatusCodeFilter(String StatusCode) throws Exception {
		Thread.sleep(1000);
		// select Status Code Filter
		CommonMethods.selectByText("statusCodeFilterReportsPage_XPATH", "Equal");
        
		//click On Status Code Search icon
		CommonMethods.Click("SearchIconStatusCodeReportsPage_XPATH");
		
		// Enter Status Code
		CommonMethods.sendkeys("SearchTextBoxReportsPage_XPATH", StatusCode);
       
		//CLick On Arrow Button
		CommonMethods.Click("statusCodeArrowButtonReportsPage_XPATH");
		
		Thread.sleep(3000);
		//select First Check Box
		CommonMethods.mouseClick("SelectFirstCheckBoxReportsPage_Xpath");
		
		//CLick On Ok Button
		CommonMethods.Click("okButtonReportsPage_XPATH");
	}
	
	
	
	//select Source Filter
	public void selectSourceFilter(String Source) throws Exception {
		Thread.sleep(1000);
		// select Source Filter
		CommonMethods.selectByText("sourceFilterReportsPage_XPATH", "Equal");
		        
		//click On Source Search icon
		CommonMethods.Click("sourceSearchIconReportsPage_XPATH");
		// Enter Source
		CommonMethods.sendkeys("SearchTextBoxReportsPage_XPATH", Source);
		       
		//CLick On Arrow Button
		CommonMethods.Click("ArrowButtonReportsPage_XPATH");
		Thread.sleep(4000);
		//select First Check Box
		CommonMethods.mouseClick("selectSourceCheckboxReportsPage_XPATH");
		Thread.sleep(2000);
		//CLick On Ok Button
		CommonMethods.Click("okButtonReportsPage_XPATH");
		Thread.sleep(1000);
	}
	
	
	//select SUb SUb Function Filter
	public void selectSubSubFunctionFilter(String SubSubFunction) throws Exception {
		Thread.sleep(1000);
		// select SUb SUb Function Filter
		CommonMethods.selectByText("subSubFuctionFilterReportspage_XPATH", "Equal");
		
        CommonMethods.scrollByVisibilityofElement("subsSubFunctionSearchIconReportsPage_XPATH");
        Thread.sleep(1000);
		// click On SUb SUb Function Search icon
		CommonMethods.Click("subsSubFunctionSearchIconReportsPage_XPATH");
		
		// Enter SUb SUb Function
		CommonMethods.sendkeys("SearchTextBoxReportsPage_XPATH", SubSubFunction);

		// CLick On Arrow Button
		CommonMethods.Click("subSubFunctionArrowButtonReportsPage_XPATH");
		Thread.sleep(3000);
		// select First Check Box
		CommonMethods.mouseClick("SelectFirstCheckBoxReportsPage_Xpath");
		Thread.sleep(2000);
		// CLick On Ok Button
		CommonMethods.Click("okButtonReportsPage_XPATH");	
		Thread.sleep(2000);
	}
	//select Nature Of Query Filter
		public void selectNatureOfQueryFilter(String NatureOfQuery) throws Exception {
			Thread.sleep(1000);
			// select Nature Of Query Filter
			CommonMethods.selectByText("natureOfQueryFilter_XPATH", "Equal");
			
			CommonMethods.scrollByVisibilityofElement("natureOfQuerySearchIcon_XPATH");
			
			// click On Nature Of Query Search icon
			CommonMethods.Click("natureOfQuerySearchIcon_XPATH");
			
			// Enter Nature Of Query
			CommonMethods.sendkeys("SearchTextBoxReportsPage_XPATH", NatureOfQuery);
			Thread.sleep(1000);
			// CLick On Arrow Button
			CommonMethods.Click("natureOfQueryArrowButtonReportsPage_XPATH");
			Thread.sleep(4000);
			// select First Check Box
			CommonMethods.mouseClick("SelectFirstCheckBoxReportsPage_Xpath");
			Thread.sleep(4000);
			// CLick On Ok Button
			CommonMethods.Click("okButtonReportsPage_XPATH");	
			Thread.sleep(2000);
		}
		
		
		
		//select AssignedToUser Filter
		public void selectAssignedToUserFilter(String AssignedToUser) throws Exception {
			Thread.sleep(1000);
			// select AssignedToUser Filter
			CommonMethods.selectByText("assignedToUserFilter_XPATH", "Equal");

			// Enter AssignedToUser
			CommonMethods.sendkeys("assignedToTextBox_XPATH", AssignedToUser);

			Thread.sleep(2000);
	}
		
		
		//click On Apply Button
		public void clickOnApplyButton() {
			
			CommonMethods.Click("ApplyButtonReportsPage_XPATH");
		}
		
		
		//verify SR created displayed in Reports
		
		public void verifySRNumberFilter(String SRNumber) throws Exception {
			
			//String actualsrnumber=ExcelUtils.getCellData(sheetname, "SRNumber", 1);
			String expsrnumber = CommonMethods.getElementText("srnumberReportsPage_XPATH");
			Assert.assertEquals(SRNumber, expsrnumber, "SR number in Reports Page is not as expected");
		}

	}
