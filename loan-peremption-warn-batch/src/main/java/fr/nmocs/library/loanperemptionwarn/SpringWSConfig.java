package fr.nmocs.library.loanperemptionwarn;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import fr.nmocs.library.loanperemptionwarn.ws.LoanService;
import fr.nmocs.library.loanperemptionwarn.ws.LoanService_Service;
import fr.nmocs.library.loanperemptionwarn.ws.TokenService;
import fr.nmocs.library.loanperemptionwarn.ws.TokenServiceService;

@Configuration
@PropertySource("classpath:webservice.properties")
public class SpringWSConfig {

	// =========== Configuration CLIENT SOAP

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
