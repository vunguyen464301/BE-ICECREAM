package com.icecream.IceCream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IceCreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(IceCreamApplication.class, args);
	}

}
