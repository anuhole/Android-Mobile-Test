package pages;

import base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AddToCartPage extends BasePage {
    @AndroidFindBy (uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").instance(4)")
    private WebElement product;

    @AndroidFindBy (accessibility = "Tap to add product to cart")
    private WebElement addCart;

    @AndroidFindBy(id ="com.saucelabs.mydemoapp.android:id/cartIV")
    private WebElement checkToProcesed;

    @AndroidFindBy (accessibility = "Confirms products for checkout")
    private WebElement processedTo;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/fullNameET")
    private WebElement fullName;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/address1ET")
    private WebElement address;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/cityET")
    private WebElement city;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/zipET")
    private WebElement postCode;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/countryET")
    private WebElement country;

    @AndroidFindBy (accessibility = "Saves user info for checkout")
    private  WebElement ToPayment;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/nameET")
    private  WebElement cartName;

    @AndroidFindBy (id = "com.saucelabs.mydemoapp.android:id/cardNumberET")
    private  WebElement cartNo;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/expirationDateET")
    private WebElement expDate;

    @AndroidFindBy(id ="com.saucelabs.mydemoapp.android:id/securityCodeET")
    private  WebElement securityCode;

    @AndroidFindBy (accessibility = "Saves payment info and launches screen to review checkout data")
    private  WebElement reviewOrder;

    @AndroidFindBy (accessibility = "Completes the process of checkout")
    private WebElement placeOrder;

    @AndroidFindBy(accessibility = "Tap to open catalog")
    private  WebElement continueShopping;

    public AddToCartPage(AndroidDriver driver) {
        super(driver);
    }

    public void setCart(){
        click(product);
        click(addCart);
        click(checkToProcesed);
        click(processedTo);
    }

    public void setCheckout(String fullNameInput, String add , String cityInput , String PostCode, String countryInput){
        EnterText(fullName,fullNameInput);
        EnterText(address,add);
        EnterText(city,cityInput);
        EnterText(postCode,PostCode);
        EnterText(country,countryInput);
        click(ToPayment);

    }

    public void SetCreditDetails(String fullNameInput, String cartNoInput, String Expired, String security){
        EnterText(cartName,fullNameInput);
        EnterText(cartNo,cartNoInput);
        EnterText(expDate,Expired);
        EnterText(securityCode,security);
        click(reviewOrder);
        click(placeOrder);
        click(continueShopping);
    }




}
