package br.com.coffeeandit.apitransactionbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRedisRepositories(basePackages = {"br.com.coffeeandit.apitransactionbff.redis"})
@EnableFeignClients
@EnableRetry
public class ApiTransactionBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTransactionBffApplication.class, args);
	}

}
