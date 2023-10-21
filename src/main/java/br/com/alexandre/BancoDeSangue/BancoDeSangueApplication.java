package br.com.alexandre.BancoDeSangue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BancoDeSangueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoDeSangueApplication.class, args);
	}

}
