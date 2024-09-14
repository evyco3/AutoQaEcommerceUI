package com.evy.framework.listeners;

import com.evy.framework.drivers.Driver;
import com.evy.framework.utils.LoggerUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;

public class AllureScreenshotListener extends TestListenerAdapter {

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtils.info(getClass(), "Test started: " + result.getMethod().getMethodName());
        Allure.step("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info(getClass(), "Test passed: " + result.getMethod().getMethodName());
        Allure.step("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshotToAllure();
        LoggerUtils.error(getClass(), "Test failed: " + result.getMethod().getMethodName(), null);
        Allure.step("Test failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.warn(getClass(), "Test skipped: " + result.getMethod().getMethodName());
        Allure.step("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtils.info(getClass(), "All tests finished in suite: " + context.getSuite().getName());
        Allure.step("All tests finished in suite: " + context.getSuite().getName());
    }

    private void attachScreenshotToAllure() {
        try {
            WebDriver driver = Driver.getInstance().getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
            } else {
                LoggerUtils.error(getClass(),"Driver does not support taking screenshots Screenshot Error",null);
            }
        } catch (Exception e) {
            LoggerUtils.error(getClass(), "Failed to capture screenshot: " + e.getMessage()+ "Screenshot Error",e);
        }
    }


}
