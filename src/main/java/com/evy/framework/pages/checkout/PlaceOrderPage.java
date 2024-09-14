package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderPage extends BasePage {

    @FindBy(css = "#sylius-thank-you>div")
    private WebElement successOrderMsg;


    public String getSuccessOrderMsg(){
        return getText(this.successOrderMsg,"success order message");
    }
}
