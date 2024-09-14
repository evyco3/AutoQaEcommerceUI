package com.evy.test.cartTests;

import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Cart Management")
@Feature("Remove Product from Cart")
public class RemoveProductFromCartTest extends BaseTest {

    @Test
    @Story("Remove All Products from Cart")
    @Description("This test verifies that a user can remove all products from the shopping cart and checks whether the correct success message is displayed.")
    public void testUserRemoveProductsFromCart() {
        String actualMsg = performRemoveProductsFromCart();
        AssertionUtils.assertEquals(actualMsg, "Order has been successfully deleted.");
    }

    private String performRemoveProductsFromCart() {
        return HomePage
                .getInstance()
                .navigateToProductDropdownSection()
                .selectProductCategories("Jeans", "Men")
                .selectProductByName("Slim fit classic")
                .setProductSize("S")
                .setProductQuantity("1")
                .clickAddToCartBtn()
                .removeAllProductsFromCart()
                .getRemoveAllProductsFromCartMsg();
    }
}
