package com.example.springbootsample;

//import com.example.springbootsample.springsecurity.WebSecurityConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
public class SpringBootSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
