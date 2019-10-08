package com.raj.sample.configuration;

import com.raj.sample.model.SimpleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class CacheService {

    @Value("$ {application.properties.locations}")
    String configLocationName;

    @Cacheable(value="simpleData")
    public SimpleData getSimpleData(String fileName){
        SimpleData simpleData=null;
        try{
            String path=new File(".").getCanonicalPath() + File.separator +configLocationName+File.separator+ fileName;
            System.out.println("path send to load file :: "+path);
            simpleData=(SimpleData) YmlConfigurationManager.load(path,SimpleData.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return simpleData;
    }
}
