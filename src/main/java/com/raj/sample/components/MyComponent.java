package com.raj.sample.components;

import com.raj.sample.configuration.CacheService;
import com.raj.sample.model.SimpleData;
import com.raj.sample.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyComponent {

    @Autowired
    MyService myService;

    @Value("$ {component.input}")
    String componentValue;

    @Autowired
    CacheService cacheService;


    public void m1(){
        String data=myService.someCall() + "component value :: "+ componentValue;
        System.out.println("final response :: "+data);

        SimpleData simpleData=cacheService.getSimpleData("mydata.yml");
        System.out.println("cache data :: "+ Arrays.deepToString(simpleData.getInput().keySet().toArray()));
        simpleData=cacheService.getSimpleData("mydata.yml");
        System.out.println("cache data again :: "+ Arrays.deepToString(simpleData.getInput().keySet().toArray()));
    }

}
