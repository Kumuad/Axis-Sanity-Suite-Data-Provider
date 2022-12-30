package com.crm.pages;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
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
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;




public class CalendarPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(CalendarPage.class);
	// WebDriver driver;
	public CalendarPage(WebDriver driver) {
		this.driver = driver;

		
	}
	public static WebElement element;
	
	// **************Methods*************

	// click On Calendar Button
	public void clickCalendarButton() throws Exception {
		
		 CommonMethods.highlightelement("calendarButtonCalendarPage_XPATH");
			CommonMethods.Click("calendarButtonCalendarPage_XPATH");
		

	}

	// click On Month Button
	public void clickMonthButton() throws Exception {
		
		 CommonMethods.highlightelement("MonthButtonCalendarPage_XPATH");
			CommonMethods.Click("MonthButtonCalendarPage_XPATH");
	}

	// click On Day Button
	public void clickDayButton() throws Exception {
		
		
		CommonMethods.highlightelement("DayButtonCalendarPage_XPATH");
		CommonMethods.Click("DayButtonCalendarPage_XPATH");

	}

	// click On Week Button
	public void clickWeekButton() throws Exception {
		CommonMethods.highlightelement("WeekButtonCalendarPage_XPATH");
		CommonMethods.Click("WeekButtonCalendarPage_XPATH");
		

	}
	
	
	//to verify created appointed on day
	
	public void verfiyCreatedAppointmentOnDay(String appointmentsubject) throws IOException {

		//WebElement ele = driver.findElement(By.xpath("//div[contains(text(), '" + text + "')]"));
		WebElement ele = driver.findElement(By.xpath("//div[text() = '" + appointmentsubject + "']"));
		// ele.getText();
		// System.out.println(ele);
		boolean flag = false;
		if (ele.isDisplayed() == true) {
			log.info("ScheduledDayAppointment is Present "+ele.getText());
			CustomListener.extentInfo("ScheduledDayAppointment is Present=  ", ele.getText());
			
			//System.out.println("Appointment Present");
			flag = true;
		}
		// boolean value=ele.isDisplayed();
		
		Assert.assertTrue(flag, "Created appointment not present");
		// System.out.println(value);
     
	}
	
	//to verify created appointed on week
	
	public void verfiyCreatedAppointmentOnWeek(String appointmentsubject) throws IOException {

		//WebElement ele = driver.findElement(By.xpath("//div[contains(text(), '" + text + "')]"));
		WebElement ele = driver.findElement(By.xpath("//div[text() = '" + appointmentsubject + "']"));
		// ele.getText();
		// System.out.println(ele);
		boolean flag = false;
		if (ele.isDisplayed() == true) {
			log.info("ScheduledWeekAppointment is Present "+ele.getText());
			CustomListener.extentInfo("ScheduledWeekAppointment is Present=  ", ele.getText());
			//System.out.println("Appointment Present");
			flag = true;
		}
		// boolean value=ele.isDisplayed();

		Assert.assertTrue(flag, "Created appointment not present");
		// System.out.println(value);
		
	}
	
	//generate randowm subject
	
			public String generateRandomSubject() {
				Random random = new Random();
				String id=String.format("%04d",random.nextInt(10000));
				String subject = String.valueOf(id);
				String text="appointment";
				String actualsubject = text + subject;
				return actualsubject;
			}
	//create appointment on Month
	public void createappointmentOnCalendar(String Reason,String ClientSegmentCode,String sheetname,int row) throws Exception {
		//CLick On Todays Date
		//CommonMethods.highlightelement("MonthTodaysDate_XPATH");
		//CommonMethods.mouseHover("MonthTodaysDate_XPATH");
		//CommonMethods.Click("MonthTodaysDate_XPATH");
		CommonMethods.clickelementbyjavascript("MonthTodaysDate_XPATH");
		//select Value from Dropdown as 'Retail Appointments'
		CommonMethods.selectByText("selectCalendarDropdown_XPATH", "Retail Appointments");
		
		Thread.sleep(2000);
		//Enter Subject
		String subject = generateRandomSubject();
		CommonMethods.sendkeys("subjectTextBoxRetailAppointmentPage_XPATH", subject);
		excel.setCellData(sheetname, "Subject", row, subject);
		excel.setCellData("verifySummaryPage", "Subject", row, subject);
		//ENter Reason
		
		CommonMethods.selectByText("ReasonCalendarDropdown_XPATH", Reason);
		//Enter Client Segment code
		//String clientsegemntcode=ExcelUtils.getCellData(sheetname, "ClientSegmentCode", 1);
		CommonMethods.sendkeys("clientsegmentcodetextbox_XPATH", ClientSegmentCode);
		CommonMethods.PickerSelect(ClientSegmentCode);
		
		//click On Save Button
		CommonMethods.Click("saveButton_XPATH");
		Thread.sleep(3000);
		
		//update SummaryPage sheet
		
		
	}
	//getElement text for Appointment on Calendar
			public  void verifycreatedAppointmentOnCalendar(String subject) {
				//ExWait(locator);
				
				element= driver.findElement(By.xpath("//div[contains(text(), '" + subject + "')]"));
				String elementtext=element.getText();
				boolean flag = false;
				if (element.isDisplayed() == true) {
					log.info("Appointment Created On Calendar " + element + " = " + elementtext);
					CustomListener.extentInfo("Appointment Created On Calendar " + element, " = " + elementtext);

					flag = true;
				}

				Assert.assertTrue(flag, "Created appointment not present");

			}
			
			

			public String getAppointmentSubject(String sheetname) throws IOException {
				
			
					//String appointmentsubject=	appointmentSubject.getText();
					
					String appointmentsubject=excel.getCellData(sheetname, "Subject", 1);
					return appointmentsubject;
				
			}	
	
	
}
