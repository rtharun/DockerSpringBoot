package com.tharun.docker.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerSpringApplication.class, args);
		new DummyLogger().logStuff();
	}
}
