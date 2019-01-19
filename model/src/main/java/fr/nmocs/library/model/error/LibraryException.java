package fr.nmocs.library.model.error;

@SuppressWarnings("serial")
public class LibraryException extends Exception {

	private ErrorCode errorCode;

	private String message;

	public LibraryException () {
	}

	public LibraryException (ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public LibraryException (ErrorCode errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
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
