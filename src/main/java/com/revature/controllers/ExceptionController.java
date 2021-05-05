package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.ReimbursementNotDeletedException;
import com.revature.exceptions.ReimbursementNotUpdatedException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller {
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		logger.warn(e.getMessage());
		ctx.status(400);
	};
	
	private ExceptionHandler<LoginException> loginExceptionHandler = (e, ctx) -> {
		logger.warn(e.getMessage());
		ctx.status(401); 
	};

	private ExceptionHandler<ReimbursementNotUpdatedException> reimbursementNotUpdatedExceptionHandler = (e, ctx) -> {
		logger.warn(e.getMessage());
		ctx.status(403); 
	};
	
	private ExceptionHandler<ReimbursementNotDeletedException> reimbursementNotDeletedExceptionHandler = (e, ctx) -> {
		logger.warn(e.getMessage());
		ctx.status(402); 
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(BadParameterException.class, badParameterExceptionHandler);
		app.exception(LoginException.class, loginExceptionHandler);
		app.exception(ReimbursementNotUpdatedException.class, reimbursementNotUpdatedExceptionHandler); 
		app.exception(ReimbursementNotDeletedException.class, reimbursementNotDeletedExceptionHandler);
	}

}
