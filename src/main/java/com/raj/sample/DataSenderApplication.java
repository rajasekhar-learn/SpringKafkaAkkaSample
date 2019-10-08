package com.raj.sample;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.raj.sample.actors.KakfkaPushTaskSupervisor;
import com.raj.sample.components.BilProperties;
import com.raj.sample.components.MyComponent;
import com.raj.sample.extensions.SpringExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static akka.actor.TypedActor.context;

@SpringBootApplication
@EnableCaching
public class DataSenderApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context =
				SpringApplication.run(DataSenderApplication.class, args);

		ActorSystem system = context.getBean(ActorSystem.class);

		final LoggingAdapter log = Logging.getLogger(system, "Application");

		SpringExtension ext = context.getBean(SpringExtension.class);

		ActorRef supervisor = system.actorOf(
				ext.props("kakfkaPushTaskSupervisor"));

		for (int i = 1; i < 1000; i++) {
			supervisor.tell(String.valueOf(i), null);
		}

		Thread.sleep(100000000);
		log.info("Shutting down");
		Await.ready(system.terminate(), Duration.Inf());

	}


}
