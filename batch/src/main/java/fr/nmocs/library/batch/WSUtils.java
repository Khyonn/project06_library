package fr.nmocs.library.batch;

import java.net.MalformedURLException;
import java.net.URL;

public class WSUtils {

	private static String servicesUrl;

	public static void setBaseUrl(String baseUrl) {
		servicesUrl = baseUrl;
	}

	public static URL getUrl(String suffix) {
		try {
			return new URL(servicesUrl + suffix);
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
