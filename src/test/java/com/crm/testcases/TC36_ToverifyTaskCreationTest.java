package com.crm.testcases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LeadsPage;
import com.crm.pages.LoginPage;
import com.crm.pages.RetailAppointmentPage;
import com.crm.pages.TaskPage;
import com.crm.utilities.CommonMethods;
import com.crm.utilities.ScreenShot;


public class TC36_ToverifyTaskCreationTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	RetailAppointmentPage retailAppointmentPage;
	TaskPage taskpage;

	CommonMethods commonmethods;

	public static int iterationCount = 0;

	@Test(dataProviderClass = CommonMethods.class, dataProvider = "dp")

	public void verifyTaskCreation(Hashtable<String, String> data) throws Exception {
		// Excel sheet path
				String sheetname = "verifyTaskCreation";
				ArrayList<Integer> rows = excel.getRunnableRowsNumber(sheetname);
		if (!(CommonMethods.isTestRunnable("verifyTaskCreation",sheetname))) {
			  
			  throw new SkipException("Skipping the test " + "To verify Task Creation".toUpperCase() + "as the Run mode is NO"); 
			  
			  }
		iterationCount++;
		loginpage = new LoginPage(driver);
		
		// Login to admin page
		loginpage.Login(data.get("UserName"), data.get("Password"));
		Thread.sleep(2000);
		homepage = new HomePage(driver);
		
		// Select BRO Role
		homepage.selectrole(data.get("Role"));
		Thread.sleep(2000);
		
		//click on Task Link
		homepage.clickOnTaskLink();
		
		taskpage = new TaskPage(driver);
		
		//click on New Button ,Select default layout
		taskpage.clickOnNewButton();
		Thread.sleep(2000);
		
		//create task
		taskpage.createtask(data.get("Subject"),data.get("Description"),sheetname,rows.get(iterationCount-1));
		
		ScreenShot.takeScreenShot("TC36_Task Creation");
		Thread.sleep(2000);
		//logout
		loginpage.Logout();
		
	}

}
