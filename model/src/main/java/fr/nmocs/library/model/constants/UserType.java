package fr.nmocs.library.model.constants;

public enum UserType {
	ADMIN("ADMIN"),
	BASIC("BASIC");

	private String value;

	private UserType (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
