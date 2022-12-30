package com.crm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.listeners.CustomListener;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class ScreenShot extends com.crm.base.TestBase{
	
	public static long PAGE_LOAD_TIMEOUT=70;
	public static long IMPLICIT_WAIT=80;
	public static String currentDir;
	public static String Screenshot;
	public static String FailedScreenShotFolder;
	public static String PassScreenShotFolder;
	public static String PassScreenShot;
	public static String FailedScreenShot;
	public static File flScreenshot;
	public static File flPassScreenshotFolder;
	public static File flFailScreenshotFolder;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	
	static String FileDateTime = null;
	static File FilePath = null;
	
	
	public static String outPutFolder;
	public static String ScreenShotFolder;

	public static File flOutput;
	public static File flScreenShotFolder, src, dest;
	public static File flFailedScreenShotFolder;
		
	public static String ScreenShotPath;
	

	

	 public static void screenShotFolder()
		{
			
			String folderDate = new SimpleDateFormat("dd-MM-yyyy HH").format(new Date());
			
			
		//
			// FailedScreenShot = "FailedScreenShot_"+folderDate;
		
			currentDir = System.getProperty("user.dir") + "\\Screenshots";

			outPutFolder = currentDir + "\\Output_" + folderDate;
			PassScreenShot = "PassScreenShot_" + folderDate;
			ScreenShotFolder = outPutFolder + "\\" + PassScreenShot;
			
			
			FailedScreenShot = "FailedScreenShot_" + folderDate;
			FailedScreenShotFolder = outPutFolder + "\\" + FailedScreenShot;
           
			flOutput = new File(outPutFolder);
			if (!flOutput.exists()) {
				if (flOutput.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}

			flScreenShotFolder = new File(ScreenShotFolder);
			if (!flScreenShotFolder.exists()) {
				if (flScreenShotFolder.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
				
				//FilePath = flScreenShotFolder;
			}
			FilePath = flScreenShotFolder;
			
			
			
			
			flFailedScreenShotFolder = new File(FailedScreenShotFolder);
			if (!flFailedScreenShotFolder.exists()) {
				if (flFailedScreenShotFolder.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
				
				//FilePath = flFailedScreenShotFolder;
			}
			//FilePath = flFailedScreenShotFolder;
			
			

			//return FilePath;
				
		}
	 
	
	 
	 
	 
	 public static File failscreenShotFolder()
		{
			
			String folderDate = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());

			FailedScreenShot = "FailedScreenShot_" + folderDate;
			currentDir = System.getProperty("user.dir") + "\\Screenshots";

			outPutFolder = currentDir + "\\Output_" + folderDate;

			FailedScreenShotFolder = outPutFolder + "\\" + FailedScreenShot;
			// flFailScreenshotFolder = new File(FailedScreenShotFolder);

			flOutput = new File(outPutFolder);
			if (!flOutput.exists()) {
				if (flOutput.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}

			flScreenShotFolder = new File(FailedScreenShotFolder);
			if (!flScreenShotFolder.exists()) {
				if (flScreenShotFolder.mkdir()) {
					System.out.println("Directory is created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}
			FilePath = flScreenShotFolder;

			
			return FilePath;
				
		}
	 
	 
 
	
	 
	   //To capture screen shot of complete web page from start to end
		public static String takeScreenShot(String name) throws IOException
		{
			String ScreenShotPath=null;
			try {
			Screenshot ss= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			
			 ScreenShotPath=CustomListener.ScreenShotFolder+"\\"+name+".jpeg";
			
			ImageIO.write(ss.getImage(), "jpeg", new File(ScreenShotPath));
			}
			catch(Exception e) {
				
			}
		return ScreenShotPath;
		}


		 public static String screenshot(String name) throws IOException {
			 
			 String ScreenShotPath=null;
			 
			//Convert web driver object to TakeScreenshot
				TakesScreenshot scrShot =((TakesScreenshot)driver);

				//Call getScreenshotAs method to create image file
				File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

				//Move image file to new destination
				File DestFile=new File(CustomListener.ScreenShotFolder+"\\"+name+".jpeg");
				ScreenShotPath  =CustomListener.ScreenShotFolder+"\\"+name+".jpeg";
				
				//Copy file at destination
				FileUtils.copyFile(SrcFile, DestFile);
			 
			 
			 
			return ScreenShotPath;
			 
			 
			 
		 }
	 
		
		public static String failScreenShot(String methodname) throws IOException
		{
			String ScreenShotPath=null;
			try {
			Screenshot ss= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
			
			
			
			ScreenShotPath=CustomListener.FailedScreenShotFolder+"\\"+methodname+".jpeg";
		
			ImageIO.write(ss.getImage(), "jpeg", new File(ScreenShotPath));
			}
			catch(Exception e) {
				
			}
			return ScreenShotPath;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
