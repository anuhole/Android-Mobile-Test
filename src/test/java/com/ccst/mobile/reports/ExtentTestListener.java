package com.ccst.mobile.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.ccst.mobile.reports.ExtentManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;
import java.util.Map;

public class ExtentTestListener implements ISuiteListener, ITestListener {
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        ExtentReports extent = ExtentManager.createInstance(suite.getName());
        Map<String, String> parameters = suite.getXmlSuite().getParameters();

        extent.setSystemInfo("Browser", getValue(parameters.get("browser"), System.getProperty("browser", "Android")));
        extent.setSystemInfo("Environment", getValue(parameters.get("env"), System.getProperty("env", "local")));
        extent.setSystemInfo("Tester", getValue(System.getProperty("tester"), "Automation"));
        extent.setSystemInfo("OS", System.getProperty("os.name", "Unknown OS"));
        extent.setSystemInfo("Java", System.getProperty("java.version", "Unknown Java"));
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.flush();
        System.out.println("Extent report : " + ExtentManager.getReportFile().toAbsolutePath());
    }

    @Override
    public void onTestStart(ITestResult result) {
        startEntry(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        entry(result).pass("Passed");
        TEST.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        entry(result).skip(result.getThrowable() != null ? result.getThrowable().toString() : "Skipped");
        TEST.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = entry(result);

        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        } else {
            test.fail("Test failed without a thrown exception.");
        }

        attachScreenshot(result, test);
        TEST.remove();
    }

    private ExtentTest startEntry(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(testName(result), result.getMethod().getMethodName());

        String[] groups = result.getMethod().getGroups();
        if (groups != null && groups.length > 0) {
            test.assignCategory(groups);
        }

        ITestContext context = result.getTestContext();
        String browser = context != null ? context.getCurrentXmlTest().getParameter("browser") : null;
        test.assignDevice(getValue(browser, System.getProperty("browser", "Android")));

        TEST.set(test);
        return test;
    }

    private ExtentTest entry(ITestResult result) {
        ExtentTest test = TEST.get();
        return (test != null) ? test : startEntry(result);
    }

    private static String testName(ITestResult result) {
        String name = result.getMethod().getMethodName();
        return (result.getParameters() == null || result.getParameters().length == 0) ? name : name;
    }

    private static String getValue(String value, String fallback) {
        return (value != null && !value.isBlank()) ? value : fallback;
    }

    private static void attachScreenshot(ITestResult result, ExtentTest test) {
        try {
            Object instance = result.getInstance();
            if (instance == null) {
                test.warning("No screenshot: the Appium session was never started");
                return;
            }

            Field field = instance.getClass().getSuperclass().getDeclaredField("driver");
            field.setAccessible(true);
            Object driver = field.get(instance);

            if (driver instanceof TakesScreenshot takesScreenshot) {
                String base64 = takesScreenshot.getScreenshotAs(OutputType.BASE64);
                test.fail("Screenshot at the moment of failure",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64, "Failure Screenshot").build());
                return;
            }

            if (driver instanceof AndroidDriver androidDriver) {
                String base64 = androidDriver.getScreenshotAs(OutputType.BASE64);
                test.fail("Screenshot at the moment of failure",
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64, "Failure Screenshot").build());
                return;
            }

            test.warning("Could not take a screenshot: no valid driver found in test instance");
        } catch (Exception e) {
            test.warning("Could not take a screenshot: " + e.getMessage());
        }
    }
}


