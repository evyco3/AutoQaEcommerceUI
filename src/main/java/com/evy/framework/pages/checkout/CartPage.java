package com.evy.framework.pages.checkout;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    @FindBy(css = "#sylius-cart-clear")
    private WebElement removeAllProductsFromCart;

    @FindBy(css = ".positive.message p")
    private WebElement operationSuccessMsg;

    @FindBy(css = "button[value='checkout']")
    private WebElement proceedToCheckoutBtn;




    public CartPage removeAllProductsFromCart(){
        click(this.removeAllProductsFromCart,"remove all products from caty button");
        return this;
    }

    public String getRemoveAllProductsFromCartMsg(){
        return getText(this.operationSuccessMsg,"remove products from cart message");
    }

    public String getAddToCartResponseMsg(){
        return getText(this.operationSuccessMsg,"add to cart success message");
    }

    public CheckoutPage proceedToCheckoutPage(){
        click(this.proceedToCheckoutBtn,"proceed to checkout button");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("/checkout"));
        return new CheckoutPage();

    }
}
