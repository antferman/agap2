package agap2.heroe.model;

public class CustomException extends Exception {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public CustomException() {
		super(null, null, true, false);
	}

	public CustomException(String msg) {
		super(msg, null, true, false);
	}

	public CustomException(String msg, Throwable cause) {
		super(msg, cause, true, false);
	}
}
