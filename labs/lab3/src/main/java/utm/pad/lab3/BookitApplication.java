package utm.pad.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class BookitApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookitApplication.class, args);
	}
}
