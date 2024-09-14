package com.evy.test.accountTests;

import com.evy.framework.pages.HomePage;
import com.evy.framework.pages.account.RegisterPage;
import com.evy.framework.utils.AssertionUtils;
import com.evy.test.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("RegistrationFunctionality")
public class RegisterTest extends BaseTest {

    private final Faker faker = new Faker();

    @Test(dataProvider = "data")
    @Parameters({"firstname", "lastname", "email", "password", "confirmation", "operation", "expectedMsg"})
    @Description("This test validates user registration with various sets of credentials and checks if the appropriate registration message is equals to expected message.")
    @Story("User Registration with various data sets")
    public void testUserRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation, String expectedMsg) {

        String actualMsg = performRegistration(firstname, lastname, email, password, confirmation, operation);
        AssertionUtils.assertEquals(actualMsg, expectedMsg);
    }

    private String performRegistration(String firstname, String lastname, String email, String password, String confirmation, String operation) {
        return HomePage.getInstance()
                .navigateToAccountSection()
                .navigateToRegisterPage()
                .register(firstname, lastname, email, password, confirmation, false, RegisterPage.class)
                .getRegisterResponseMsg(operation);
    }

    @DataProvider(name = "data")
    @Description("Data provider for user registration test cases")
    private Object[][] getData() {
        String pw = faker.internet().password(8, 10, true);
        return new Object[][]{
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), pw, pw, "valid data", "Thank you for registering, check your email to verify your account."},
                {faker.name().firstName(), faker.name().lastName(), "evy@example", pw, pw, "invalid data", "This email is invalid."},
                {faker.name().firstName(), faker.name().lastName(), "fashion@example.com", pw, pw, "invalid data", "This email is already used."},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "password", "password123", "invalid data", "The entered passwords don't match"},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), "123", "123", "invalid data", "Password must be at least 4 characters long."},
                {" ", faker.name().lastName(), faker.internet().emailAddress(), "123", "123", "invalid data", "Please enter your first name."},
                {faker.name().firstName()," ", faker.internet().emailAddress(), "123", "123", "invalid data", "Please enter your last name."},
                {faker.name().firstName(),faker.name().lastName(), " ", "123", "123", "invalid data", "Please enter your email."},
                {faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), " ", "123", "invalid data", "The entered passwords don't match"},



        };
    }
}
