package fst.spring.cloud.ams.erukaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringErukaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringErukaClientApplication.class, args);
	}
}
