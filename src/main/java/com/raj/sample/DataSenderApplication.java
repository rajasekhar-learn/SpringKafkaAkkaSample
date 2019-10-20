package com.raj.sample;

import com.raj.sample.components.BilStateKeeper;
import com.raj.sample.services.EmailService;
import com.raj.sample.services.ScheduledTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.raj.sample.constants.SupportConstants.PROPERTY_LOAD;

@SpringBootApplication
@EnableCaching
public class DataSenderApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext context =
				SpringApplication.run(DataSenderApplication.class, args);
		/*ActorSystem system = context.getBean(ActorSystem.class);

		final LoggingAdapter log = Logging.getLogger(system, "Application");

		SpringExtension ext = context.getBean(SpringExtension.class);

		ActorRef supervisor = system.actorOf(
				ext.props("kakfkaPushTaskSupervisor"));
		long st=System.currentTimeMillis();
		for (int i = 1; i <= 100000; i++) {
			supervisor.tell(String.valueOf(i), null);
		}
		Thread.sleep(9000);
		System.out.println("time took :: "+(System.currentTimeMillis()-st));
		Thread.sleep(1000000);
		log.info("Shutting down");
		Await.ready(system.terminate(), Duration.Inf());*/
		/*try {
			EmailService emailService = context.getBean(EmailService.class);
			emailService.sendSimpleMail("rajasekhar veeragandhapu", "rveeragandhapu@gmail.com", Locale.ENGLISH);
			System.out.println("email sent !!");
		}catch (Exception e){
			e.printStackTrace();
		}
		*/
		BilStateKeeper bilStateKeeper=context.getBean(BilStateKeeper.class);
		ScheduledTask scheduledTask=context.getBean(ScheduledTask.class);
		ExecutorService executorService= Executors.newCachedThreadPool();
		executorService.execute(()->{
			scheduledTask.doAction(PROPERTY_LOAD);
		});

		Thread.sleep(25000);
		System.out.println("Main thread :: updated vales :: keys ::  "+ Arrays.deepToString(BilStateKeeper.getAppConfig().get().getBil().keySet().toArray())+
				" values :: "+Arrays.deepToString(BilStateKeeper.getAppConfig().get().getBil().values().toArray()));

		Thread.sleep(100000000000l);
		System.exit(0);
	}


}
