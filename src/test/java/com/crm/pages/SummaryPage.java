package com.crm.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;

public class SummaryPage extends TestBase{
	public static Logger log = LoggerFactory.getLogger(SummaryPage.class);
	public SummaryPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	// Methods start here
	
	
	//verify Lead ID On Summary PAge
	
	public void verfiyleadIDOnSummaryPage(String LeadID) throws Exception {
		
		//get Lead id from excel
		//String actualleadId=ExcelUtils.getCellData(sheetname, "LeadID", 1);
		//get lead id from summary page
		String expleadId=CommonMethods.getElementText("leadIdSummaryPage_XPATH");
		
		//verify lead iD present on Summary PAge
		
		Assert.assertEquals(LeadID, expleadId, "Lead id not present ");
		
	}
	
	
	
	
	
	//verify Appointment  On Summary PAge
	
		public void verfiyAppointmentOnSummaryPage(String Subject) throws Exception {
			String expappointment = null;

			// get appointment id from excel
			//String actualappointment = ExcelUtils.getCellData(sheetname, "Subject", 1);

			List<WebElement> list = driver
					.findElements(By.xpath("//div[@class='cardListing__row']//span[@class='acd-link-text']"));

			for (WebElement e : list) {

				System.out.println("Appointmenttext:     " + e.getText());
				if (e.getText().contains(Subject)) {
					expappointment = e.getText();
					System.out.println(expappointment);
					log.info("Data sucessfully extracted on " + e + " = " + expappointment);
					CustomListener.extentInfo("Data extracted on " + e, " = " + expappointment);
					break;
				}

			}
			Assert.assertEquals(Subject, expappointment, "Appointment is not present ");

				//Assert.assertEquals(actualappointment, expappointment, "Appointment is not present ");
	           
			//get appointment id from summary page
			//String expappointment=CommonMethods.getElementText("appointmentsubjectSummaryPage_XPATH");
			
			//verify appointment iD present on Summary PAge
			
			
		}
		
		
		
	
	
	
}
