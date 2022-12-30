package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;




public class CheckerPage extends TestBase{
	
	public static Logger log = LoggerFactory.getLogger(CheckerPage.class);
	//Constructor 
	public CheckerPage(WebDriver driver) {
		this.driver = driver;
		
	}

	
	
	//Page method starts--------
	
	//Select value from Views Assigned to Dropdown
	public void selectAssignedTo(int i) throws Exception {
		
		CommonMethods.selectByIndex("viewsAssigneTodropdownCheckerPage_XPATH", i);
	}
	
	
	//select value from views statuscode dropdown
	public void selectviewsStatuscodedropdown(int i) throws Exception {
		
      CommonMethods.selectByIndex("viewsStatuscodedropdownCheckerPage_XPATH", i);
	}
		
	//click on Views Arrow Button
	public void clickOnViewsArrowButton() {
		
	
		CommonMethods.Click("viewsArrowButtonCheckerPage_XPATH");
		
	}
	
	//click on views SR Number Link
		public void clickviewsSRNumberLink() {
		
			CommonMethods.Click("viewsSRNumberCheckerPage_XPATH");
			

		}
	//click on Closure milestone
	public void clickOnCLosureMilestone() {
	
		CommonMethods.Click("CLosuremilestoneCheckerPage_XPATH");
		
		
	}

	//click on save and proceed button
	public void clickOnSaveandproceedbutton() {
		
		CommonMethods.Click("SaveandproceedbtnCheckerPage_XPATH");
	
		
	}
	
	//click On Edit SR
	public void clickOnEditSR() {
		
		CommonMethods.scrollDown(500);
		CommonMethods.Click("EditSRButtonCheckerPage_XPATH");
		//EditSRButton.click();

	}

	//click On Edit SR Link
	public void clickOnEditSRLink() {
		
		CommonMethods.Click("EditSRLinkCheckerPage_XPATH");
		
	}
	
	//click On sub status
		public void clickOnsubstatussearch() {
			
			CommonMethods.Click("substatussearchCheckerPage_XPATH");
			
		}
	
    //select approved and closed substatus
		public void selectApprovedandCLosedSubStatus() {
			
			
			CommonMethods.Click("approvedandclosedsubstatus");
		}
		//Enter text in remarks fields
		public void enterremarks() {
			
			CommonMethods.sendkeys("RemarksfieldCheckerPage_XPATH", "test");
			
		}
		
		
		//verify Conditional Access denied message
		
		public void verifyConditionalAccessDeniedMsg() throws IOException {
			
			CommonMethods.isElementDisplayed("accessdeniedmsg_XPATH");
			
			
		}
}
