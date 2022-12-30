package com.crm.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.crm.base.TestBase;





public class ExcelUtils extends TestBase{
	
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static Cell cell;
	public static FileInputStream inputStream ;
	public static FileOutputStream outputStream;
	public static ArrayList<String> sheetNames;
	public static int TotalSheetsNum;
	
	public static Logger log = LoggerFactory.getLogger(ExcelUtils.class);

	static HashMap<String, Integer> excelColumns = new HashMap<String, Integer>();
	public static Properties config = TestBase.loadConfig();
	static String ExcelPATH =  System.getProperty("user.dir")+ config.getProperty("ExcelData");
	
	public ExcelUtils() {
		try {
			inputStream = new FileInputStream(ExcelPATH);
			wb = new XSSFWorkbook(inputStream);
			sheet = wb.getSheetAt(0);
			inputStream.close();
		} catch (Exception e) {
			System.out.println("+++++++++Excel Error 1++++++");
			e.printStackTrace();
		}
	}
	public int getRowCount(String sheet) throws IOException
	{
		File f = new File(ExcelPATH);

		//Create an object of FileInputStream class to read excel file
		inputStream = new FileInputStream(f);

		//creating workbook instance that refers to .xlsx file
		wb=new XSSFWorkbook(inputStream); 

		int rowCount = wb.getSheet(sheet).getLastRowNum();

		return rowCount; 
	}
	
	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;
	
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(0);
	
		if (row == null)
			return -1;
	
