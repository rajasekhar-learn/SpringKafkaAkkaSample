package com.raj.sample.configuration;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.InputStream;

public class YmlConfigurationManager<T> {
    public final static YmlConfigurationManager YMLCONFIG=new YmlConfigurationManager();

    private YmlConfigurationManager(){}

    public static Object load(String path,Class T){
        return YMLCONFIG.loadConfiguartion(path,T);
    }

    public  T loadConfiguartion(String path,Class c){
        Yaml yaml = new Yaml(new Constructor(c));
        InputStream inputStream=null;
        try {
            inputStream = new FileInputStream(path);
            return  yaml.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            }catch (Exception ex){

            }
        }
        return null;
    }

}
