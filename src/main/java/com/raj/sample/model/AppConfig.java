package com.raj.sample.model;

import java.util.Map;

public class AppConfig {

    Map<String,String> input;
    Map<String,Object> service;
    Map<String,Object> bil;
    Map<String,Object> component;
    Map<String,Object> application;

    public Map<String, Object> getApplication() {
        return application;
    }

    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    public Map<String, Object> getComponent() {
        return component;
    }

    public void setComponent(Map<String, Object> component) {
        this.component = component;
    }

    public Map<String, String> getInput() {
        return input;
    }

    public void setInput(Map<String, String> input) {
        this.input = input;
    }

    public Map<String, Object> getService() {
        return service;
    }

    public void setService(Map<String, Object> service) {
        this.service = service;
    }

    public Map<String, Object> getBil() {
        return bil;
    }

    public void setBil(Map<String, Object> bil) {
        this.bil = bil;
    }
}
