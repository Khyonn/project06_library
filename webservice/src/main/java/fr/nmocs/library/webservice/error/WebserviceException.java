package fr.nmocs.library.webservice.error;

import fr.nmocs.library.model.error.ErrorCode;

public class WebserviceException extends Exception {

	private static final long serialVersionUID = 4136368364038095853L;

	private String message;

	public WebserviceException () {
	}

	public WebserviceException (String message) {
		this.message = message;
	}

	public WebserviceException (ErrorCode ec) {
		this.message = ec.getId();
	}

	public WebserviceException (ErrorCode ec, String message) {
		this.message = ec.getId() + " : " + message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
