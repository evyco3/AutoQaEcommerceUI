package com.evy.framework.configs;

import org.aeonbits.owner.ConfigCache;

public final class Config {

    private Config(){}


    public static FrameworkConfig get(){
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }


}
