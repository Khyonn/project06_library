package fr.nmocs.library.model.error;

@SuppressWarnings("serial")
public class LibraryBusinessException extends LibraryException {

	public LibraryBusinessException () {
	}

	public LibraryBusinessException (ErrorCode errorCode) {
		super(errorCode);
	}

	public LibraryBusinessException (ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
}
