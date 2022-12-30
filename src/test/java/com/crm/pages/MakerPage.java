package com.crm.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;



public class MakerPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(MakerPage.class);
	// Constructor
	public MakerPage(WebDriver driver) {
		//super();
		this.driver = driver;
		
	}

	
	
	//********************Methods Starts*************************************
	

	//get SR Number from Details Page
	public String getSRNumber() throws Exception {
		
		return CommonMethods.getElementText("SRNumberMakerDetailsPage_XPATH");
		
	}
	
	
	// to get registration page title
	public String verifyregistrationPageTitle() {
		return driver.getTitle();

	}

	// select value from views Assigned dropdown
	public void selectAssignedTo(int i) throws Exception {
		
		CommonMethods.selectByIndex("viewsAssigneTodropdownMakerPage_XPATH", i);
		
		

	}

	// select value from views statuscode dropdown
	public void selectviewsStatuscodedropdown(int i) throws Exception {
		CommonMethods.selectByIndex("viewsStatuscodedropdownMakerPage_XPATH", i);
		

	}

	// click on views arrow button
	public void clickviewsArrowButton() {
		
		CommonMethods.Click("viewsArrowButtonMakerPage_XPATH");
		

	}

	// click on views SR Number Link
	public void clickviewsSRNumberLink() {
		
		CommonMethods.Click("viewsSRNumberMakerPage_XPATH");
		

	}

	// click on views SR Number Link
	public void clickviewsSRNumbercheckBox() {

		
		
		try {
			
			CommonMethods.Click("viewsSRNumbercheckBoxMakerPage_XPATH");
			
		} catch (StaleElementReferenceException e) {
			
			CommonMethods.Click("viewsSRNumbercheckBoxMakerPage_XPATH");
			
		}

	}

	// click on SelfAssignMakerCustomButton

	public void clickSelfAssignMakerCustomButton() {
		
		CommonMethods.Click("SelfAssignMakerCustomButtonMakerPage_XPATH");
		

	}

	// click On Ok Button After self Assign
	public void clickOnOkButtonAfterselfAssign() {
		
		CommonMethods.Click("OkButtonSelAssignMakerPage_XPATH");
		

	}

	// click On Edit SR
	public void clickOnEditSR() throws InterruptedException {
		
		CommonMethods.Click("EditSRButtonMakerPage_XPATH");
		Thread.sleep(1000);

	}

	// click On Edit SR Link
	public void clickOnEditSRLink() {
		
		CommonMethods.Click("EditSRLinkMakerPage_XPATH");
		
	}

	// Select Sent for verification status code
	public void selectsentforverificationstatuscode() {
		
		CommonMethods.Click("sentforverificationstatuscodeMakerPage_XPATH");
		
	}

	// click on checker Search icon
	public void clickOnCheckersearchicon() {
		
		CommonMethods.Click("checkersearchiconMakerPage_XPATH");
		
	}

	// select checker user
	public void selectcheckeruser() throws IOException, Exception {
        CommonMethods.clickelementbyjavascript("selectcheckeruserMakerPage_XPATH");
		
		
	}

	// Enter text in remark field
	public void enterremarks() {
		CommonMethods.sendkeys("remarksfieldMakerPage_XPATH", "test");
		
	}

	// CLick on save and proceed button
	public void clickOnSaveandProceedButton() {
		
		CommonMethods.Click("SaveandproceedbtnMakerPage_XPATH");
		
	}
	
	
	
//get url for Edit Page
	public String getcurrentURL() {
		String url=driver.getCurrentUrl();
		System.out.println(url);
		String[] words = url.split("[?]");
		String currenturl = words[0];
		System.out.println(currenturl);
		return currenturl;
	}
	
	
	//Verify Sr Deited By Assigned User
	public void verifySREditedByAssignedUser(String actualurl,String expurl) {
		
		Assert.assertEquals(actualurl, expurl, "Assigned To User not Able to Edit SR");
		
	}
	
	/**
   //Select value from dropdown from views for dpdis
	public void selectdpdisViewsStatuscodedropdown(String text) {
		
		CommonMethods.selectByText("viewsAssigneTodropdownMakerPage_XPATH", text);
		CommonMethods.selectByText(dpdisViewsDropdown, text);

	}
	
	
	//get Status code from  DPDIS Maker Page
	public String expStatusCode() {
		return statuscode.getText();
	}
	
	//get assigned To From Maker Page
	public String expAssignedTo() {
		return assignedTo.getText();
	}
	
	
	
	//verify next possible status code
	public void verifyNextPossibleStatusCode() {
		
		String[] expstatuscode= {"Sent to Checker","Sent To Authorizer"};
		
		List<WebElement> alloptions= driver.findElements(By.xpath(".//div[@class='nav-stage__item unlocked']/span"));
		for(WebElement ele:alloptions) {
			System.out.println("Actual Possible Status Code:"+ele.getText());
			
			boolean match = false;
			  for (int i=0; i<expstatuscode.length; i++){
			      if (ele.getText().equals(expstatuscode[i])){
			        match = true;
			      }
			    }
			  Assert.assertTrue(match);
			 }  
			
		}
		
	
	
	// Sent SR to 'Sent to Authorizer'
	
	// Select Sent for verification status code
		public void sentSRToAuthorizer() {
			dpdisSentToAuthorizerStatusCode.click();
		}
		
		
	
	**/
}