		return row.getLastCellNum();
	
	}
	
	public static ArrayList<String> getSheetsName() throws IOException
	{
		File f = new File(ExcelPATH);

		//Create an object of FileInputStream class to read excel file
		inputStream = new FileInputStream(f);

		//creating workbook instance that refers to .xlsx file
		wb=new XSSFWorkbook(inputStream); 
		
		TotalSheetsNum=wb.getNumberOfSheets();
		log.info("Total Number of sheets= "+TotalSheetsNum);
		sheetNames = new ArrayList<String>();

		for(int i=0;i<TotalSheetsNum;i++) 
		{
			//System.out.println("Sheet name in for Loop : "+wb.getSheetName(i));
			sheetNames.add(wb.getSheetName(i));
		}
		log.info("Sheet Names List from excel = "+sheetNames);
		return sheetNames;
	}

	//To read test data from excel sheet
	public String readData(String sheet , int rowNum , int colNum) throws EncryptedDocumentException, IOException
	{
//		File f = new File(ExcelPATH);
//
//		//Create an object of FileInputStream class to read excel file
//		 inputStream = new FileInputStream(f);
//
//		//creating workbook instance that refers to .xls file
//		 wb=new XSSFWorkbook(inputStream); 

		String CellData;
		cell  = wb.getSheet(sheet).getRow(rowNum).getCell(colNum);

		//DataFormatter formatter = new DataFormatter();
		//CellData = formatter.formatCellValue(cell);
		//CellData= cell.getStringCellValue();
		//log.info("Excel Data : "+CellData);
	
		try
		{
		  if(cell.getCellType()==CellType.STRING) 
		  { 
			  CellData= cell.getStringCellValue(); 
			  //log.info("Excel Data : "+CellData); 
			  return CellData;
		  } 
		  else if(cell.getCellType()==CellType.NUMERIC)
		  {
			  CellData = NumberToTextConverter.toText(cell.getNumericCellValue());
			  //log.info("Excel Data : "+CellData); 
			  return CellData;
			  
		  } else if(cell.getCellType()==CellType.FORMULA )
		  {
			  CellData = cell.getStringCellValue();
			  //log.info("Excel Data : "+CellData); 
			  return CellData;
		  }else if(cell.getCellType()==CellType.BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		}catch(Exception e)
		{
			return "row "+rowNum+" or column "+colNum +" does not exist in xls";
		}
	
	}	
	
	public ArrayList<String> getcolumnData(String SheetName, String colName) throws IOException
	{	
        ArrayList<String> columnData = new ArrayList<String>();

//		File f = new File(ExcelPATH);
//
//		//Create an object of FileInputStream class to read excel file
//		inputStream = new FileInputStream(f);
//
//		//creating workbook instance that refers to .xls file
//		wb=new XSSFWorkbook(inputStream); 

		sheet = wb.getSheet(SheetName);
		
		//adding all the column header names to the map 'columns'
		sheet.getRow(0).forEach(cell ->{
			excelColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
		});
				
        int lastRowIndex = sheet.getLastRowNum() + 1;
        int colNum = excelColumns.get(colName);
        for (int j = 1; j < lastRowIndex; j++) {
        	String runmode = getCellData("TestScenario", "RunMode", j);
			if(runmode.equalsIgnoreCase("Yes"))
			{
	        	String s = readData(SheetName, j, colNum);
	        	if (!(s.isEmpty() || s.isBlank()))
	        		columnData.add(s);
			}
        }
        
	return columnData;

	}
	
	public int getRunnableCount() throws IOException
	{	
        int count =0;

//		File f = new File(ExcelPATH);
//
//		//Create an object of FileInputStream class to read excel file
//		inputStream = new FileInputStream(f);
//
//		//creating workbook instance that refers to .xls file
//		wb=new XSSFWorkbook(inputStream); 

		sheet = wb.getSheet("TestScenario");
		
		//adding all the column header names to the map 'columns'
		sheet.getRow(0).forEach(cell ->{
			excelColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
		});
				
        int lastRowIndex = sheet.getLastRowNum() + 1;
        for (int j = 1; j < lastRowIndex; j++) {
        	String runmode = getCellData("TestScenario", "RunMode", j);
			if(runmode.equalsIgnoreCase("Yes"))
	        	count++;
			
        }
        
        return count;

	}
	
	// returns the data from a cell using column number
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
	
			int index = wb.getSheetIndex(sheetName);
	
			if (index == -1)
				return "";
	
			sheet = wb.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
	
			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
	
				//String cellText = String.valueOf(cell.getNumericCellValue());
				//String cellText = cell.getStringCellValue();
				String cellText =NumberToTextConverter.toText(cell.getNumericCellValue());
				
				return cellText;
			} else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			// Log.info("Excel error 3 occurs as: "+e);
			System.out.println("Excel Error");
	
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	//To read excel data using column header
	public String getCellData(String SheetName ,String columnName, int rowNum) throws EncryptedDocumentException, IOException 
	{
//		File f = new File(ExcelPATH);
//
//		//Create an object of FileInputStream class to read excel file
//		 inputStream = new FileInputStream(f);
//
//		//creating workbook instance that refers to .xls file
//		 wb=new XSSFWorkbook(inputStream); 

		 sheet = wb.getSheet(SheetName);
		//XSSFSheet sh = wb.getSheetAt(0);    //0 - index of 1st sheet

		//adding all the column header names to the map 'columns'
		sheet.getRow(0).forEach(cell ->{
			excelColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
		});

		return readData(SheetName, rowNum, excelColumns.get(columnName));
	}


	//To write data into excel
	public void writeToExcel(String sheetName , int rowNum , int colNum, String data) throws Exception
	{
		try {
			Thread.sleep(1000);
			//System.out.println("Write values : "+sheet +"|"+rowNum+"|"+colNum+"|"+data);
//			File f = new File(ExcelPATH);
//
//			//Create an object of FileInputStream class to read excel file
//			 inputStream = new FileInputStream(f);
//
//			//creating workbook instance that refers to .xls file
//			 wb=new XSSFWorkbook(inputStream); 

			//creating a Sheet object using the sheet Name
			 sheet = wb.getSheet(sheetName);

			//Create a row object to retrieve row at index m
			 row=sheet.getRow(rowNum);
			if(row ==null)
			{
				row = sheet.createRow(rowNum);
			}

			//create a cell object to enter value in it using cell Index
			XSSFCell cell = row.getCell(colNum);
			
			if (cell == null) 
			{
				cell = row.createCell(colNum);
			}
			cell.setCellValue(data);
			//System.out.println("Row : "+rowNum+" | Cell :"+colNum+" | Data : "+data);
			log.info("Write to excel with : Row="+rowNum+" | Cell ="+colNum+" | Data ="+data);

			// Write the data back in the Excel file
			outputStream = new FileOutputStream(ExcelPATH);
			wb.write(outputStream);
			Thread.sleep(1000);

			inputStream.close();
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			throw(e);		
		}

	}
	
	
