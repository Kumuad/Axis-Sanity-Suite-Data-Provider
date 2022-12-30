package com.crm.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
//import java.time.Duration;
//import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang.UnhandledException;
import org.apache.poi.EncryptedDocumentException;
//import org.apache.log4j.Logger;
//import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
//import org.testng.Assert;
import org.slf4j.Logger;
import com.aventstack.extentreports.Status;
import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.opencsv.CSVWriter;

import java.time.Duration;
//import org.slf4j.Logger;

public class CommonMethods extends TestBase {
	public static Logger log = LoggerFactory.getLogger(CommonMethods.class);
	// public static Logger log = Logger.getLogger(CommonMethods.class);

//	public  JavascriptExecutor js = (JavascriptExecutor) driver;
	public static JavascriptExecutor js;
	public static String parentwindow;
	public static WebElement element;
	public static Actions a;
	public static WebDriverWait wait;
	public static Properties prop = new Properties();

	// to scroll down the page by pixel values as Y-coordiante
	public static void scrollDown(int y) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + y + ")");
	}

	public static void scrollhorizontal(String locator, int x) throws Exception, IOException {
		js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		js.executeScript("arguments[0].scrollIntoView()", element);

	}

	// To scroll down the page by visibility of the element
	public static void scrollByVisibilityofElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	// To scroll down the page at the bottom of page.
	public static void scrollAtBottom() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// Return the complete height of body (page)
	}

	//// Selection of dropdown by text
	public static void selectByText(WebElement element, String text) {
		Select sel = new Select(element);

		sel.selectByVisibleText(text);

	}

	//// Selection of dropdown by value
	public static void selectByValue(WebElement element, String str) {
		Select sel = new Select(element);
		sel.selectByValue("str");
	}

	// Selection of dropdown by index
	public static void selectByIndex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	// Mouse hover on element
	public static void mouseHover(WebElement toElement) {
		Actions a = new Actions(driver);
		a.moveToElement(toElement).perform();
	}

	// click element by javascript
	public static void clickelementbyjavascript(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

	}

	public static void clickelementbyjavascript(String locator) throws Exception, IOException {
		js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		js.executeScript("arguments[0].click();", element);
		log.info("Sucessfully clicked on " + locator);
		CustomListener.extentInfo("Sucessfully clicked on ", locator);
	}

	// highlight the element
	public static void highlightelement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px dotted red'", element);
	}

	// Maximize Window
	public static void maximizeWindow() {

		driver.manage().window().maximize();
	}
	// Switch to window

	public static void switchwindow() {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));

			parentwindow = driver.getWindowHandle();
			// System.out.println(parentwindow);

			for (String handle : driver.getWindowHandles()) {
				// System.out.println(handle);
				if (!parentwindow.equalsIgnoreCase(handle)) {
					// System.out.println(handle);
					driver.switchTo().window(handle);
				}
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static void switchToWindow() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			parentwindow = driver.getWindowHandle();
			// String parentWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> it = windows.iterator();
			while (it.hasNext()) {
				String childWindow = it.next();
				if (!parentwindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
					// Verify title of the child window
					// System.out.println("Window Title = "+driver.getTitle() +"and URL =
					// "+driver.getCurrentUrl() );
					log.info("Switched to Child window name : " + driver.getTitle() + " || URL :"
							+ driver.getCurrentUrl());
				}
			}
		} catch (Exception e) {
			log.error("Not able switch to window " + e.getMessage());
		}
	}

	// switch to parent window

	public static void switchtoparentwindow() {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			driver.switchTo().window(parentwindow);

		}

		catch (Exception e) {
			e.getMessage();
		}
	}

	// Generate Random Mobile Number 10 digit

	public static String generateRandomMobileNumber() {
		Random random = new Random();
		// int randomInt = random.nextInt(1000000000);
		String id = String.format("%09d", random.nextInt(10000));
		String mobilenumber = String.valueOf(id);

		String s1 = "9";
		String actualmobnum = s1.concat(mobilenumber);
		return actualmobnum;
	}

	// generate Random Customer Id 9 digit
	public static String generateRandomCustomerID() {
		Random random = new Random();
		// int randomInt = random.nextInt(1000000000);
		String id = String.format("%09d", random.nextInt(10000));
		String customerid = String.valueOf(id);
		return customerid;

	}

	// generate Random Number

	public static int generateRandomNumber() {
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		return randomNum;

	}

	// generate Random Email

	public static String generateRandomEmail() {
		// int num=generateRandomNumber();
		Random random = new Random();
		// int randomInt = random.nextInt(1000000000);
		String id = String.format("%04d", random.nextInt(10000));
		String emailid = String.valueOf(id);
		String email = "test" + emailid + "@gmail.com";

		return email;
	}

	public static String generateRandomAppointmentSubject() {
		// int num=generateRandomNumber();
		Random random = new Random();
		// int randomInt = random.nextInt(1000000000);
		String id = String.format("%04d", random.nextInt(10000));
		String appointmentid = String.valueOf(id);
		String appointmentsubject = "appointment" + appointmentid;

		return appointmentsubject;
	}

	public static String generateRandomID() {

		Random random = new Random();

		String id = String.format("%04d", random.nextInt(10000));
		String idno = String.valueOf(id);
		// String lead= "lead"+leadid;

		return idno;
	}

	// generate random PAN Number

	public static String generatePANNumber() {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		// int randomNum = random.nextInt(1000);
		// String pannumber= "ASDUY"+id+"Z";
		String pannumber = "CJJPS" + id + "Z";
		return pannumber;
	}
	// file upload

	public static void fileupload(String filepath) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			Robot robo = new Robot();
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));

			// element.click();
			Thread.sleep(2000);
			StringSelection path = new StringSelection(filepath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);

			robo.setAutoDelay(3000);

			robo.keyPress(KeyEvent.VK_CONTROL);
			robo.keyPress(KeyEvent.VK_V);
			robo.keyRelease(KeyEvent.VK_CONTROL);
			robo.keyRelease(KeyEvent.VK_V);
			Thread.sleep(2000);
			robo.keyPress(KeyEvent.VK_ENTER);
			robo.keyRelease(KeyEvent.VK_ENTER);
			log.info("File Uploaded successfully ");
			CustomListener.extentInfo("File Uploaded Successfully ", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void PickerSelect(String value) throws IOException, InterruptedException {
		Thread.sleep(4000);
		String data = value;

		try {

			// element = driver.findElement(By.xpath("//td[contains(text(),'" + data +
			// "')]"));
			element = driver.findElement(By.xpath("//td[text() = '" + data + "']"));

			// element = driver.findElement(By.xpath("//td[contains(translate(text(),'" +
			// data + "']"));

			System.out.println(data);

			element.click();

			// CommonMethods.clickelementbyjavascript("")
			// .info("Selected " + data);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/// to select first selected option from dropdown
	public static String getFirstSelectedOption(String locator) throws Exception {

		ExWait(locator);
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		Select sel = new Select(element);
		String text = sel.getFirstSelectedOption().getText();

		log.info("First Selected option " + locator + " = " + text);
		CustomListener.extentInfo("First Selected option " + locator, " = " + text);
		return text;

	}

	public static boolean isTestRunnable(String testName, String sheetName) throws Exception {

		// String sheetName="TestScenario";
		int rows = excel.getRowCount("TestScenario");
		// System.out.println("No of rows : "+rows + " and test name = "+testName);

		for (int rNum = 1; rNum <= rows; rNum++) {

			String testCase = excel.getCellData("TestScenario", "TC Name", rNum);

			if (testCase.equalsIgnoreCase(testName)) {

				String runmode = excel.getCellData("TestScenario", "RunMode", rNum);

				if (runmode.equalsIgnoreCase("Yes")) {
					initialization(sheetName);
					return true;
				} else
					return false;
			}
		}
		return false;
	}

	// To handle mouse hover actions
	public static void mouseClick(WebElement element) throws Exception {
		try {
			a = new Actions(driver);
			ExplicitWait(element);
			highlightelement(element);
			// highlightelement(locator);
			// WebElement element =
			// driver.findElement(By.xpath(ObjectRepository.getProperty(locator)));

			a.moveToElement(element).click().perform();
			// log.debug("Mouse Click on " + locator);
		} catch (Exception e) {
			// log.error("Not able to Mouseclick due to " + e.getMessage());
		}

	}

	public static void Click(WebElement element) {
		try {
			ExplicitWait(element);
			element.click();
			// log.info("Clicked Sucessfully on "+element.getText());
			// TestListeners.extentInfo("Clicked Sucessfully on "+element.getText());
		} catch (Exception e) {
			log.error("Unable to click on " + element.getText() + " due to " + e.getMessage());
		}
	}

	public static void ExplicitWait(WebElement element) throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// wait=new WebDriverWait();
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void sendkeys(WebElement element, String SheetName, String ColName, int rowNum, String msg)
			throws IOException {
		try {
			Click(element);
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(excel.getCellData(SheetName, ColName, rowNum));
			String text = excel.getCellData(SheetName, ColName, rowNum);
			System.out.println(msg + " : " + text);
			System.out.println();
			log.info("Data Sucessfully entered on " + element.getAttribute("placeholder") + " = "
					+ excel.getCellData(SheetName, ColName, rowNum));
			// CustomListener.extentInfo("Data Sucessfully entered on
			// "+element.getAttribute("placeholder")+" =
			// "+ExcelUtils.getCellData(SheetName,ColName,rowNum));
			CustomListener.testReport.get().log(Status.INFO, "Data entered on " + element.getAttribute("placeholder")
					+ " = " + excel.getCellData(SheetName, ColName, rowNum));
		} catch (Exception e) {
			log.error("Data Not Sucessfully entered on " + element.getAttribute("placeholder") + " due to :"
					+ e.getMessage());
		}
	}

	public static void selectByText(WebElement element, String sheetName, String colName, int rowNum, String msg)
			throws InterruptedException {
		Thread.sleep(1000);
		Select sel = new Select(element);
		// Select sel = new Select(element);
		// sel.selectByVisibleText(text);
		try {
			// scrollByVisibilityofElement(element);
			ExplicitWait(element);
			sel.selectByVisibleText(excel.getCellData(sheetName, colName, rowNum));
			String text = excel.getCellData(sheetName, colName, rowNum);
			System.out.println(msg + " : " + text);
			System.out.println();
			// sel.getFirstSelectedOption();
			log.info(sel.getFirstSelectedOption().getText() + " : Data Sucessfully Selected from dropdown ");
			// CustomListener.extentInfo("Data Sucessfully entered on
			// "+element.getAttribute("placeholder")+" =
			// "+ExcelUtils.getCellData(sheetName,colName,rowNum));
			CustomListener.testReport.get().log(Status.INFO,
					msg + " : " + excel.getCellData(sheetName, colName, rowNum));
		} catch (Exception e) {
			log.error("Not able to select from dropdown " + element);
		}
	}

	//
	public static String getElementText(WebElement element, String msg) throws InterruptedException {
		// ExplicitWait(element);
		String txtMsg = element.getText();
		// log.info("Text of Web element :" +txtMsg);
		System.out.println(msg + " : " + txtMsg);
		System.out.println();
		CustomListener.testReport.get().log(Status.INFO, msg + " : " + txtMsg);
		return txtMsg;
	}

	// generate random client ID
	public static String generateRandomclientID() {
		Random random = new Random();
		// int randomInt = random.nextInt(1000000000);
		String id = String.format("%010d", random.nextInt(10000));
		String clientid = String.valueOf(id);
		return clientid;

	}

	// *******************************Common utilities***************************
	// Explicit Wait
	public static void ExWait(String locator) throws Exception {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		// wait= new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonMethods.readPropertyFile(locator))));
	}

	// keys enter
	public static void KeysEnter(String locator) {
		try {
			Click(locator);
			if (locator.endsWith("_XPATH")) {

				driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).sendKeys(Keys.ENTER);
			} else if (locator.endsWith("_ID")) {

				driver.findElement(By.id(CommonMethods.readPropertyFile(locator))).sendKeys(Keys.ENTER);
			}
			log.info("Keys Entered on " + locator);

		} catch (Exception e) {
			log.error("Keys Entered on  " + locator);
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}

	}

	// CLick Element
	public static void Click(String locator) {
		try {
			ExWait(locator);
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).click();
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(CommonMethods.readPropertyFile(locator))).click();
			}
			log.info("Sucessfully clicked on " + locator);

			CustomListener.extentInfo("Sucessfully clicked on ", locator);

		} catch (Exception e) {
			log.error("Not Sucessfully clicked on " + locator + " due to :" + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}
	}

	// To enter values (sendkeys)
	public static void sendkeys(String locator, String text) {
		try {
			Click(locator);
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).clear();
				driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).sendKeys(text);
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(CommonMethods.readPropertyFile(locator))).clear();
				driver.findElement(By.id(CommonMethods.readPropertyFile(locator))).sendKeys(text);
			}
			log.info("Data sucessfully entered on " + locator + " = " + text);
			CustomListener.extentInfo("Data sucessfully entered on " + locator, " = " + text);

		} catch (Exception e) {
			log.error("Data Not Sucessfully entered on " + locator + " due to :" + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}
	}

	// To enter values (sendkeys)
	public static void sendkeys(String locator, String SheetName, String ColName, int rowNum) {
		try {
			Click(locator);
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).clear();
				driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)))
						.sendKeys(excel.getCellData(SheetName, ColName, rowNum));
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(CommonMethods.readPropertyFile(locator))).clear();
				driver.findElement(By.id(CommonMethods.readPropertyFile(locator)))
						.sendKeys(excel.getCellData(SheetName, ColName, rowNum));
			}
			log.info("Data sucessfully entered on " + locator + " = " + excel.getCellData(SheetName, ColName, rowNum));
			CustomListener.extentInfo("Data sucessfully entered on " + locator,
					" = " + excel.getCellData(SheetName, ColName, rowNum));

		} catch (Exception e) {
			log.error("Data Not Sucessfully entered on " + locator + " due to :" + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}
	}

	// to get element text
	public static String getElementText(String locator) throws Exception {
		ExWait(locator);
		String txtMsg = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).getText();
		log.info("Data sucessfully extracted on " + locator + " = " + txtMsg);
		CustomListener.extentInfo("Data extracted on " + locator, " = " + txtMsg);

		return txtMsg;
	}

	// getElemnt text for Appointment on Calendar
	public static String verifycreatedAppointmentOnCalendar(String text) {
		// ExWait(locator);

		element = driver.findElement(By.xpath("//div[contains(text(), '" + text + "')]"));
		String elementtext = element.getText();
		return elementtext;
	}

	// to get Element value
	public static String getElementValue(String locator) throws Exception {
		ExWait(locator);
		String elementValue = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)))
				.getAttribute("value");
		CustomListener.extentInfo("Data extracted on " + locator, " = " + elementValue);
		// log.info("Value of WebElement :" +elementValue);
		return elementValue;

	}

	// To highlight selected webelement
	public static void highlightelement(String locator) throws Exception {
		ExWait(locator);
		js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
	}

	// To scroll down the page by visibility of the element
	public static void scrollByVisibilityofElement(String locator) throws Exception {
		js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	// To select values from dropdown by visible text
	public static void selectByText(String locator, String sheetName, String colName, int rowNum) throws Exception {
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		Select sel = new Select(element);
		try {
			ExWait(locator);
			String text = excel.getCellData(sheetName, colName, rowNum);
			sel.selectByVisibleText(text);
			log.info("Data = " + text + " Sucessfully Selected from dropdown " + locator);
			CustomListener.extentInfo("Data sucessfully Selected on " + locator,
					" = " + excel.getCellData(sheetName, colName, rowNum));
		} catch (Exception e) {
			log.error("Not able to select from dropdown " + locator + "due to " + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}
	}

	// select by text
	public static void selectByText(String locator, String text) {
		try {
			WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
			Select sel = new Select(element);
			sel.selectByVisibleText(text);
			System.out.println("Value selected from dropdown : " + text);
			log.info("Data = " + text + " Sucessfully Selected from dropdown " + locator);
			CustomListener.extentInfo("Data = " + text + " Sucessfully Selected from dropdown ", locator);
		} catch (Exception e) {
			log.error("Not able to select from dropdown " + locator + "due to " + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}

	}

	// To select values from dropdown by its value
	public static void selectByValue(String locator, String sheetName, String colName, int rowNum)
			throws InterruptedException, EncryptedDocumentException, IOException {
		try {
			WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
			String value = excel.getCellData(sheetName, colName, rowNum);

			Select sel = new Select(element);
			ExWait(locator);
			sel.selectByValue(value);
			log.info("Data = " + value + " Sucessfully Selected from dropdown " + locator);
			CustomListener.extentInfo("Element Selected  ", locator);
		} catch (Exception e) {
			log.error("Not able to select from dropdown " + locator + "due to " + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}
	}

	// To select values from dropdown by its index value
	public static void selectByIndex(String locator, int index) throws Exception {
		try {
			WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
			Select sel = new Select(element);
			ExWait(locator);
			sel.selectByIndex(index);
			log.info("Data = " + index + " Sucessfully Selected from dropdown " + locator);
			CustomListener.extentInfo("Element Selected  ", locator);
		} catch (Exception e) {
			log.error("Not able to select from dropdown " + locator + "due to " + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}
	}

	// To handle mouse hover actions
	public static void mouseHover(String locator) throws Exception {
		try {
			a = new Actions(driver);
			ExWait(locator);
			highlightelement(locator);
			WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));

			a.moveToElement(element).perform();
			log.debug("Mouse hover on " + locator);
		} catch (Exception e) {
			log.error("Unable to mouse hover due to " + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}

	}

	// To handle mouse hover actions
	public static void mouseClick(String locator) throws Exception {
		try {
			a = new Actions(driver);
			ExWait(locator);
			highlightelement(locator);
			WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));

			a.moveToElement(element).click().perform();
			log.debug("Mouse Click on " + locator);
		} catch (Exception e) {
			log.error("Not able to Mouseclick due to " + e.getMessage());
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + locator);
			Assert.fail(locator);
		}

	}

	// to verify Element is present
	public static void isElementDisplayed(String locator) throws UnhandledException, IOException {

		boolean element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).isDisplayed();
		Assert.assertTrue(element, "element not dispalyed");
		log.info("Element is Displayed On UI" + locator);
		CustomListener.extentInfo("Element is displayed  ", locator);

	}

	// to verify Element is present
	public static void iselementDisplayed(String locator) throws Exception {

		CommonMethods.scrollByVisibilityofElement(locator);
		boolean status = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).isDisplayed();
		Assert.assertTrue(status, "Element is displayed");
		log.info("Element is displayed  " + locator);
		CustomListener.extentInfo("Element is displayed ", locator);

	}

	// to verify Element is present
	public static boolean isElementPresent(String locator) throws Exception, IOException {
		try {
			driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	// verify exp status code on Details pAge
	public static String expStatusCode(String locator, String expstatuscode) throws Exception, IOException {
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		String actualstatuscode = element.getText();

		System.out.println("Status Code On Details Page :" + element.getText());

		log.info("Exp status code:  " + locator + " = " + actualstatuscode);
		CustomListener.extentInfo("Exp status code:  " + locator, " = " + actualstatuscode);

		Assert.assertEquals(expstatuscode, actualstatuscode, "Status code mismatch");
		return actualstatuscode;
	}

	// verify exp status code on Details pAge
	public static String expAssignedTo(String locator, String expsassignedto) throws Exception, IOException {
		WebElement element = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));
		String actualassignedTo = element.getText();

		System.out.println("Assigned TO On Details Page :" + element.getText());
		log.info("Assigned TO On Details Page :" + locator + " = " + actualassignedTo);
		CustomListener.extentInfo("Assigned TO On Details Page :" + locator, " = " + actualassignedTo);

		Assert.assertEquals(expsassignedto, actualassignedTo, "Status code mismatch");
		return actualassignedTo;
	}

	// verify actual exp title

	public static void verifyPageTitle(String actualtitle) {
		String exptitle = driver.getTitle();
		// System.out.println("page title"+exptitle);
		Assert.assertEquals(exptitle, actualtitle, "Page Title Doesnot Match");
		log.info("Page Title :" + exptitle);
		CustomListener.extentInfo("Page Title  ", exptitle);
	}

	// get list of Element
	public static List<WebElement> getElements(String locator) throws Exception, IOException {
		return driver.findElements(By.xpath(CommonMethods.readPropertyFile(locator)));
		// return driver.findElements(locator);
	}

	// get count of list of elements
	public static int getElementsListCount(String locator) throws IOException, Exception {

		int size = getElements(locator).size();
		log.info("Element List Count" + size);
		CustomListener.extentInfo("Element List Count " + locator, " = " + size);
		return size;
	}

	// get list text of elements from list
	public static List<String> getElementsTextList(String locator) throws Exception, Exception {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	// print text of all elements from list
	public static void printAllElementsText(String locator) throws Exception, Exception {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println("===============" + text + "===============");
		}

	}

	// get attribute value
	public static String getAttributeValue(String locator, String attributeName) throws Exception, IOException {
		String elementValue = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)))
				.getAttribute(attributeName);
		return elementValue;
	}

	// to clear text
	public static void clear(String locator) throws Exception, IOException {
		driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).clear();

	}

	// write dat to csv

	public static void writeDataAtOnce(String filePath, String SRNum) {

		// first create file object for file placed at location
		// specified by filepath
		File file = new File(filePath);

		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			@SuppressWarnings("deprecation")
			CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

			// create a List which contains String array
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Remarks", "Parent SR Number", "Target Branch", "SR Branch Name", "Sub Unit",
					"Cash Limit", "Pickup Frequency", "Pickup Address", "Product Type" });
			data.add(new String[] { "test", SRNum, "Originating", "Branch", "CCPH-CMS Logistics", "100", "On Call",
					"Andheri", "Cheque" });

			writer.writeAll(data);

			// closing writer connection
			writer.close();

			System.out.println("Successful.....");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CustomListener.testReport.get().log(Status.FAIL, "Element Not Displayed : " + SRNum);
			Assert.fail(SRNum);
		}
	}

	// to get element text
	public static String getInnerHtml(String locator) throws Exception {
		ExWait(locator);
		String txtMsg = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).getAttribute("innerHTML");
		// System.out.println("String: "+txtMsg);
		log.info("Data sucessfully extracted on " + locator + " = " + txtMsg);
		CustomListener.extentInfo("Data extracted on " + locator, " = " + txtMsg);

		return txtMsg;
	}

	// enter text by java script
	public static void Entertextbyjavascript(String locator, String Text) throws Exception, IOException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement email = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));

		js.executeScript("arguments[0].value= '" + Text + "' ", email);

	}

	// Tab out
	public static void enterTAB(String locator) throws Exception, IOException {

		WebElement entertab = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator)));

		entertab.sendKeys(Keys.TAB);
	}

	public static String getModule(String methodName) throws Exception {
		int rows = excel.getRowCount("TestScenario");

		String module = null;

		for (int rNum = 1; rNum <= rows; rNum++) {

			String testCase = excel.getCellData("TestScenario", "TC Name", rNum);

			if (testCase.equalsIgnoreCase(methodName)) {

				module = excel.getCellData("TestScenario", "Module", rNum);
			}
		}
		return module;
	}

	public static String getTestTypes() {
		ArrayList<String> columnData = new ArrayList<String>();
		String testTypes = null;
		try {
			columnData = excel.getcolumnData("TestScenario", "Test Type");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// To remove duplicates from the array list
		List<String> newList = columnData.stream().distinct().collect(Collectors.toList());

		testTypes = String.join(", ", newList);

		System.out.println("ArrayList with duplicates removed: " + newList);

		return testTypes;
	}

	// To get system info
	public static String[] getSystemInfo() {
		String[] sysInfo = new String[2];
		Capabilities browserCap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = browserCap.getBrowserName();
		String browserVersion = browserCap.getBrowserVersion();
		sysInfo[0] = browserName;
		sysInfo[1] = browserVersion;
		return sysInfo;
	}

	public static String getAttribute(String locator) throws Exception {
		ExWait(locator);
		String txtMsg = driver.findElement(By.xpath(CommonMethods.readPropertyFile(locator))).getAttribute("value");
		// System.out.println("String: "+txtMsg);
		log.info("Data sucessfully extracted on " + locator + " = " + txtMsg);
		CustomListener.extentInfo("Data extracted on " + locator, " = " + txtMsg);

		return txtMsg;
	}

	// read property file
	public static String readPropertyFile(String propertyName) throws UnhandledException, IOException {
		prop = TestBase.loadConfig();
		String propertyValue = prop.getProperty(propertyName);

		return propertyValue;
	}

	// getScenarioROwNum
	public static int getTestScenarioRowNum(String testScenario) throws Exception {
		String sheetName = CommonMethods.readPropertyFile("SheetName");
		int rows = excel.getRowCount(sheetName);
		int rNum = 1;
		for (; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, "TC Name", rNum);
			if (testCase.equalsIgnoreCase(testScenario)) {
				log.info("Row num for TestScenario = " + testScenario + " is = " + rNum);
				return rNum;
			}
		}
		return rNum;
	}

	// verify product present
	public static boolean productpresent(String product) {

		try {
			driver.findElement(By.xpath("//div[text()= '" + product + "']")).isDisplayed();
			return true;
		}

		catch (NoSuchElementException e) {
			return false;
		}
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		// System.out.println("Sheet name in current data provider is::"+sheetName);
		ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows.size()][1];

		Hashtable<String, String> table = null;

		// for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
		int count = 0;

		for (int i : rows) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, i + 1));
				data[count][0] = table;
			}
			count++;
		}
		return data;
	}
}
