package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dto.FMReimbursementDTO;
import com.revature.dto.MessageDTO;
import com.revature.dto.ReimbursementActionDTO;
import com.revature.dto.ReimbursementDTO;
import com.revature.dto.addReimbursementDTO;
import com.revature.dto.deleteReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbController implements Controller {

	private ReimbursementService reimbService;
	private Logger logger = LoggerFactory.getLogger(ReimbController.class);

	public ReimbController() {
		this.reimbService = new ReimbursementService();
	}

	private Handler reimbHandler = ctx -> {
		logger.info("Now submitting POST request to Finance Manager Reimbursement Handler");
		User user = ctx.sessionAttribute("currentlyLoggedInUser"); 		
		List<FMReimbursementDTO> reimbursements = ReimbursementService.getReimbursements(user); 
		
		if (reimbursements == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("Unable to retrieve reimbursements from the database.");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
			ctx.json(reimbursements);
		}
	};
	
	private Handler updateStatusReimbHandler = ctx -> {
		logger.info("Now submitting PUT request to Reimbursement Handler");	
		ReimbursementActionDTO reimbToBeUpdated = ctx.bodyAsClass(ReimbursementActionDTO.class); 
		Reimbursement reimbursement = ReimbursementService.updateReimbursementStatus(reimbToBeUpdated); 
		
		if (reimbursement == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("Unable to update reimbursement on the database.");
			ctx.json(messageDTO);
			ctx.status(403);
		} else {
			ctx.json(reimbursement);
			ctx.status(200);
		}
	};
	
	private Handler deleteReimbursementHandler = ctx -> {
		logger.info("Now submitting Delete request to Reimbursement Handler");	
		deleteReimbursementDTO reimbToBeDeleted = ctx.bodyAsClass(deleteReimbursementDTO.class);
		Reimbursement reimbursement = ReimbursementService.deleteReimbursement(reimbToBeDeleted); 
		
		if (reimbursement == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("Unable to update reimbursement on the database.");
			ctx.json(messageDTO);
			ctx.status(403);
		} else {
			ctx.json(reimbursement);
			ctx.status(200);
		}
	};
	
	private Handler addReimbursementHandler = ctx -> {
		logger.info("Now submitting POST request to Reimbursement Handler");	
		addReimbursementDTO reimbToBeAdded = ctx.bodyAsClass(addReimbursementDTO.class);
		Reimbursement reimbursement = ReimbursementService.addReimbursement(reimbToBeAdded); 
		
		if (reimbursement == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("Unable to update reimbursement on the database.");
			ctx.json(messageDTO);
			ctx.status(403);
		} else {
			ctx.json(reimbursement);
			ctx.status(200);
		}
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/reimbursements", reimbHandler);
		app.put("/reimbursements", updateStatusReimbHandler); 
		app.delete("reimbursements", deleteReimbursementHandler); 
		app.post("reimbursements/add", addReimbursementHandler);
	}

}
