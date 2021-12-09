package br.com.bruno.locadora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LocadoraApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(LocadoraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
