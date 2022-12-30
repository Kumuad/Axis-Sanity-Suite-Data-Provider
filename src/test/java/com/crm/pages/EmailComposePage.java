package com.crm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.utilities.CommonMethods;

public class EmailComposePage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(EmailComposePage.class);
	public EmailComposePage(WebDriver driver) {
		this.driver = driver;

	}

	// Click on Email tab
	public void clickOnEmailTab() throws Exception {

		CommonMethods.highlightelement("Emailtab_XPATH");
		CommonMethods.Click("Emailtab_XPATH");
	}

	// click on compose email link
	public void clickOnComposeEmailLink() throws Exception {

		CommonMethods.mouseHover("composeemaillink_XPATH");

		CommonMethods.Click("composeemaillink_XPATH");
	}

//Enter To email address
	public void switchinganotherwindow() throws Exception {
		// swich window
		CommonMethods.switchwindow();
		Thread.sleep(3000);

		// Maximize wndow
		CommonMethods.maximizeWindow();
		Thread.sleep(3000);

	}

//Enter To adress

	public void enterToEmailAddress() throws Exception {

		CommonMethods.mouseHover("To_Xpath");
		Thread.sleep(3000);
		CommonMethods.mouseClick("To_Xpath");
		Thread.sleep(3000);

		CommonMethods.Entertextbyjavascript("To_Xpath", "kumuad.sagar@crmnext.com");
		Thread.sleep(3000);

	}

	// Enter CC adress

	public void enterCCEmailAddress(String servicerequest_sheetname) throws Exception {

		CommonMethods.sendkeys("CC_XPATH", servicerequest_sheetname, "EmailCC", 1);
		Thread.sleep(3000);
	}

	// Enter BCC adress
	public void enterBCCemailAddress(String servicerequest_sheetname) throws Exception {

		CommonMethods.sendkeys("BCC_XPATH", servicerequest_sheetname, "EmailBCC", 1);
		Thread.sleep(3000);
	}

	// Enter subject

	public void enterEmailSubject() throws Exception {

		CommonMethods.sendkeys("subject_XPATH", "sub");

	}

	// Enter email body

	public void enterEmailBody() throws Exception {

		List<WebElement> framesList = driver.findElements(By.xpath("//iframe"));
		int numOfFrames = framesList.size();

		System.out.println("Size: " + numOfFrames);

		driver.switchTo().frame(0);

		System.out.println("Frame switched");
		CommonMethods.scrollByVisibilityofElement("text_XPATH");
		CommonMethods.sendkeys("text_XPATH", "test");
		System.out.println("Text Entered");
		Thread.sleep(3000);

		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		CommonMethods.highlightelement("SendEmailButton_XPATH");
		// CommonMethods.Click("SendEmailButton_XPATH");
		CommonMethods.clickelementbyjavascript("SendEmailButton_XPATH");
		Thread.sleep(3000);
	}

	/*
	 * CommonMethods.mouseHover("EmailBody_XPATH"); Thread.sleep(3000);
	 * CommonMethods.mouseClick("EmailBody_XPATH"); Thread.sleep(3000);
	 * 
	 * CommonMethods.Entertextbyjavascript(
	 * "EmailBody_XPATH","Set Demo For AUtomation"); Thread.sleep(3000);
	 */

	/*
	 * CommonMethods.highlightelement("SendEmailButton_XPATH");
	 * CommonMethods.Click("SendEmailButton_XPATH");
	 */

	//

}
