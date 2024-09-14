package com.evy.framework.utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

public final class AssertionUtils {

    private static final Logger logger = LogManager.getLogger(AssertionUtils.class);

    private AssertionUtils() {
    }

    public static <T> void assertEquals(T actual, T expected) {
        try {
            Assertions.assertThat(actual)
                    .isEqualTo(expected);
            String message = String.format("Assertion passed: actual value '%s' is equal to expected value '%s'", actual, expected);
            Allure.step(message);
            logger.info(message);
        } catch (AssertionError e) {
            String errorMessage = String.format("Assertion failed: actual value '%s' is not equal to expected value '%s'", actual, expected);
            Allure.step(errorMessage);
            logger.error(errorMessage, e);
            throw e;
        }
    }

    public static void assertContains(String actual, String expectedSubstring) {
        try {
            Assertions.assertThat(actual)
                    .contains(expectedSubstring);
            String message = String.format("Assertion passed: actual string '%s' contains substring '%s'", actual, expectedSubstring);
            Allure.step(message);
            logger.info(message);
        } catch (AssertionError e) {
            String errorMessage = String.format("Assertion failed: actual string '%s' does not contain substring '%s'", actual, expectedSubstring);
            Allure.step(errorMessage);
            logger.error(errorMessage, e);
            throw e;
        }
    }

    public static void assertTrue(boolean condition, String description) {
        try {
            Assertions.assertThat(condition)
                    .isTrue();
            String message = String.format("Assertion passed: %s", description);
            Allure.step(message);
            logger.info(message);
        } catch (AssertionError e) {
            String errorMessage = String.format("Assertion failed: %s", description);
            Allure.step(errorMessage);
            logger.error(errorMessage, e);
            throw e;
        }
    }
}
