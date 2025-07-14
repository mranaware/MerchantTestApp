package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static final String EXCEL_PATH = "TestResults.xlsx";
	private static final String SHEET_NAME = "Results";

	public static void updateResult(String testCaseId, String actualResult, String status, String screenshotPath,
			String reportPath) {
		try {
			File file = new File(EXCEL_PATH);
			XSSFWorkbook workbook;
			XSSFSheet sheet;

			// If Excel doesn't exist, create it with headers
			if (!file.exists()) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet(SHEET_NAME);

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("TestCaseID");
				header.createCell(1).setCellValue("ActualResult");
				header.createCell(2).setCellValue("Status");
				header.createCell(3).setCellValue("TimeStamp");
				header.createCell(4).setCellValue("Screenshot");
				header.createCell(5).setCellValue("HTMLReport");

				try (FileOutputStream fos = new FileOutputStream(file)) {
					workbook.write(fos);
				}
				workbook.close();
			}

			// Now reopen to write result
			FileInputStream fis = new FileInputStream(EXCEL_PATH);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(SHEET_NAME);
			fis.close();

			int lastRow = sheet.getLastRowNum();
			Row row = sheet.createRow(lastRow + 1);

			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

			row.createCell(0).setCellValue(testCaseId);
			row.createCell(1).setCellValue(actualResult);
			row.createCell(2).setCellValue(status);
			row.createCell(3).setCellValue(timeStamp);
			row.createCell(4).setCellValue(screenshotPath);
			row.createCell(5).setCellValue(reportPath);

			try (FileOutputStream fos = new FileOutputStream(EXCEL_PATH)) {
				workbook.write(fos);
			}

			workbook.close();

		} catch (Exception e) {
			System.err.println("Excel update failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
