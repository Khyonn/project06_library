package fr.nmocs.library.model.constants;

public enum BookSampleStatus {

	AVAILABLE("A"),
	DAMAGED("D"),
	UNAVAILABLE("U");

	private String value;

	private BookSampleStatus (String value) {
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
