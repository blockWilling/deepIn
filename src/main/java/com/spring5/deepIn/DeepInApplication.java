package com.spring5.deepIn;

import com.spring5.beanNameGenerators.MyBeanNameGenerator;
import com.spring5.event.myEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring5","com.java8"},nameGenerator = MyBeanNameGenerator.class)
@PropertySource("classpath:properties/myPro.sql")
//@PropertySource("classpath*:com/**/simpleService.java")
public class DeepInApplication {

//	private DeepInApplication(){}
	@Bean
public static String out(){
	return "11";
}
	public static void main(String[] args) {
	SpringApplication.run(DeepInApplication.class, args);
//		new SpringApplicationBuilder()
//				.sources(DeepInApplication.class,
//						normalBeanConf.class)
//				.run(args);
//System.out.print("22");
    }
	@EventListener(myEvent.class)
	public void receieveMyEvent(myEvent event) {
		System.out.println(event.getSource().toString());
	}


}

