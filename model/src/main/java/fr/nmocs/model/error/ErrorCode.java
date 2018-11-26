package fr.nmocs.model.error;

public enum ErrorCode {

	ERROR("NO_ERROR");

	private String id;

	private ErrorCode (String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}
}
