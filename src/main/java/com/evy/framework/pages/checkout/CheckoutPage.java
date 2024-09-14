package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {

    @FindBy(css = "#checkout_billingAddress_firstName")
    private WebElement firstname;
    @FindBy(css = "#checkout_billingAddress_lastName")
    private WebElement lastname;
    @FindBy(css = "#checkout_billingAddress_street")
    private WebElement address;
    @FindBy(css = "#checkout_billingAddress_countryCode")
    private WebElement country;
    @FindBy(css = "#checkout_billingAddress_city")
    private WebElement city;
    @FindBy(css = "#checkout_billingAddress_postcode")
    private WebElement postcode;
    @FindBy(css = "label[for='checkout_shipments_0_method_2']")
    private WebElement fedexShipment;
    @FindBy(css = "label[for='checkout_payments_0_method_1'")
    private WebElement bankTransferPayment;
    @FindBy(css = "button[type='submit']")
    private WebElement placeOrderBtn;

    public CheckoutPage setFirstName(String firstname) {
        sendKeys(this.firstname, firstname, "firstname");
        return this;
    }

    public CheckoutPage setLastName(String lastname) {
        sendKeys(this.lastname, lastname, "lastname");
        return this;
    }

    public CheckoutPage setAddress(String address) {
        sendKeys(this.address, address, "address");
        return this;
    }

    public CheckoutPage setCountry(String country) {
        selectByVisibleText(this.country, country, "country");
        return this;
    }

    public CheckoutPage setCity(String city) {
        sendKeysUsingJavaScript(this.city, city, "city");
        return this;
    }

    public CheckoutPage setPostcode(String postcode) {
       sendKeysUsingJavaScript(this.postcode, postcode, "postcode");
        return this;
    }

    public CheckoutPage setFedExShipment() {
         if(this.fedexShipment.isEnabled()) {
             click(this.fedexShipment, "FedEx Shipment");
         }
        return this;
    }

    public CheckoutPage setBankTransferPayment() {
        click(this.bankTransferPayment, "Bank Transfer Payment");
        return this;
    }

    public PlaceOrderPage clickPlaceOrder() {
        click(this.placeOrderBtn, "Place Order Button");
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("/order/thank-you"));
        return new PlaceOrderPage();
    }
}
