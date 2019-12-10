package com.whiteslave.whiteslaveApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WhiteslaveAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteslaveAppApplication.class, args);
	}

}
