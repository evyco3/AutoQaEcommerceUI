package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductListingPage extends BasePage {

    public ProductPage selectProductByName(String productName) {
        try {

            String productNameStr = String.format("//a[@class='header sylius-product-name'][normalize-space()='%s']", productName);
            WebElement productNameElement = driver.findElement(By.xpath(productNameStr));

            click(productNameElement, productName);
            waitForPageTitle(productName);

            LoggerUtils.info(getClass(), String.format("Successfully selected the product: %s", productName));

            return new ProductPage();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("An error occurred while selecting product: %s", productName), e);
            throw new RuntimeException(String.format("Unexpected error for product: %s", productName), e);
        }
    }
}
