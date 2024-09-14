package com.evy.framework.pages.account;

import com.evy.framework.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSection extends BasePage {

    @FindBy(css = "a[href*='login']")
    private WebElement loginLink;
    @FindBy(css = "a[href*='register']")
    private WebElement registerLink;


    public LoginPage navigateToLoginPage(){
        click(this.loginLink,"login link button");
        waitForPageTitle("Customer login | Fashion Plus Web Store");
        return new LoginPage();
    }

    public RegisterPage navigateToRegisterPage(){
        click(this.registerLink,"register link");
        waitForPageTitle("Register in the store | Fashion Plus Web Store");
        return new RegisterPage();
    }
}
