package fr.nmocs.library.webservice.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("fr.nmocs.library")
@ImportResource("classpath:/app-context.xml")
@PropertySource("classpath:/business.properties")
public class SpringConfiguration {

}
