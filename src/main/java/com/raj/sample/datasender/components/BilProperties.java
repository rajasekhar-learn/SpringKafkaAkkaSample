package com.raj.sample.datasender.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="bil")
public class BilProperties {

    Map<String,String> test;

    public Map<String, String> getTest() {
        return test;
    }

    public void setTest(Map<String, String> test) {
        this.test = test;
    }
}
