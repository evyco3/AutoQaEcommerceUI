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
@Feature("Product Category Navigation")
public class ProductCategoriesTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"mainCategory", "subCategory", "expectedUrl"})
    @Story("User selects product categories from dropdown")
    @Description("Verifies that the user can navigate through different product categories and subcategories, and land on the correct page corresponding to the selected category.")
    public void testUserSelectProductCategories(String mainCategory, String subCategory, String expectedUrl) {

        String actualUrl = performSelectProductCategories(mainCategory, subCategory);
        AssertionUtils.assertContains(actualUrl, expectedUrl);
    }

    private String performSelectProductCategories(String mainCategory, String subCategory) {
        return HomePage.getInstance()
                .navigateToProductDropdownSection()
                .selectProductCategories(mainCategory, subCategory)
                .getCurrentUrl();
    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"Caps", "Simple", "/caps/simple"},
                {"Caps", "With pompons", "/caps/with-pompons"},
                {"Dresses", "", "/dresses"},
                {"Jeans", "Men", "/jeans/men"},
                {"Jeans", "Women", "/jeans/women"},
                {"T-Shirts", "Men", "/t-shirts/men"},
                {"T-Shirts", "Women", "/t-shirts/women"},
        };
    }
}
