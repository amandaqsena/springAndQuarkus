package springclasses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"springclasses"})
public class SpringClassesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClassesApplication.class, args);
	}

}
