package com.crm.autodesk.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * it is developed using Apache poi libraries,which is used to handle Microsoft excel sheet
 * @author ASHUTOSH NAYAK
 *
 */

public class ExcelUtility {
	/**
	 * it is used to read data from excel with below argument 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return data
	 * @throws Throwable
	 */
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis= new FileInputStream("./data/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
	}
	/**
	 * it is used to get the last used row number on specified sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis = new FileInputStream("./data/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	}
	public void setDateExcel(String sheetName,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream  fis = new FileInputStream("./data/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(cellNum);
		cel.setCellValue(data);
		FileOutputStream fos= new FileOutputStream("./data/testscriptdata.xlsx");
		wb.write(fos);
		wb.close();
	}
	

}