public ArrayList<Integer> getRunnableRowsNumber(String sheetName) {
		
		ArrayList<Integer> num = new ArrayList<Integer>();

		//Create an object of FileInputStream class to read excel file
		try {
//			File f = new File(ExcelPATH);
//
//			//Create an object of FileInputStream class to read excel file
//			 inputStream = new FileInputStream(f);
//
//			//creating workbook instance that refers to .xls file
//			 wb=new XSSFWorkbook(inputStream); 
//			 
			sheet = wb.getSheet(sheetName);
			
			int totalRows = sheet.getLastRowNum();
			
			for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
				String runMode = getCellData(sheet.getSheetName(), "RunMode", rowNum);
				if (runMode.equalsIgnoreCase("Yes")) {
					num.add(rowNum);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;

	}

	public int[] getTC_IterationCount() throws EncryptedDocumentException, IOException {
	    int iterationCount = 0;
	    int runnableTCCount = 0;
	    int[] count = new int[2];
	    String testCaseSheet = null;
	    
	    try {
	        sheet = wb.getSheet("TestScenario");
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	       
	    int lastRowIndex = sheet.getLastRowNum() + 1;
	    for (int j = 1; j < lastRowIndex; j++) {
	        String runmode=getCellData("TestScenario", "RunMode", j );
	        if (runmode.equalsIgnoreCase("Yes")) {
	        	runnableTCCount++;
	            testCaseSheet = getCellData("TestScenario", "TC Name", j);
	            sheet = wb.getSheet(testCaseSheet);
	            int numberOfRows = sheet.getLastRowNum() + 1;
	            for(int i = 1; i < numberOfRows; i++)
	            {
	            	 String tcRunmode=getCellData(testCaseSheet, "RunMode", i );
	            	 if (tcRunmode.equalsIgnoreCase("Yes")) 
	            		 iterationCount++;
	            }
	        }
	   }
	    count[0] = runnableTCCount;
	    count[1] = iterationCount;
	    return count;
	    
	}

	//find whether sheets exists
	public boolean isSheetExist(String sheetName) {
//		try {
//			File f = new File(ExcelPATH);
//	
//			//Create an object of FileInputStream class to read excel file
//			 inputStream = new FileInputStream(f);
//	
//			//creating workbook instance that refers to .xls file
//			
//			 wb=new XSSFWorkbook(inputStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		 
		int index = wb.getSheetIndex(sheetName);
		if (index == -1) {
			index = wb.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	public void setCellData(String sheetName,String colName,int rowNum,String data) throws Exception
	{
//		File f = new File(ExcelPATH);
//
//		//Create an object of FileInputStream class to read excel file
//		 inputStream = new FileInputStream(f);
//
//		//creating workbook instance that refers to .xls file
//		 wb=new XSSFWorkbook(inputStream); 

		 sheet = wb.getSheet(sheetName);
		//XSSFSheet sh = wb.getSheetAt(0);    //0 - index of 1st sheet

		//adding all the column header names to the map 'columns'
		sheet.getRow(0).forEach(cell ->{
			excelColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
		});
		
		writeToExcel(sheetName, rowNum, excelColumns.get(colName), data);
		 
	}
	
	
	
}