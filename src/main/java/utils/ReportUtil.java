package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportUtil {
	public static ExtentReports initReports(String reportPath2) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String reportPath = "./reports/ExtentReport_" + timestamp + ".html";

		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setReportName("Merchant Login Test Report");
		spark.config().setDocumentTitle("Automation Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Mansi Ranaware");
		extent.setSystemInfo("Environment", "UAT");

		return extent;
	}
}
