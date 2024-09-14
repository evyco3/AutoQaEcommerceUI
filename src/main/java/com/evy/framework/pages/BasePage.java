package com.evy.framework.pages;

import com.evy.framework.drivers.Driver;
import com.evy.framework.utils.ActionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class BasePage {
    protected final WebDriver driver;

    public BasePage() {
        this.driver = Driver.getInstance().getDriver();
        PageFactory.initElements(this.driver, this);
    }

    protected WebElement waitForElementVisibility(WebElement element) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForTextInElementToBe(WebElement element, String text) {
        ActionUtils.performAction(getClass(), () -> {
                    boolean result;
                    result = new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.textToBePresentInElement(element, text));
                    return result;
                },
                // Success message when the text is present in the element
                "Successfully found the text: \"" + text + "\" in the element.",
                // Error message if the text is not found or an exception occurs
                "Failed to find the text: \"" + text + "\" in the element.");
    }

    protected void waitForPageTitle(String title) {
        ActionUtils.performAction(getClass(), () -> {
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(ExpectedConditions.titleContains(title));
                    return null;
                }, "Successfully loaded page with the title: " + title,
                "Failed to load the page with the expected title: " + title);
    }



    protected void sendKeys(WebElement element, String value, String elementName) {
        ActionUtils.performAction(getClass(), () -> {
                    WebElement webElement = waitForElementVisibility(element);
                    webElement.clear();
                    webElement.sendKeys(value);
                    return null;
                }, "Send keys to " + elementName + ":" + value,
                "Failed to send keys to " + elementName);
    }

    protected void sendKeysUsingJavaScript(WebElement element, String value, String elementName) {
        ActionUtils.performAction(getClass(), () -> {
                    WebElement webElement = waitForElementVisibility(element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", webElement, value);
                    return null;
                }, "Send keys to " + elementName + " using JavaScript: " + value,
                "Failed to send keys to " + elementName + " using JavaScript");
    }

    protected void click(WebElement element, String elementName) {
        ActionUtils.performAction(getClass(), () -> {
                    WebElement webElement = waitForElementVisibility(element);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
                    return null;
                }, "Click on " + elementName,
                "Failed to click on " + elementName);
    }

    protected String getText(WebElement element,String elementName){
        return ActionUtils.performAction(getClass(),()->{
            WebElement webElement=waitForElementVisibility(element);
            return webElement.getText().trim();
        },"Retrieve text from "+elementName+":"+element.getText(),"Failed to retrieve text from "+elementName);
    }

    protected void moveTo(WebElement element,String elementName){
        ActionUtils.performAction(getClass(),()->{
            WebElement webElement=waitForElementVisibility(element);
            new Actions(driver).moveToElement(webElement).build().perform();
            return null;
        },"Moved to "+elementName,"Failed dto move to "+elementName);
    }

    protected void selectByVisibleText(WebElement element,String value,String elementName){
        ActionUtils.performAction(getClass(),()->{
            WebElement webElement=waitForElementVisibility(element);
            new Select(webElement).selectByVisibleText(value);
            return null;
        },"Select from "+elementName+":"+value,"Failed to select from "+elementName);
    }

    protected void SelectByVisibleText(WebElement element,String value,String elementName){
        ActionUtils.performAction(getClass(),()->{
            WebElement webElement=waitForElementVisibility(element);
            new Select(webElement).selectByVisibleText(value);
            return null;
        },"Select value from "+elementName+" dropdown: "+value,"Failed to select "+value+" from "+elementName+" dropdown");
    }

    public String getCurrentUrl(){
        return ActionUtils.performAction(getClass(), driver::getCurrentUrl,
                "Retrieve current url: "+driver.getCurrentUrl(),"Failed to retrieve current url");
    }

    public String getTitle(){
        return ActionUtils.performAction(getClass(),driver::getTitle,
                "Retrieve current title:"+driver.getTitle(),"Failed to retrieve current title");
    }
    public boolean isDisplayed(WebElement element, String elementName) {
        return ActionUtils.performAction(getClass(), () -> {
                    WebElement webElement = waitForElementVisibility(element);
                    return webElement.isDisplayed();
                },
                "Checked if " + elementName + " is displayed: " + true,
                "Failed to check if " + elementName + " is displayed");
    }


}
