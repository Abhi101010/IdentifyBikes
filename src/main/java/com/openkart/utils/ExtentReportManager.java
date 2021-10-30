package com.openkart.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	static ExtentHtmlReporter reporter=null;
	static ExtentReports report=null;
	
	public static ExtentReports generateReport() {
		
		String path = System.getProperty("user.dir")+"/ExtentReport/Report.html";
		reporter= new ExtentHtmlReporter(path);
		report = new ExtentReports();
		report.attachReporter(reporter);
		
		report.setSystemInfo("Host Name", "User System");
		report.setSystemInfo("Environment", "Windows 10");
		report.setSystemInfo("User Name", "User-1");

		reporter.config().setDocumentTitle("Free CRM");
		reporter.config().setReportName("Free CRM Testing");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setTheme(Theme.STANDARD);
		
		return report;
	}
}
