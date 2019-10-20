package com.raj.sample.configuration;

import com.raj.sample.model.AppConfig;
import com.raj.sample.model.SimpleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class CacheService {

    @Value("${application.properties.locations}")
    String configLocationName;

    @Cacheable(value="simpleData")
    public SimpleData getSimpleData(String fileName){
        SimpleData simpleData=null;
        try{
            String path=new File(".").getCanonicalPath() + File.separator +configLocationName+File.separator+ fileName;
            System.out.println("path send to load file :: "+path);
            simpleData=(SimpleData) YmlConfigurationManager.load(path,SimpleData.class,false);

        }catch (Exception e){
            e.printStackTrace();
        }
        return simpleData;
    }

    public AppConfig loadAppConfig(String fileName,boolean isClassPath){
        AppConfig appConfig=null;
        String path=null;
        try{
            if(!isClassPath){
                path=new File(".").getCanonicalPath() + File.separator +configLocationName+File.separator+ fileName;
                System.out.println("path send to load file :: "+path);
            }else {
                path= fileName;
                System.out.println("path send to load file :: "+path);
            }
            appConfig=(AppConfig) YmlConfigurationManager.load(path,AppConfig.class,isClassPath);

        }catch (Exception e){
            e.printStackTrace();
        }
        return appConfig;
    }

}
