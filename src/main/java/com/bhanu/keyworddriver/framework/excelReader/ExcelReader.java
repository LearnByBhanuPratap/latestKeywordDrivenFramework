package com.bhanu.keyworddriver.framework.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	XSSFWorkbook workbook;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	String excelPath;

	XSSFSheet sheet;

	public ExcelReader(String excelPath) {
		this.excelPath = excelPath;
		try {
			fis = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			return 0;
		} else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}
	// returns the data from a cell
		public String getCellData(String sheetName,String colName,int rowNum){
			
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			
			XSSFRow row = sheet.getRow(0);
			
			int col_Num = 0;
			
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())){
					col_Num=i;
					break;
				}
			}
			
			row = sheet.getRow(rowNum-1);

			XSSFCell cell = row.getCell(col_Num);
			
			if(cell==null){
				System.out.println("cell is not present");
				return "";
			}
			
			switch (cell.getCellTypeEnum()) {
			case STRING:
				return cell.getStringCellValue();
			default:
				System.out.println("no matching enum date type found");
				break;
			}
			
			return colName;
			
		}
		
		public static void main(String[] args) {
			ExcelReader excelReader = new ExcelReader(System.getProperty("user.dir")+"/src/test/java/testData/TestSuite1.xlsx");
			System.out.println(excelReader.getRowCount("TC01"));
			System.out.println(excelReader.getCellData("TC01", "Keyword", 3));
	}

}
