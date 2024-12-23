package br.com.alexandre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"br.com.alexandre.configuration"})
public class BancoDeSangueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoDeSangueApplication.class, args);
	}

}
