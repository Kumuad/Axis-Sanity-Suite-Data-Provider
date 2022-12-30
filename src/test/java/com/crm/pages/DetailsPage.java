package com.crm.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DetailsPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(DetailsPage.class);
	public DetailsPage(WebDriver driver) {
		this.driver = driver;
		
	}
	public static Properties config = TestBase.loadConfig();
	public static String fileuploadpath=System.getProperty("user.dir") +config.getProperty("FileUpload");
	public static String xlfile=System.getProperty("user.dir") +config.getProperty("ImportMappingUpload");
	
	
	
	// Methods--

	// get SR Number from Details Page
	public String getSRNumber() throws Exception {

		return CommonMethods.getElementText("SRNumberDetailspage_XPATH");
		// return SRNumber.getText();
	}

	// get Assigned to from Details Page
		public String getAssignedTo() throws Exception {

			return CommonMethods.getElementText("assignedTotext_XPATH");
			
		}
	
	
	// get Status code from Details Page
	public String getStatuscodeDetailsPage() throws Exception {

		return CommonMethods.getElementText("statuscodeDetailspage_XPATH");

	}

	// get Client Code From Details Page
	public String getClientCode() throws Exception {

		return CommonMethods.getElementText("clientcodeDetailspage_XPATH");

	}

	// get Email ID from Details Page
	public String getEmailID() throws Exception {

		return CommonMethods.getElementText("communicationemailidDetailspage_XPATH");

	}

	// get CIF ID from Details Page
	public String getCIFID() throws Exception {

		return CommonMethods.getElementText("cifidDetailspage_XPATH");

	}

	// get Corporate Name from Details Page
	public String getCorporateName() throws Exception {

		return CommonMethods.getElementText("corporatenameDetailspage_XPATH");

	}

	// click On Import Child SR Button
  
	
	public void clickOnImportChildSRButton() throws Exception {
		
		CommonMethods.highlightelement("ImportChildSRButtonDetailspage_XPATH");
		CommonMethods.Click("ImportChildSRButtonDetailspage_XPATH");
	}
	
	public void uploadFileImport(String SRNumber) throws Exception {
		
		//write SR number to csv file
		CommonMethods.writeDataAtOnce(xlfile,SRNumber);
		
		Thread.sleep(5000);
		CommonMethods.switchToWindow();
		Thread.sleep(3000);
		CommonMethods.maximizeWindow();
		Thread.sleep(3000);
		
		//click on chose file button
		CommonMethods.mouseClick("chosefilebutton_XPATH");
		
		Thread.sleep(3000);
		//upload file for import
		CommonMethods.fileupload(xlfile);
		Thread.sleep(3000);
		
		//click on finish button
		CommonMethods.Click("Finishbutton_XPATH");
		Thread.sleep(10000);
		
		//click on navigate to back link
		CommonMethods.Click("clicktonavigateLink_XPATH");
		Thread.sleep(3000);
		//switch to parent window
		CommonMethods.switchtoparentwindow();
		Thread.sleep(10000);
		String importmsg=CommonMethods.getElementText("Importmessagetext_XPATH");
		Assert.assertTrue(true, importmsg);
		
		ScreenShot.takeScreenShot("TC34_Import Message");
		driver.navigate().back(); 
		Thread.sleep(2000);
		
		
		CommonMethods.Click("childSRtab_XPATH");
		ScreenShot.takeScreenShot("TC34_Import Details Page");
		
	}
	
	//verify sr is assigned to user
	public void verifySRassignedToUser(String AssignedTo) throws Exception {
		
		String expassignedto=getAssignedTo();
		//ScreenShot.takeScreenShot("SR is Assigne To User ");
		//String actualassignedto = ExcelUtils.getCellData(sheetname, "AssignedTo", 1);
		Assert.assertEquals(AssignedTo, expassignedto, "Assigned TO  Mismatched");
		
		
	}
	
	
	//***********************History Tab **********************8
	
	// click On History Tab
			public void clickOnHistoryTab() throws Exception {
				
				
				  CommonMethods.mouseHover("historyTabLeadPage_XPATH");
				  CommonMethods.highlightelement("historyTabLeadPage_XPATH");
				  CommonMethods.Click("historyTabLeadPage_XPATH");
				 

				Thread.sleep(3000);
				CommonMethods.scrollDown(300);
				Thread.sleep(3000);
				
			}
        //verify SR History Details
			
			public void verifySRHistoryDetails() throws Exception {
				
				// verify status code column is displayed
				CommonMethods.iselementDisplayed("StatuscodeCodehistoryTab_XPATH");

				// verify Assigned To column is displayed
				CommonMethods.iselementDisplayed("AssignedTohistoryTab_XPATH");

				// verify Last Modified On column is displayed
				CommonMethods.iselementDisplayed("LastModifiedOnhistoryTab_XPATH");

				// verify STO Reason column is displayed
				CommonMethods.iselementDisplayed("STOhistoryTab_XPATH");

				// verify Opening CSA ID column is displayed

				CommonMethods.iselementDisplayed("OpeningCSAIDhistoryTab_XPATH");

				// verify Opening Department column is displayed
				CommonMethods.iselementDisplayed("OpeningDepartmenthistoryTab_XPATH");

				// verify Current Department column is displayed
				CommonMethods.iselementDisplayed("CurrentDepartmenthistoryTab_XPATH");
				
				// verify Closing CSA ID column is displayed
				CommonMethods.iselementDisplayed("ClosingCSAIDhistoryTab_XPATH");

				// verify CLosing Department column is displayed
				CommonMethods.iselementDisplayed("ClosingDepartmenthistoryTab_XPATH");

				// verify Last Modified By column is displayed
				CommonMethods.iselementDisplayed("LastModifiedByhistoryTab_XPATH");
				
				
				Thread.sleep(4000);
	         
			}
			
			
}
