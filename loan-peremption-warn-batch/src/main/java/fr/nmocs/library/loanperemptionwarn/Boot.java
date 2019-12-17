package fr.nmocs.library.loanperemptionwarn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Boot {

	public static void main(String[] args) throws Exception {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(Boot.class, args);
	}
}
