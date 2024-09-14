package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#_username")
    private WebElement email;
    @FindBy(css = "#_password")
    private WebElement password;
    @FindBy(css = "button[type='submit']")
    private WebElement loginBtn;
    @FindBy(css = "a[href*='account']")
    private WebElement successLoginMsg;
    @FindBy(css = ".negative.message")
    private WebElement invalidLoginMsg;


    public <T>T login(String email, String password, boolean criteria, Class<T> nextPageClass) {
        try {

            sendKeys(this.email, email, "email");
            sendKeys(this.password, password, "password");
            click(loginBtn, "loginBtn");

            if (criteria) {
                waitForPageTitle("Fashion Plus Web Store");
                LoggerUtils.info(getClass(), "Login successful, navigating to the next page.");

            }

            return nextPageClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to log in with email: " + email, e);
            throw new RuntimeException("Error during login process", e);
        }
    }


    public boolean getLoginResponseMsg(String operation) {
        try {
            boolean isDisplayed;
            if ("valid data".equalsIgnoreCase(operation)) {
                isDisplayed = isDisplayed(this.successLoginMsg, "success login message");
            } else {
                isDisplayed = isDisplayed(this.invalidLoginMsg, "invalid login message");
            }
            LoggerUtils.info(getClass(), "Checked login response message: " + operation + " - Displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Error checking login response message: " + operation, e);
            throw new RuntimeException("Error during login response message check", e);
        }
    }
}
