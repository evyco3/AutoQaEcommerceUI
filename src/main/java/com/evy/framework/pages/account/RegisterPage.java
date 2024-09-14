package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(css = "#sylius_customer_registration_firstName")
    private WebElement firstname;
    @FindBy(css = "#sylius_customer_registration_lastName")
    private WebElement lastname;
    @FindBy(css = "#sylius_customer_registration_email")
    private WebElement email;
    @FindBy(css = "#sylius_customer_registration_user_plainPassword_first")
    private WebElement password;
    @FindBy(css = "#sylius_customer_registration_user_plainPassword_second")
    private WebElement confirmation;
    @FindBy(css = "button[type='submit']")
    private WebElement registerBtn;
    @FindBy(css = ".positive.message p")
    private WebElement successRegisterMsg;
    @FindBy(css = ".sylius-validation-error")
    private WebElement errorRegisterMsg;


    /**
     * Registers a new user with the given details.
     */
    public <T> T register(String firstname, String lastname, String email, String password, String confirmation, boolean criteria, Class<T> nextPageClass) {
        try {
            LoggerUtils.info(getClass(), "Filling out registration form");
            sendKeys(this.firstname, firstname, "firstname");
            sendKeys(this.lastname, lastname, "lastname");
            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");
            sendKeys(this.confirmation, confirmation, "confirmation");
            click(this.registerBtn, "register button");

            if (criteria) {
                waitForElementVisibility(this.successRegisterMsg);
                LoggerUtils.info(getClass(), "Registration successful");
            }

            return nextPageClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error during registration", e);
            throw new RuntimeException("Registration failed", e);
        }
    }

    /**
     * Retrieves the registration response message based on the operation type.
     */
    public String getRegisterResponseMsg(String operation) {
        if ("valid data".equalsIgnoreCase(operation)) {
            return getText(this.successRegisterMsg, "success register message");
        } else if ("invalid data".equalsIgnoreCase(operation)) {
            return getText(this.errorRegisterMsg, "error register message");
        } else {
            LoggerUtils.warn(getClass(), "Invalid operation type provided");
            throw new RuntimeException("Invalid operation type: " + operation);
        }
    }
}
