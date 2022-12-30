package com.crm.pages;


import java.util.Random;


import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.utilities.CommonMethods;


public class AlertsPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(AlertsPage.class);
	
	public AlertsPage(WebDriver driver) {
		this.driver = driver;
		
		//PageFactory.initElements(driver, this);
	}


	
	// Methods starts here

	// click On New Button On Alerts Page
	public void clickOnNewButton() throws Exception {
		CommonMethods.mouseHover("newButtonAlertsPage_XPATH");
		CommonMethods.highlightelement("newButtonAlertsPage_XPATH");
		CommonMethods.Click("newButtonAlertsPage_XPATH");

	}
	public String generateRandomAlertSubject() {
		Random random = new Random();
		String id=String.format("%04d",random.nextInt(10000));
		String alertsubject = String.valueOf(id);
		String text="alert";
		String actualsubject = text + alertsubject;
		return actualsubject;
	}
	//create alert
	public void createalert(String Title,String Details,String sheetname,int row) throws Exception {
		
		// Enter Alert Subject
		String subject = generateRandomAlertSubject();
		CommonMethods.sendkeys("subjectTextboxAlertsPage_XPATH", subject);
		//ExcelUtils.writeToExcel(sheetname, 1, 1, subject);
		 excel.setCellData(sheetname, "Subject", row, subject);
		
		//Enter Title
		CommonMethods.sendkeys("titleTextboxAlertsPage_XPATH", Title);
		
		//Enter Details
		CommonMethods.sendkeys("detailsTextboxAlertsPage_XPATH",Details);
		
		//click on Save Button
         CommonMethods.Click("saveButtonAlertsPage_XPATH");
         
         
         Thread.sleep(3000);
         
       //Get Alert iD NUmber
         String alertid=CommonMethods.getElementText("alertIDAlertsPage_XPATH");
        
         excel.setCellData(sheetname, "AlertID", row, alertid);
    	
         
         
	}
	
	
}
