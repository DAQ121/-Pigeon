package com.aqdai.pigeonweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aqdai.pigeonweb.dao")
public class PigeonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigeonWebApplication.class, args);
	}

}
