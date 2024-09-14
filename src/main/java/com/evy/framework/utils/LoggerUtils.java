package com.evy.framework.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LoggerUtils {

    private LoggerUtils(){}


    private static Logger getLogger(Class<?>clazz){
        return LogManager.getLogger(clazz);
    }

    public static void info(Class<?>clazz,String msg){
        getLogger(clazz).info(msg);
    }

    public static void warn(Class<?>clazz,String msg){
        getLogger(clazz).warn(msg);
    }

    public static void error(Class<?>clazz,String msg,Exception e){
        getLogger(clazz).error(msg,e);
    }
}
