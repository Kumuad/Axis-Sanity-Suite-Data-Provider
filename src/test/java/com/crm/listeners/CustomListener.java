package com.crm.listeners;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.crm.base.TestBase;

import com.crm.utilities.CommonMethods;
import com.crm.utilities.EmailReporting;
import com.crm.utilities.ExcelUtils;
import com.crm.utilities.ExtentReporter;
import com.crm.utilities.ScreenShot;

import ru.yandex.qatools.ashot.Screenshot;










public class CustomListener extends ScreenShot implements ITestListener,ISuiteListener{
	
	//String SD = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
	
	public static String folderDate = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
	 public static String reportPath = System.getProperty("user.dir")+"\\Reports\\TestReport_"+folderDate+".html";
	//static File statusFile = new File("status.txt");
    static FileWriter myWriter;
    static int testCount = 0;

	public static ExtentTest test;
	ExtentReports extent = ExtentReporter.getReportsObject();
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
	public static List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
	public static List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	public static LocalDateTime startTime;
	public static LocalDateTime endTime;
	public static int runnableCount = 0;
	public static int runnableTCCount = 0;
    public static int iterationCount = 0;
    
	public static Properties config = TestBase.loadConfig();
	 static File statusFile = new File(System.getProperty("user.dir") + config.getProperty("StatusFile"));
	public void onTestStart(ITestResult result) {
		//System.out.println(result.getName()+" test case started");	
		//test = extent.createTest(result.getMethod().getMethodName()+" test case started");
		//testReport.set(test);
		
		
        String methodName = result.getMethod().getMethodName();
      //String module = CommonMethods.getModule(methodName);
       //test = extent.createTest(result.getTestClass().getName() ).assignCategory(module);
	   test = extent.createTest(result.getTestClass().getName() );
		
		//ExtentTest test = extent.createTest("     @TestCase : " + result.getMethod().getMethodName().toUpperCase());
		testReport.set(test);
		log.info("Test Case_  " + methodName.toUpperCase() + " _Successfully Started");
	}

	public void onTestSuccess(ITestResult result) {
		testCount++;
		modifyExecutionStatus();
		
		//System.out.println("The name of the testcase passed is :"+result.getName());	
		//testReport.get().log(Status.PASS, result.getMethod().getMethodName()+" Passed");
		
		
		
         String methodName = result.getMethod().getMethodName();
		
		
		
		//System.out.println("**********  " + methodName + "  _Get Passed  ***********************");
		
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		 passedtests.add(result.getMethod());
	     assignCategory(methodName);
	        
	        log.info("Test Case_  " + methodName.toUpperCase() + " _Successfully Passed");
		
		
	        try {
				String sheetName = CommonMethods.readPropertyFile("SheetName");
				int rowNum = CommonMethods.getTestScenarioRowNum(methodName);
				excel.setCellData(sheetName, "Status", rowNum, "Pass");
			} catch (Exception e) {}
		
	}

