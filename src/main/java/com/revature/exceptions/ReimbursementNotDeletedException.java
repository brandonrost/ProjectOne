package com.revature.exceptions;

public class ReimbursementNotDeletedException extends Exception {

	public ReimbursementNotDeletedException() {
		super();
	}

	public ReimbursementNotDeletedException(String message) {
		super(message);
	}

	public ReimbursementNotDeletedException(Throwable cause) {
		super(cause);
	}

	public ReimbursementNotDeletedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReimbursementNotDeletedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
