package com.crm.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.utilities.CommonMethods;

public class BranchHeadPage extends TestBase{
	public static Logger log = LoggerFactory.getLogger(BranchHeadPage.class);
	public BranchHeadPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	
	// Methods start here
	
	// Select value from dropdown from views for Category
		public void selectCategoryValuefromdropdown(String text) {
			CommonMethods.selectByText("viewsCategorydropdown_XPATH", text);

		}
		
		
		// click on views arrow button
		public void clickviewsArrowButton() {
			CommonMethods.Click("viewsArrowButtonBranchHeadPage_XPATH");

		}
		

		// click on views SR Number Link
			public void clickviewsSRNumbercheckBox() {
	            CommonMethods.Click("viewsSRNumbercheckBox_XPATH");
			}
			
			
			// click On Edit SR
			public void clickOnEditSR() {
				CommonMethods.scrollAtBottom();
				CommonMethods.Click("EditSRButtonTeamExecutive_XPATH");

			}
			
			
			// Enter text in remark field
			public void enterremarks() {
				CommonMethods.sendkeys("TeamExecutiveSTORemarks_XPATH", "test");
			}
			
			// click On Edit SR
			public void clickOnSaveandProceed() {
				
				CommonMethods.Click("saveandProceedButton_XPATH");

			}
}
