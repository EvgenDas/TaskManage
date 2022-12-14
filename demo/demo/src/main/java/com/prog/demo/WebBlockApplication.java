package com.prog.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com/prog/demo/contollers"})

public class WebBlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBlockApplication.class, args);
	}

}
