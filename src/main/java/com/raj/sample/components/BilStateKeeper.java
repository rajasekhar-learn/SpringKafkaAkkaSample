package com.raj.sample.components;

import com.raj.sample.model.AppConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Scope("singleton")
public class BilStateKeeper {

 public static AtomicReference<AppConfig> appConfig=new AtomicReference<>(new AppConfig());
 public static AtomicReference<Map<String,Object>> cache=new AtomicReference<>(new ConcurrentHashMap<>());

    public static AtomicReference<AppConfig> getAppConfig() {
        return appConfig;
    }

    public static void setAppConfig(AtomicReference<AppConfig> appConfig) {
        BilStateKeeper.appConfig = appConfig;
    }

    public static AtomicReference<Map<String, Object>> getCache() {
        return cache;
    }

    public static void setCache(AtomicReference<Map<String, Object>> cache) {
        BilStateKeeper.cache = cache;
    }
}
