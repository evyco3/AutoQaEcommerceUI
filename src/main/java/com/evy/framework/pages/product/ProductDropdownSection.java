package com.evy.framework.pages.product;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDropdownSection extends BasePage {

    public ProductListingPage selectProductCategories(String mainCategory, String subCategory) {
        try {

            if ("Dresses".equalsIgnoreCase(mainCategory)) {
                WebElement mainCategoryElement = driver.findElement(By.xpath("//a[normalize-space()='Dresses']"));
                click(mainCategoryElement, mainCategory);
                waitForTextInElementToBe(driver.findElement(By.cssSelector("h1.monster")), mainCategory);

                LoggerUtils.info(getClass(), String.format("Successfully selected the category: %s", mainCategory));
            } else {
                String mainCategoryStr = String.format("//div[@class='ui large stackable menu']//span[normalize-space()='%s']", mainCategory);
                WebElement mainCategoryElement = driver.findElement(By.xpath(mainCategoryStr));

                String subCategoryStr = String.format("//div[@class='ui large stackable menu']//span[normalize-space()='%s']/parent::div//a[normalize-space()='%s']", mainCategory, subCategory);
                WebElement subCategoryElement = driver.findElement(By.xpath(subCategoryStr));

                click(mainCategoryElement, mainCategory);
                click(subCategoryElement, subCategory);
                waitForTextInElementToBe(driver.findElement(By.cssSelector("h1.monster")), subCategory);

                LoggerUtils.info(getClass(), String.format("Successfully selected the main category: %s and subcategory: %s", mainCategory, subCategory));
            }

            return new ProductListingPage();


        } catch (Exception e) {
            LoggerUtils.error(getClass(), String.format("An error occurred while selecting category: %s, subcategory: %s", mainCategory, subCategory), e);
            throw new RuntimeException(String.format("Unexpected error for category: %s and subcategory: %s", mainCategory, subCategory), e);
        }
    }
}
