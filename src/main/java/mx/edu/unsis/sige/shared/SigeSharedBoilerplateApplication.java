package mx.edu.unsis.sige.shared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "mx.edu.unsis.sige")
@EnableJpaRepositories(basePackages = "mx.edu.unsis.sige")
@EntityScan(basePackages = "mx.edu.unsis.sige")
public class SigeSharedBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigeSharedBoilerplateApplication.class, args);
	}

}
