package com.evy.framework.drivers;

import com.evy.framework.utils.LoggerUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.EnumMap;
import java.util.function.Supplier;

public final class DriverManager {

    private final EnumMap<BrowserType, Supplier<WebDriver>>BROWSERS_MAP=new EnumMap<>(BrowserType.class);

    public DriverManager(){
        initializeSuppliers();
    }

    private void initializeSuppliers(){
         BROWSERS_MAP.put(BrowserType.CHROME,new ChromeDriverSupplier()::get);
         BROWSERS_MAP.put(BrowserType.FIREFOX,new FirefoxDriverSupplier()::get);
         BROWSERS_MAP.put(BrowserType.EDGE,new EdgeDriverSupplier()::get);
         BROWSERS_MAP.put(BrowserType.OPERA,new OperaDriverSupplier()::get);
         BROWSERS_MAP.put(BrowserType.SAFARI,new SafariDriverSupplier()::get);
         BROWSERS_MAP.put(BrowserType.EXPLORER,new InternetExplorerDriver()::get);
    }

     WebDriver get(BrowserType browserType) {
        WebDriver driver;
        try {
            Supplier<WebDriver> driverSupplier = BROWSERS_MAP.get(browserType);

            if (driverSupplier == null) {
                LoggerUtils.warn(DriverManager.class, "No WebDriver supplier found for browser: " + browserType);
                throw new IllegalArgumentException("No WebDriver supplier found for browser: " + browserType);
            }

            driver=driverSupplier.get();
            LoggerUtils.info(DriverManager.class, "WebDriver initialized successfully for browser: " + browserType);

        } catch (Exception e) {
            LoggerUtils.error(DriverManager.class, "Error occurred while initializing WebDriver for browser: " + browserType, e);
            throw new RuntimeException("Error initializing WebDriver for browser: " + browserType, e);
        }

        return driver;
    }






    private static final class ChromeDriverSupplier implements DriverSupplier{

        @Override
        public WebDriver get() {
            return WebDriverManager.chromedriver().capabilities(new ChromeOptions().addArguments("--headless")).create();
        }
    }

    private static final class FirefoxDriverSupplier implements DriverSupplier{

        @Override
        public WebDriver get() {
            return WebDriverManager.firefoxdriver().capabilities(new FirefoxOptions().addArguments("--headless")).create();
        }
    }

    private static final class EdgeDriverSupplier implements DriverSupplier{

        @Override
        public WebDriver get() {
            return WebDriverManager.edgedriver().create();
        }
    }

    private static final class InternetExplorerDriver implements DriverSupplier{

        @Override
        public WebDriver get() {
            return WebDriverManager.iedriver().create();
        }
    }

    private static final class SafariDriverSupplier implements DriverSupplier{

        @Override
        public WebDriver get() {
            return WebDriverManager.safaridriver().create();
        }
    }

    private static final class OperaDriverSupplier implements DriverSupplier{

        @Override
        public WebDriver get() {
            return WebDriverManager.operadriver().create();
        }
    }


}
