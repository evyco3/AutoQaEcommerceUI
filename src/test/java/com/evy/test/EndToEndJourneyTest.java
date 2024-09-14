package com.evy.test;

import com.evy.framework.configs.Config;
import com.evy.framework.pages.HomePage;
import com.evy.framework.utils.AssertionUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Epic("Order Management")
@Feature("End-to-End Order Placement")
public class EndToEndJourneyTest extends BaseTest {

    private final Faker faker = new Faker();

    @Test
    @Story("Order Placement")
    @Description("This test performs a complete end-to-end journey from login to placing an order and verifies the success message.")
    public void testUserEndToEndJourney() {
        AssertionUtils.assertEquals(performEndToEndJourney(), "You have successfully placed an order.");
    }

    private String performEndToEndJourney() {
        return HomePage.getInstance()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(Config.get().email(), Config.get().password(), true, HomePage.class)
                .navigateToProductDropdownSection()
                .selectProductCategories("T-Shirts", "Men")
                .selectProductByName("Slim fit men")
                .setProductSize("L").setProductQuantity("1").clickAddToCartBtn()
                .proceedToCheckoutPage()
                .setFirstName(faker.name().firstName()).setLastName(faker.name().lastName())
                .setAddress(faker.address().fullAddress()).setCountry("Mexico").setCity(faker.address().city())
                .setPostcode(faker.address().zipCode()).setBankTransferPayment().setFedExShipment().clickPlaceOrder()
                .getSuccessOrderMsg();
    }
}
