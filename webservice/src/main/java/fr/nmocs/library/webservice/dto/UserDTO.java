package fr.nmocs.library.webservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String status;

	private UserOptionsDTO options;
}
