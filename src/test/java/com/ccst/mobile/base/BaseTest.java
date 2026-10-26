package com.ccst.mobile.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;

public class BaseTest {
    public AndroidDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void startSession() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setApp("C:\\Users\\ccst\\Desktop\\Automation Framework\\Mobile Test\\apps\\mda-2.3.0-27.apk");
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @AfterMethod (alwaysRun = true)
    public void quit(){
        if (driver != null) {
            driver.quit();
        }
    }
}
