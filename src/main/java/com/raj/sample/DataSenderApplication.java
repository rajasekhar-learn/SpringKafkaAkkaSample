package com.raj.sample.datasender;
import com.raj.sample.datasender.components.BilProperties;
import com.raj.sample.datasender.components.MyComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class DataSenderApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DataSenderApplication.class, args);
		MyComponent myComponent=applicationContext.getBean(MyComponent.class);
		myComponent.m1();
		BilProperties bilProperties=applicationContext.getBean(BilProperties.class);
		String val=Arrays.deepToString(bilProperties.getTest().keySet().toArray());
		System.out.println("Bil Propertie :: "+val);

	}


}
