package fr.nmocs.library.business.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryEmail {

	private String to;

	private String subject;

	private String body;
}
