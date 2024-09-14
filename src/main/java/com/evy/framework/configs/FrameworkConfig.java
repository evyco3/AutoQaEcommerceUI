package com.evy.framework.configs;

import com.evy.framework.drivers.BrowserType;
import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/config.properties")
public interface FrameworkConfig extends Config {

    @ConverterClass(BrowserTypeConverter.class)
    @Key("browserType")
    @DefaultValue("CHROME")
    BrowserType browserType();

    @Key("baseUrl")
    String baseUrl();

    @Key("implicitTime")
    int implicitTime();

    @Key("pageLoadTime")
    int pageLoadTime();

    @Key("email")
    String email();

    @Key("password")
    String password();
}
