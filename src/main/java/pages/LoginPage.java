package pages;



import base.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class LoginPage extends BasePage {

    @AndroidFindBy(accessibility = "View menu")
    private WebElement menu;

    @AndroidFindBy (accessibility = "Login Menu Item")
    private WebElement logIn;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement username;

    @AndroidFindBy(id="com.saucelabs.mydemoapp.android:id/passwordET")
    private WebElement password;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private WebElement loginBtn;

    @AndroidFindBy (accessibility = "Logout Menu Item")
    private  WebElement logOut;

    @AndroidFindBy (id = "android:id/button1")
    private  WebElement logoutConf;

//    private final By menu = AppiumBy.accessibilityId("View menu");
//    private final By logIn = AppiumBy.accessibilityId("Login Menu Item");
//    private final By username = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
//    private final By password = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
//    private final By loginBtn = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    private final  By ErrorMessage =AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameErrorTV");

    public LoginPage(AndroidDriver driver) {
        super(driver);
//        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void navigateLogin(){
        click(menu);
        click(logIn);
    }

    public void  performLogin(String usernameInput, String passwordInput){

       EnterText(username,usernameInput);
       EnterText(password,passwordInput);
       click(loginBtn);
    }
    public String  ErrorMessage(){
        return   wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMessage)).getText();
    }

    public void  performLogout(){
        click(menu);
        click(logOut);
        click(logoutConf);
    }
}
