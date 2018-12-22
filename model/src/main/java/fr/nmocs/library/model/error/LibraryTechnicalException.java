package fr.nmocs.library.model.error;

@SuppressWarnings("serial")
public class LibraryTechnicalException extends LibraryException {

	public LibraryTechnicalException () {
	}

	public LibraryTechnicalException (ErrorCode errorCode) {
		super(errorCode);
	}

	public LibraryTechnicalException (ErrorCode errorCode, String message) {
		super(errorCode, message);
	}

}
