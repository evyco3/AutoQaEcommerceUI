package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.pages.checkout.CartPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "#sylius-product-adding-to-cart select")
    private WebElement productSize;
    @FindBy(css = "#sylius_add_to_cart_cartItem_quantity")
    private WebElement productQuantity;
    @FindBy(css = "button[type='submit']")
    private WebElement addToCartBtn;


    public ProductPage setProductSize(String productSize){
        selectByVisibleText(this.productSize,productSize,"product size dropdown");
        return this;
    }

    public ProductPage setProductQuantity(String productQuantity){
        sendKeys(this.productQuantity,productQuantity,"product quantity");
        return this;
    }

    public CartPage clickAddToCartBtn(){
        click(this.addToCartBtn,"add to cart button");
        waitForPageTitle("Your shopping cart | Fashion Plus Web Store");
        return new CartPage();
    }






}
