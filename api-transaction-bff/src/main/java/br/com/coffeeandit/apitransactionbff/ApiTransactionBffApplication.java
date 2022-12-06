package br.com.coffeeandit.apitransactionbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = {"br.com.coffeeandit.apitransactionbff.redis"})
public class ApiTransactionBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTransactionBffApplication.class, args);
	}

}
