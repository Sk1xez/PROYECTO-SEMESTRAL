package com.system_mk.system_mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // <--- Importante


@EnableFeignClients //

@SpringBootApplication
public class SystemMkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemMkApplication.class, args);
	}

}
