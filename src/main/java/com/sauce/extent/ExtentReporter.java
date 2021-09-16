package com.sauce.extent;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ExtentReporter implements ITestListener {

	public static String platform;
	public static String fileName;
	public static String onlyFileName;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String time = getDate();
	public static String report;
	public static  WebDriver driver;

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String name = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":",
				"_");
		return name;
	}

	@Override
	public void onStart(ITestContext context) {
		try {
			platform = context.getSuite().getName();
			System.out.println("PlatForm : " + platform);
			report = context.getName();
			extent = new ExtentReports(
					System.getProperty("user.dir") + "/reports" + "/" + report + "/" + report + "_" + time + ".html",
					true);
			fileName = System.getProperty("user.dir") + "/reports" + "/" + report + "/" + report + "_" + time + ".html";
			onlyFileName = report + "-" + time + ".html";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getMethod().getMethodName());
		screencapture(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, result.getMethod().getMethodName());
		screencapture(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getMethod().getMethodName());
		screencapture(result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.endTest(test);
		extent.flush();
	}

	public void extentLogger(String stepName, String details) {
		test.log(LogStatus.INFO, stepName, details);
	}
	
	public void extentLoggerPass(String stepName, String details) {
		test.log(LogStatus.PASS, stepName, details);
	}

	public void extentLoggerFail(String stepName, String details) {
		test.log(LogStatus.FAIL, stepName, details);
	}
	
	public void screencapture(String testname) {
		test.log(LogStatus.INFO, test.addScreenCapture(getScreenhot(testname +"_" + time + ".jpg")));
	}

	public static String getScreenhot(String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}
	
}
