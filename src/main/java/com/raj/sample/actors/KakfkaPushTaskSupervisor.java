package com.raj.sample.actors;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import akka.japi.pf.DeciderBuilder;
import com.raj.sample.extensions.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class KakfkaPushTaskSupervisor extends UntypedActor {

    @Autowired
    SpringExtension springExtension;

    @Override
    public void onReceive(Object message)  {
        ActorRef actorRef=context().actorOf(springExtension.props("taskActor"));
        actorRef.tell(message,getSender());
    }

   /* @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(false, DeciderBuilder.
                match(Exception.class, e -> {
                    System.out.println("Evaluation of a top level expression failed, restarting.");
                    return SupervisorStrategy.restart();
                }).build());
    }*/
}
