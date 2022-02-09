package br.ufrn.smartSecurityConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // Standup a config server that can communicate with other applications.
@SpringBootApplication
public class SmartSecurityConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSecurityConfigApplication.class, args);
	}

}

/*
 * http://localhost:8888/limits-service/dev 
 */