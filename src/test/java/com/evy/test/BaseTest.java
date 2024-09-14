package com.evy.test;

import com.evy.framework.drivers.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {



    @BeforeMethod
    public void setup(){
      Driver.getInstance().init();
    }

    @AfterMethod
    public void tearDown(){
        Driver.getInstance().quit();
    }


}
