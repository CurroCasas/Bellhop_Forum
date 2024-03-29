package forum;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = run(App.class, args);
	}
}
