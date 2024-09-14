package com.evy.test.cartTests;

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

@Epic("Cart Management")
@Feature("Add Product to Cart")
public class AddProductToCartTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"productSize", "productQuantity", "expectedMsg"})
    @Story("Add Product to Cart by Setting Attributes")
    @Description("This test verifies that a user can add a product to the cart by selecting a size and quantity, and it checks whether the correct success message is displayed.")
    public void testUserAddProductToCart(String productSize, String productQuantity, String expectedMsg) {
        String actualMsg = performSetAttributesAndAddProductToCart(productSize, productQuantity);
        AssertionUtils.assertEquals(actualMsg, expectedMsg);
    }

    private String performSetAttributesAndAddProductToCart(String productSize, String productQuantity) {
        return HomePage.getInstance()
                .navigateToProductDropdownSection()
                .selectProductCategories("Jeans", "Men")
                .selectProductByName("Slim fit classic")
                .setProductSize(productSize)
                .setProductQuantity(productQuantity)
                .clickAddToCartBtn()
                .getAddToCartResponseMsg();
    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"S", "1", "Item has been added to cart"},
                {"M", "1", "Item has been added to cart"},
                {"L", "1", "Item has been added to cart"},
                {"XL", "1", "Item has been added to cart"},
                {"XXL", "1", "Item has been added to cart"},
        };
    }
}
