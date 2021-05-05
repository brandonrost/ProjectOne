package com.revature.exceptions;

public class ReimbursementNotUpdatedException extends Exception {

	public ReimbursementNotUpdatedException() {
		super();
	}

	public ReimbursementNotUpdatedException(String message) {
		super(message);
	}

	public ReimbursementNotUpdatedException(Throwable cause) {
		super(cause);
	}

	public ReimbursementNotUpdatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReimbursementNotUpdatedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
