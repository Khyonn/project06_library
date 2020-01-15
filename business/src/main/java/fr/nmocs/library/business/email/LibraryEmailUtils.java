package fr.nmocs.library.business.email;

import java.util.List;

import fr.nmocs.library.business.model.LibraryEmail;
import fr.nmocs.library.model.error.LibraryTechnicalException;

public interface LibraryEmailUtils {

	void sendEmails(List<LibraryEmail> emails) throws LibraryTechnicalException;

	void sendEmail(LibraryEmail email) throws LibraryTechnicalException;

}
