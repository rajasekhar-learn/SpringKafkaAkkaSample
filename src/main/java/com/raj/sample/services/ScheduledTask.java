package com.raj.sample.services;

import com.raj.sample.components.BilStateKeeper;
import com.raj.sample.configuration.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.raj.sample.constants.SupportConstants.PROPERTY_LOAD;

@Component
public class ScheduledTask implements Task {

    @Autowired
    CacheService cacheService;
    @Autowired
    BilStateKeeper bilStateKeeper;

    private Long constantTime=20000l;

    public ScheduledTask(Long scheduleTime){
        this.constantTime=scheduleTime;
    }
    public ScheduledTask(){}


    @Override
    public Object doAction(Object... dependencies) {
        try {
            String taskId=String.valueOf(dependencies[0]);

            while (true) {
                switch (taskId){
                    case PROPERTY_LOAD:
                        loadConfig();
                    default:
                        System.out.println("no matching action !!");
                }
                Thread.sleep(constantTime);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error while performing scheduled task!"+e.getMessage());
        }
        return null;
    }

    private void loadConfig(){
        System.out.println("loading configuration!! time : "+ LocalDateTime.now());
            synchronized (BilStateKeeper.getAppConfig()) {
                System.out.println("loading configuration!! now :: "+ LocalDateTime.now());
                BilStateKeeper.getAppConfig().getAndSet(cacheService.loadAppConfig("testapp.yml",false));
                System.out.println("updated vales :: keys ::  "+ Arrays.deepToString(BilStateKeeper.getAppConfig().get().getBil().keySet().toArray())+
                        " values :: "+Arrays.deepToString(BilStateKeeper.getAppConfig().get().getBil().values().toArray()));
            }
    }



}
