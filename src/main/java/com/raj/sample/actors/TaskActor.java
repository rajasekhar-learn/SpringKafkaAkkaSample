package com.raj.sample.actors;

import akka.actor.UntypedActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TaskActor extends UntypedActor {

    /*@Autowired
    KafkaTemplate kafkaTemplate;*/

    static long count=0;

    @Override
    public void onReceive(Object message) throws Throwable {
        //kafkaTemplate.send("",message);
        System.out.println("Actor msg:: "+String.valueOf(message));
        ++count;
        System.out.println("Actor count :: "+count);
      //  throw new Exception("test exception thrown!!");
    }
}
