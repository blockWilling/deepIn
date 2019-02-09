package com.spring5.deepIn;

import com.spring5.anno.MyComponent;
import com.spring5.beanNameGenerators.MyBeanNameGenerator;
import com.spring5.conf.normalBeanConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spring5","com.java8"},nameGenerator = MyBeanNameGenerator.class)
@PropertySource("classpath:myPro.sql")

public class DeepInApplication {

//	private DeepInApplication(){}
public String out(){
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



}

