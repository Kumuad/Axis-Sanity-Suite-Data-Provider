package com.crm.pages;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.base.TestBase;
import com.crm.listeners.CustomListener;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ScreenShot;


public class TaskPage extends TestBase {
	public static Logger log = LoggerFactory.getLogger(TaskPage.class);
	// Constructor
	public TaskPage(WebDriver driver) {
		this.driver = driver;
		

	}
	public static Properties config = TestBase.loadConfig();
	
	//file upload path
	public static String fileupload_task_path=System.getProperty("user.dir") +config.getProperty("FileUpload");
	
	
		
		// click On New Button On Task Page
		public void clickOnNewButton() throws Exception {
			
			//Mouse hover New Button
			CommonMethods.mouseHover("newButtonTaskPage_XPATH");
			CommonMethods.highlightelement("newButtonTaskPage_XPATH");
			Thread.sleep(2000);
			
			//select default layout
			CommonMethods.highlightelement("defaultlayoutTaskPage_XPATH");
			CommonMethods.Click("defaultlayoutTaskPage_XPATH");

		}
		
		
		public void createTaskOnLead(String task_sheet) throws Exception {
			//Switch to window
			CommonMethods.switchwindow();
			Thread.sleep(3000);
			
			
			//Enter Subject
			
			CommonMethods.sendkeys("taskSubjectTextBoxTaskPage_XPATH", task_sheet, "Subject", 1);

			
			String subject=excel.getCellData(task_sheet, "Subject", 1);
			System.out.println("Subject Entered: "+subject);
			
			
			
			//Enter Description
			CommonMethods.sendkeys("taskDescriptionTextBoxTaskPage_XPATH", task_sheet, "Description", 1);
			String description=excel.getCellData(task_sheet, "Subject", 1);
			System.out.println("Description Entered: "+description);
			
			
            
			CommonMethods.clickelementbyjavascript("attachementTabTaskPage_XPATH");
			Thread.sleep(3000);
			
			
			// upload file on Task page
			CommonMethods.fileupload(fileupload_task_path);
			Thread.sleep(2000);
			
			
			//click On Save Button
			CommonMethods.Click("saveButtonTaskPage_XPATH");
			Thread.sleep(5000);
			
			//switch to parent window
			CommonMethods.switchtoparentwindow();
			
			ScreenShot.takeScreenShot("Task Creation");
		}
		
		//generate randowm subject
		
		public String generateRandomTaskSubject() {
			Random random = new Random();
			String id=String.format("%04d",random.nextInt(10000));
			String tasktsubject = String.valueOf(id);
			String text="task";
			String actualsubject = text + tasktsubject;
			return actualsubject;
		}
		
		//create independent task
		public void createtask(String Subject,String Description,String sheetname,int row) throws Exception {
			//switch to window
			CommonMethods.switchToWindow();
			Thread.sleep(2000);
			
			//maximize window
			CommonMethods.maximizeWindow();
			
			// Enter Task Subject
			String subject = generateRandomTaskSubject();
			CommonMethods.sendkeys("taskSubjectTextBoxTaskPage_XPATH", subject);
			excel.setCellData(sheetname, "Subject", row, subject);
			
			//Enter Description
			CommonMethods.sendkeys("taskDescriptionTextBoxTaskPage_XPATH", Description);
			
			// upload file on Task page
			CommonMethods.clickelementbyjavascript("attachementTabTaskPage_XPATH");
			Thread.sleep(3000);
			
			CommonMethods.fileupload(fileupload_task_path);
			Thread.sleep(2000);
			
			//click on Save Button
			CommonMethods.Click("saveButtonTaskPage_XPATH");
			Thread.sleep(4000);
			//switch to parent window
            CommonMethods.switchtoparentwindow();
			
			//ScreenShot.takeScreenShot("Task Creation");
		}
		

}