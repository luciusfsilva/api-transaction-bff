package br.com.coffeeandit.apilimitesvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;
import br.com.coffeeandit.apilimitesvc.repository.LimiteDiarioRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = LimiteDiarioRepository.class)
@EntityScan(basePackageClasses = LimiteDiario.class)
public class ApiLimiteSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLimiteSvcApplication.class, args);
	}

}
