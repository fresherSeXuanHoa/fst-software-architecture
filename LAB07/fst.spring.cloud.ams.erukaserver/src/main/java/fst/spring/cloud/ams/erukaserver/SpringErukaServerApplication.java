package fst.spring.cloud.ams.erukaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringErukaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringErukaServerApplication.class, args);
	}
}
