package com.crm.utilities;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.crm.base.TestBase;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtentReporter extends TestBase{
	public static Properties config = TestBase.loadConfig();
	 public static ExtentReports extent;
	 static Logger log = LoggerFactory.getLogger(ExtentReporter.class);
	 public static String folderDate = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
	 public static String currentDir = System.getProperty("user.dir")+"\\Reports";
	 public static String outPutFolder = currentDir ;
	 public static String reportPath = outPutFolder + "\\TestReport_"+folderDate+".html";
	public static ExtentReports getReportsObject() 
	{
		
		extent = new ExtentReports();
		
		
		if(config.getProperty("RunExecutedFromJar").equalsIgnoreCase("YES"))
		{
			String runID = config.getProperty("RunID");
			outPutFolder = currentDir +"\\" + runID;
			//reportPath = runID +".html";
			reportPath =outPutFolder+"\\"+ runID +".html";
		}
		else
		{

			
			reportPath =System.getProperty("user.dir")+"\\Reports\\TestReport_"+folderDate+".html";
		}
		//ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName).filter().statusFilter().as(new Status [] {Status.FAIL,Status.PASS}).apply().viewConfigurer().viewOrder().as(new ViewName [] {ViewName.DASHBOARD,ViewName.TEST}).apply();    
		
		
		//ExtentReports extent = null;
		//String folderDate = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
		//try {
		//String reportPath = System.getProperty("user.dir")+"\\Reports\\TestReport_"+folderDate+".html";
		
	
		
		
		//ExtentSparkReporter reporter =new ExtentSparkReporter(reportPath);
			
			ExtentSparkReporter reporter =new ExtentSparkReporter(reportPath).viewConfigurer()
				    .viewOrder()
				    .as(new ViewName[] { 
					   ViewName.DASHBOARD, 
					   ViewName.TEST, 
					   ViewName.CATEGORY,
					   ViewName.AUTHOR, 
					   ViewName.DEVICE, 
					   ViewName.EXCEPTION, 
					   ViewName.LOG
					})
				  .apply();
		//reporter.config().setReportName("Web Automation Result");
		//reporter.config().setDocumentTitle("Test Results");
		
		
			 
			
		
		 reporter.config().setTimelineEnabled(false);
		 reporter.config().setCss(".sysenv-container{right:50%} .category-container{left:50%}");
		 reporter.config().setJs("document.querySelector('.category-container .card .card-header p').innerHTML='Cases/Scenarios';");
		 try {
				reporter.loadXMLConfig(new File(System.getProperty("user.dir") + config.getProperty("ExtentConfigXml")));
				//System.out.println("Config File loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Unable to load config.xml file due to "+e.getMessage());
			}
		
		//extent = new ExtentReports();
		extent.attachReporter(reporter);
		//extent.setSystemInfo("Tester Name","Kumuad Sagar");
		//extent.setSystemInfo(" "," ");
		extent.setSystemInfo("Project Name","Axis Sanity Suite");
		extent.setSystemInfo("Test Coverage", CommonMethods.getTestTypes());
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		
		//}catch(Exception e)
		//{
			//System.out.println(e.getMessage());
			//log.error("Unable to load config.xml file due to "+e.getMessage());
		//}
		return extent;
	}
}
