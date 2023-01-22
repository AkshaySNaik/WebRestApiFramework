package com.webrestapi.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webrestapi.testcase.BaseClass;

public class ExtentListener extends BaseClass implements ITestListener {

	ExtentSparkReporter httpreport;
	ExtentReports report;
	ExtentTest test;
	ThreadLocal<ExtentTest> extenttestthread = new ThreadLocal<ExtentTest>();

	public void cofigReport() {

		String timestap = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportname = "restapi" + timestap + ".html";
		String reportpath = System.getProperty("user.dir") + "//reports//" + reportname;

		httpreport = new ExtentSparkReporter(reportpath);
		httpreport.config().setDocumentTitle("Test Report");
		httpreport.config().setReportName("Opencart Report");
		httpreport.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(httpreport);
		report.setSystemInfo("Username", System.getProperty("user.name"));
		report.setSystemInfo("Operting System", System.getProperty("os.name"));
		report.setSystemInfo("Java Version", System.getProperty("java.version"));

	}

	@Override
	public void onStart(ITestContext context) {

		cofigReport();
		log.info("Test Started For The Given Features");
	}

	@Override
	public void onFinish(ITestContext context) {

		log.info("Test Completed For The Given Features");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {

		log.info("Test Started For:" + result.getName());
		test = report.createTest(result.getName());
		extenttestthread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		log.info("Test Passed:" + result.getName());
		extenttestthread.get().log(Status.PASS,
				MarkupHelper.createLabel("Test Passed: " + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		log.info("Test Failed:" + result.getName());
		extenttestthread.get().log(Status.FAIL,
				MarkupHelper.createLabel("Test Failed: " + result.getName(), ExtentColor.RED));

		extenttestthread.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		log.info("Test Skipped:" + result.getName());
		extenttestthread.get().log(Status.SKIP,
				MarkupHelper.createLabel("Test Skipped: " + result.getName(), ExtentColor.YELLOW));
	}

}
