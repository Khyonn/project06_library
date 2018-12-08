package fr.nmocs.library.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TestWS {

	@WebMethod
	Integer getAddition(int a, int b);

}
