package com.openkart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ManageExcelData {

	public static String path = "src/test/java/Tests/TestData.xlsx";
	
	public static String[][] readData(String sheetName) throws IOException {
		
		FileInputStream file = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		String[][] data = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i=0; i<sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i+1);
			for (int j=0; j<row.getLastCellNum(); j++) {
				data[i][j] = String.valueOf(row.getCell(j));
			}
		}
		return data;
	}
}
