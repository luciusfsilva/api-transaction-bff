package br.com.coffeeandit.apitransactionsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"br.com.coffeeandit.apitransactionsvc.repository"})
public class ApiTransactionSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTransactionSvcApplication.class, args);
	}

}
