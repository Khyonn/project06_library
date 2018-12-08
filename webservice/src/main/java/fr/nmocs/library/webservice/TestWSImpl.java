package fr.nmocs.library.webservice;

import javax.jws.WebService;

@WebService(endpointInterface = "fr.nmocs.library.webservice.TestWS")
public class TestWSImpl implements TestWS {

	@Override
	public Integer getAddition(int a, int b) {
		return a + b;
	}
}
