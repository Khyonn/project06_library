package fr.nmocs.library.webservice.error;

import fr.nmocs.library.model.error.ErrorCode;

public class LibraryWebserviceException extends Exception {

	private static final long serialVersionUID = 4136368364038095853L;

	private String message;

	public LibraryWebserviceException () {
	}

	public LibraryWebserviceException (String message) {
		this.message = message;
	}

	public LibraryWebserviceException (ErrorCode ec) {
		this.message = ec.getId();
	}

	public LibraryWebserviceException (ErrorCode ec, String message) {
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
