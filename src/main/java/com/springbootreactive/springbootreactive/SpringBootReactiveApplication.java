package com.springbootreactive.springbootreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootReactiveApplication {

	public static void main(String[] args) {
//		BlockHound.builder()
//				.allowBlockingCallsInside(
//						// 타임리프 템플릿엔진 블록킹 코드 허용
//						TemplateEngine.class.getCanonicalName(),"process")
//				.install();

		SpringApplication.run(SpringBootReactiveApplication.class, args);
	}

}
