package fr.nmocs.library.model.error;

public enum ErrorCode {

	ERROR("ERROR"),

	// ===== TECHNICAL

	USER_NOT_FOUND("LTE0000"),

	USER_DUPLICATED_EMAIL("LTE0001"),

	// ===== BUSINESS

	USER_UNSETTED("LBE0000"),

	USER_UNSETTED_LASTNAME("LBE0001"),

	USER_UNSETTED_FIRSTNAME("LBE002"),

	USER_UNSETTED_PASSWORD("LBE0003"),

	USER_UNSETTED_EMAIL("LBE0004"),

	USER_OVERSIZED_LASTNAME("LBE0005"),

	USER_OVERSIZED_FIRSTNAME("LBE006"),

	USER_OVERSIZED_PASSWORD("LBE0007"),

	USER_OVERSIZED_EMAIL("LBE0008"),

	USER_DOESNT_EXIST("LBE0009");

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
