package fr.nmocs.library.webservice.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("fr.nmocs.library")
@ImportResource("classpath:/app-context.xml")
public class SpringConfiguration {

}
