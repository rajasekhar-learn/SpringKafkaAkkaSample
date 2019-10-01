package com.raj.sample.datasender.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Value("${service.input}")
    String serviceValue;


    public String someCall(){
        return "Hellow !!... service Value :: "+serviceValue;
    }




}
