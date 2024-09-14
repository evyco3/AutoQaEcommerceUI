package com.evy.test.accountTests;

import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.account.LoginPage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.test.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "data")
    @Parameters({"email", "password", "operation"})
    @Story("User Login Scenarios")
    @Description("This test validates user login with various sets of credentials and checks if the appropriate login message is displayed.")
    public void testUserLoginScenarios(String email, String password, String operation) {
        boolean actualMsg = performLogin(email, password, operation);
        AssertionUtils.assertTrue(actualMsg,
                String.format("Expected that login response message for operation '%s' would be displayed correctly, and the result is as expected.", operation));
    }

    private boolean performLogin(String email, String password, String operation) {
        return HomePage.getInstance()
                .navigateToAccountSection()
                .navigateToLoginPage()
                .login(email, password, false, LoginPage.class)
                .getLoginResponseMsg(operation);
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {"fashion@example.com", "sylius", "valid data"},
                {"fashion@example.com", "wrongPassword", "invalid data"},
                {"wrongEmail@example.com", "sylius", "invalid data"},
                {"wrongEmail@example.com", "wrongPassword", "invalid data"},
                {"fashion@example.com", "", "invalid data"},
                {"", "sylius", "invalid data"},
                {"", "", "invalid data"}
        };
    }
}
