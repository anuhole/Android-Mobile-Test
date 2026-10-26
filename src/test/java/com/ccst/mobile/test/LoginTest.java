package com.ccst.mobile.test;

import com.ccst.mobile.base.BaseTest;
import io.appium.java_client.appmanagement.ApplicationState;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import pages.LoginPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


public class LoginTest  extends BaseTest {

    @Test
    public void LoginWithValid() throws InterruptedException {
        String pkg ="com.saucelabs.mydemoapp.android";

        driver.runAppInBackground(Duration.ofSeconds(5));
        driver.terminateApp(pkg);
        driver.activateApp(pkg);

        Thread.sleep(3000);

        ApplicationState state = driver.queryAppState(pkg);
        Assert.assertEquals(state,ApplicationState.RUNNING_IN_FOREGROUND);

        LoginPage page =new LoginPage(driver);
//        driver.rotate(ScreenOrientation.LANDSCAPE);
        page.navigateLogin();

        page.performLogin("bod@example.com","10203040");
        WebElement title = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        Assert.assertTrue(title.isDisplayed(),"Title is not displayed");

        page.performLogout();
        File shot = driver.getScreenshotAs(OutputType.FILE);

    }
    @Test
    public void   LoginWithInvalid(){
        LoginPage page=new LoginPage(driver);
        page.navigateLogin();
        page.performLogin("","10203040");
        driver.getScreenshotAs(OutputType.FILE);

        String errorChecked =page.ErrorMessage();
        Assert.assertEquals(errorChecked,"Username is required");

    }

}
