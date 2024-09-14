package com.evy.framework.drivers;

import com.evy.framework.configs.Config;
import com.evy.framework.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Driver {

    private final static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Driver INSTANCE = new Driver();  // Singleton instance

    // Private constructor to prevent instantiation
    private Driver() {
    }

    // Singleton method to get the instance
    public static Driver getInstance() {
        return INSTANCE;
    }

    // Initialize WebDriver
    public void init() {
        BrowserType browserType = Config.get().browserType();  // Fetch browser type from config
        try {
            WebDriver driver = new DriverManager().get(browserType);  // Get WebDriver instance
            if (driver == null) {
                throw new RuntimeException("Failed to get WebDriver instance from DriverManager.");
            }
            driverThreadLocal.set(driver);  // Set WebDriver to ThreadLocal
            LoggerUtils.info(Driver.class, "WebDriver instance set to ThreadLocal for browser: " + browserType);
            configure(driver);  // Configure driver with timeouts, cookies, etc.
        } catch (Exception e) {
            LoggerUtils.error(Driver.class, "Error initializing or configuring WebDriver for browser: " + browserType, e);
            throw new RuntimeException("Failed to initialize or configure WebDriver", e);
        }
    }

    // Get the current thread's WebDriver instance
    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Quit WebDriver and clean up ThreadLocal
    public void quit() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();  // Quit the WebDriver session
                driverThreadLocal.remove();  // Remove the instance from ThreadLocal
                LoggerUtils.info(Driver.class, "WebDriver instance successfully quit and removed from ThreadLocal.");
            } catch (Exception e) {
                LoggerUtils.error(Driver.class, "Error while quitting WebDriver", e);
                throw new RuntimeException("Error while quitting WebDriver", e);
            }
        } else {
            LoggerUtils.warn(Driver.class, "No WebDriver instance found to quit.");
        }
    }

    // Configure the WebDriver settings (timeouts, cookies, etc.)
    private static void configure(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.get().pageLoadTime()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.get().implicitTime()));
        driver.manage().deleteAllCookies();
        driver.get(Config.get().baseUrl());  // Navigate to the base URL
    }
}
