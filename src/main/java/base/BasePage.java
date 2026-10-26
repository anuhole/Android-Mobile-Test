package base;

import io.appium.java_client.android.AndroidDriver;


import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
    public AndroidDriver driver;
    public WebDriverWait wait;

    public BasePage(AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       // PageFactory.initElements(driver, AppiumFieldDecorator.DEFAULT_WAITING_TIMEOUT);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);

    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

//    public void click(By locator){
//        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
//    }

    public void EnterText(WebElement element, String message){

       // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(message);
    }


//    public void EnterText(By locator, String message){
//
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        wait.until(ExpectedConditions.visibilityOf(element));
//        element.clear();
//        element.sendKeys(message);
//    }

}