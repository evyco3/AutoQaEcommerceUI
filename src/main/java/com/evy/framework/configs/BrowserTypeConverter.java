package com.evy.framework.configs;

import com.evy.framework.drivers.BrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public final class BrowserTypeConverter implements Converter<BrowserType> {

    @Override
    public BrowserType convert(Method method, String browserType) {
        try {
            return BrowserType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(String.format("Invalid browser type: '%s'. Supported browsers: %s", browserType, e));
        }
    }


}