	public void onTestFailure(ITestResult result) {
		testCount++;
		modifyExecutionStatus();
		String methodName = result.getMethod().getMethodName();
		log.error(
				" _ " + methodName.toUpperCase() + " _ " + ": Get Failed" + "\n" + result.getThrowable().getMessage());

		//System.out.println("**********  " + methodName + "  _Get Failed  ***********************");
		//System.out.println("Exception Message::  " + result.getThrowable().getMessage());

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		// String excepionMessage= result.getThrowable().getMessage();
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		
		
		 failedtests.add(result.getMethod());
	        
	        assignCategory(methodName);
		
		//commented code
		
		
		
		//testReport.get().fail(result.getThrowable());
		
		//String methodName=result.getMethod().getMethodName();
		
		try{
			
			//commented code
			//testReport.get().addScreenCaptureFromPath(TestUtil.failScreenShot(result.getMethod().getMethodName()), result.getMethod().getMethodName());
			
			ScreenShot.failScreenShot(methodName);
			//testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					//MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.ScreenShotPath).build());
			testReport.get().addScreenCaptureFromPath(ScreenShot.failScreenShot(methodName));
			//failAshot(result.getInstanceName().trim());
			
		}
		//catch(Exception e) {
		//	System.out.println(e.getMessage());
		//}
		
		catch (IOException e) {

		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
		try {
			String sheetName = CommonMethods.readPropertyFile("SheetName");
			int rowNum = CommonMethods.getTestScenarioRowNum(methodName);
			excel.setCellData(sheetName, "Status", rowNum, "Fail");
		} catch (Exception e) {}

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		//testReport.get().skip(m);
         extent.removeTest(test);
		
        skippedtests.add(result.getMethod());

		log.info("Test Case_  " + methodName.toUpperCase() + " _Is Skipped As In Runmode it's 'NO' ");

		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void onStart(ISuite arg0) {
		startTime =  LocalDateTime.now();
		 try {
				int [] count = excel.getTC_IterationCount();
				iterationCount = count[1];
				runnableTCCount = count[0];
		    } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try {
		      if(statusFile.exists()) {
			        System.out.println("Status file already exists. Deleting it..");
			        statusFile.delete();
		      }
			        if (statusFile.createNewFile()) {
				        System.out.println("File created: " + statusFile.getName());
				      }
			      
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
//		try {
//				myWriter = new FileWriter(statusFile.getName());
//				myWriter.write("Total number of tests selected with runnable mode as YES: " + excel.getRunnableCount());
//				myWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		writeStatus(runnableTCCount+"\n");
		writeStatus("0/"+iterationCount+ " Completed");
		
		currentDir = System.getProperty("user.dir")+"\\ScreenShots";
		
		if(config.getProperty("RunExecutedFromJar").equalsIgnoreCase("YES"))
		{
			String runID = config.getProperty("RunID");
			outPutFolder = currentDir +"\\" + runID;
			PassScreenShot = runID + "_PassScreenShot";
			ScreenShotFolder = outPutFolder+"\\"+PassScreenShot;
			FailedScreenShot = runID + "_FailedScreenShot";
			FailedScreenShotFolder = outPutFolder+"\\"+FailedScreenShot;
		}
		else
		{
			outPutFolder = currentDir +"\\Output_"+folderDate;
			PassScreenShot ="PassScreenShot_"+folderDate;
			ScreenShotFolder = outPutFolder+"\\"+PassScreenShot;
			FailedScreenShot = "FailedScreenShot_"+folderDate;
			FailedScreenShotFolder = outPutFolder+"\\"+FailedScreenShot;
		}
	   
	   /** PassScreenShot = "PassScreenShot_" + folderDate;
		FailedScreenShot = "FailedScreenShot_" + folderDate;

		currentDir = System.getProperty("user.dir")+"\\Screenshots";
		outPutFolder = currentDir + "\\Output_" + folderDate;
		ScreenShotFolder = outPutFolder + "\\" + PassScreenShot;
		FailedScreenShotFolder = outPutFolder + "\\" + FailedScreenShot;
**/
		
		flOutput = new File(outPutFolder);
		flScreenShotFolder = new File(ScreenShotFolder);
		flFailedScreenShotFolder = new File(FailedScreenShotFolder);
		flOutput.mkdir();
		flScreenShotFolder.mkdir();
		flFailedScreenShotFolder.mkdir();
		
		
	
		
		

	}
	
	public static void extentInfo(String message,String name)
	{
		Markup m = MarkupHelper.createLabel(message +" "+ name, ExtentColor.BLUE);
		testReport.get().log(Status.INFO, m);
	}
	
	public static void extentError(String message)
	{
		Markup m = MarkupHelper.createLabel(message, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}
	
	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}
		
	}
	public void onFinish(ISuite arg0) {
		endTime =  LocalDateTime.now();
		String [] systemInfo = CommonMethods.getSystemInfo();
		try {
			extent.setSystemInfo("Browser Name" , systemInfo[0].toUpperCase());
			extent.setSystemInfo("Browser Version" , systemInfo[1]);
			extent.flush();
		    EmailReporting.sendReportViaEmail(passedtests.size(), failedtests.size(), skippedtests.size(), startTime, endTime);  
			writeStatus("\nExecution status mail sent.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void assignCategory(String methodName) {
		try {
			String module = CommonMethods.getModule(methodName);
           System.out.println("Module"+module);
			test.assignCategory(module);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//To write data into status file
		public static void writeStatus(String status) {
		
			try {			
				 BufferedWriter out = new BufferedWriter(new FileWriter(statusFile.getAbsolutePath(), true));
			 
			            // Writing on output stream
			            out.write(status);
			            // Closing the connection
			            out.close();
			     
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		//To overwrite the test execution Status in the file
		public static void modifyExecutionStatus() {
			 try {
				 	String filePath = statusFile.getAbsolutePath();
				 	String result = fileToString(filePath);
				 	String oldStatus = ((testCount - 1)+"/"+iterationCount+ " Completed");
				 	String newStatus = ("\n"+(testCount)+"/"+iterationCount+ " Completed");
				 	if(result.contains(oldStatus))
				 		result = result.replaceAll(oldStatus,newStatus);
				 	else
				 		result = result.concat(newStatus);
				     //Rewriting the contents of the file
				     PrintWriter writer = new PrintWriter(new File(filePath));
				     writer.append(result);
				     writer.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//To return the file contents as a string
		public static String fileToString(String filePath) throws Exception{
		      String input = null;
		      Scanner sc = new Scanner(new File(filePath));
		      StringBuffer sb = new StringBuffer();
		      while (sc.hasNextLine()) {
		         input = sc.nextLine();
		         sb.append(input);
		      }
		      return sb.toString();
		   }
}
