package com.springbootreactive.springbootreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringBootReactiveApplication {
	// 블록하운드

	public static void main(String[] args) {
		BlockHound.install();

		SpringApplication.run(SpringBootReactiveApplication.class, args);
	}

}
