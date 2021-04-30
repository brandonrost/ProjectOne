package com.revature.controllers;

import java.util.List;

import com.revature.dto.MessageDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbController implements Controller {

	private ReimbursementService reimbService;

	public ReimbController() {
		this.reimbService = new ReimbursementService();
	}

	private Handler reimbHandler = (ctx) -> {
		User user = ctx.bodyAsClass(User.class); 		
		List<Reimbursement> reimbursements = ReimbursementService.getReimbursements(user); 

		if (reimbursements == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("Unable to retrieve reimbursements from the database.");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
			ctx.json(reimbursements);
		}
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/reimbursements", reimbHandler);

	}

}
