package fr.nmocs.library.webapp;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import fr.nmocs.library.webapp.ws.BookService;
import fr.nmocs.library.webapp.ws.BookService_Service;
import fr.nmocs.library.webapp.ws.LoanService;
import fr.nmocs.library.webapp.ws.LoanService_Service;
import fr.nmocs.library.webapp.ws.TokenService;
import fr.nmocs.library.webapp.ws.TokenServiceService;
import fr.nmocs.library.webapp.ws.UserService;
import fr.nmocs.library.webapp.ws.UserService_Service;

@Configuration
@ComponentScan("fr.nmocs.library.webapp")
@PropertySource("classpath:webservice.properties")
public class SpringConfiguration {

	// ====== Configuration CLIENT SOAP

	@Value("${webservice.services.url}")
	private String webserviceUrl;

	@Bean
	public LoanService getLoanService() {
		if (StringUtils.isBlank(webserviceUrl)) {
			return new LoanService_Service().getLoanServicePort();
		}
		try {
			return (new LoanService_Service(new URL(webserviceUrl + "/loan?wsdl"))).getLoanServicePort();
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Bean
	public BookService getBookService() {
		if (StringUtils.isBlank(webserviceUrl)) {
			return new BookService_Service().getBookServicePort();
		}
		try {
			return new BookService_Service(new URL(webserviceUrl + "/book?wsdl")).getBookServicePort();
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Bean
	public UserService getUserService() {
		if (StringUtils.isBlank(webserviceUrl)) {
			return new UserService_Service().getUserServicePort();
		}
		try {
			return new UserService_Service(new URL(webserviceUrl + "/user?wsdl")).getUserServicePort();
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Bean
	public TokenService getTokenService() {
		if (StringUtils.isBlank(webserviceUrl)) {
			return new TokenServiceService().getTokenServicePort();
		}
		try {
			return new TokenServiceService(new URL(webserviceUrl + "/token?wsdl")).getTokenServicePort();
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
