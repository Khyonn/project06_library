package fr.nmocs.library.model.constants;

public enum UserStatus {

	ACTIVE("A"),
	UNACTIVE("U");

	private String value;

	private UserStatus (String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
