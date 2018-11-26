package fr.nmocs.model.constants;

public enum BookStatus {

	AVAILABLE("A"),
	UNAVAILABLE("U");

	private String value;

	private BookStatus (String value) {
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
