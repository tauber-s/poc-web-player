package com.poc.webplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebPlayerApplication.class, args);
	}
	@GetMapping("/")
	public String home() {
		return "hello world";
	}

}
