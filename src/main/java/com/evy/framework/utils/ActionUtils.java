package com.evy.framework.utils;

import io.qameta.allure.Allure;

import java.util.concurrent.Callable;

public final class ActionUtils {

    private ActionUtils(){}


    public static <T>T performAction(Class<?>clazz, Callable<T>callable,String successMsg,String errorMsg){

        try {
          T result=callable.call();
          LoggerUtils.info(clazz,successMsg);
            Allure.step(successMsg);
            return result;
        }
        catch (Exception e){
            LoggerUtils.error(clazz,errorMsg,e);
            Allure.step(errorMsg);
            throw new RuntimeException(errorMsg,e);
        }
    }
}
