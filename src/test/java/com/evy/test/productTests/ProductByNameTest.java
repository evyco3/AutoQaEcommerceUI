package com.evy.test.productTests;

import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("Product Management")
@Feature("Product Selection By Name")
public class ProductByNameTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"mainCategory", "subCategory", "productName", "expectedTitle"})
    @Story("Select Product by Name from Category")
    @Description("This test allows the user to select a product by category and product name, and verifies that the product title contains the expected title.")
    public void testUserSelectProduct(String mainCategory, String subCategory, String productName, String expectedTitle) {
        String actualTitle = performSelectProductByName(mainCategory, subCategory, productName);
        AssertionUtils.assertContains(actualTitle, expectedTitle);
    }

    public String performSelectProductByName(String mainCategory, String subCategory, String productName) {
        return HomePage.getInstance()
                .navigateToProductDropdownSection()
                .selectProductCategories(mainCategory, subCategory)
                .selectProductByName(productName)
                .getTitle();
    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"Caps", "Simple", "Beautiful cap for woman", "Beautiful cap for woman"},
                {"Caps", "Simple", "Simple cap", "Simple cap"},
                {"Caps", "With pompons", "Basic winter hot cap", "Basic winter hot cap"},
                {"Caps", "With pompons", "Regular cap with big pompon", "Regular cap with big pompon"},
                {"Dresses", "", "Circle-skirt Dress", "Circle-skirt Dress"},
                {"Dresses", "", "Sleeveless Dress", "Sleeveless Dress"},
                {"Dresses", "", "Summer tunic", "Summer tunic"},
                {"Jeans", "Men", "Basic regular", "Basic regular"},
                {"Jeans", "Men", "Slim fit classic", "Slim fit classic"},
                {"Jeans", "Men", "Regular Fit casual", "Regular Fit casual"},
                {"Jeans", "Men", "Slim fit elegant", "Slim fit elegant"},
                {"Jeans", "Women", "Ultra slim", "Ultra slim"},
                {"Jeans", "Women", "Slim fit", "Slim fit"},
                {"Jeans", "Women", "New age regular", "New age regular"},
                {"Jeans", "Women", "Whole holes classic", "Whole holes classic"},
                {"T-Shirts", "Men", "Slim fit men", "Slim fit men"},
                {"T-Shirts", "Men", "Regular fit men", "Regular fit men"},
                {"T-Shirts", "Men", "Slim fit V-neck men", "Slim fit V-neck men"},
                {"T-Shirts", "Women", "Slim fit woman", "Slim fit woman"},
                {"T-Shirts", "Women", "Basic regular woman", "Basic regular woman"},
                {"T-Shirts", "Women", "Regular Fit V-neck woman", "Regular Fit V-neck woman"},
        };
    }
}
