package com.ccst.mobile.test;

import com.ccst.mobile.base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddToCartPage;
import pages.LoginPage;

public class AddCartTest extends BaseTest {
    @Test
    public void  testAddToCart (){
        LoginPage page =new LoginPage(driver);
        AddToCartPage cart =new AddToCartPage(driver);
        page.navigateLogin();

        page.performLogin("bod@example.com","10203040");
        cart.setCart();
        cart.setCheckout("Anushka Hole","Pune","Pune","412214","India");
        cart.SetCreditDetails("Anushka Hole" ,"123 345 456","1212","1234");


        WebElement title = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        Assert.assertTrue(title.isDisplayed(),"Title is not displayed");
        System.out.println("Shopping Done!!!");

        page.performLogout();
    }
}
