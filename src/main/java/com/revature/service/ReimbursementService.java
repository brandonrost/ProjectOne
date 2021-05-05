package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.ReimbursementRepository;
import com.revature.dto.FMReimbursementDTO;
import com.revature.dto.ReimbursementActionDTO;
import com.revature.dto.addReimbursementDTO;
import com.revature.dto.deleteReimbursementDTO;
import com.revature.exceptions.ReimbursementNotDeletedException;
import com.revature.exceptions.ReimbursementNotUpdatedException;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {
	
	private static ReimbursementRepository reimbRepo; 

	public ReimbursementService() {
		this.reimbRepo = new ReimbursementRepository(); 
	}
	
	//for mockito
	public ReimbursementService(ReimbursementRepository reimbRepo) {
		this.reimbRepo = reimbRepo;
	}

	public static ArrayList<FMReimbursementDTO> getReimbursements(User user) {
		ArrayList<FMReimbursementDTO> reimbursements; 
		if(user.getUser_role().getUser_role_id() ==1) {
			reimbursements = reimbRepo.getReimbursements(user); 
		}else {
			reimbursements = reimbRepo.getReimbursementsForFinanceManagers(user); 
		}
		return reimbursements; 
	}

	public static Reimbursement updateReimbursementStatus(ReimbursementActionDTO reimbToBeUpdated) throws ReimbursementNotUpdatedException {
		Reimbursement reimbursement = reimbRepo.updateReimbursementStatus(reimbToBeUpdated); 
		if(reimbursement == null) {
			throw new ReimbursementNotUpdatedException("Could not update reimbursement in the database.");
		}
		return reimbursement; 
	}

	public static Reimbursement deleteReimbursement(deleteReimbursementDTO reimbToBeDeleted) throws ReimbursementNotDeletedException {
		Reimbursement reimbursement = reimbRepo.deleteReimbursement(reimbToBeDeleted);
		if(reimbursement == null) {
			throw new ReimbursementNotDeletedException("Could not delete reimbursement from the database.");
		}
		return reimbursement; 
	}

	public static Reimbursement addReimbursement(addReimbursementDTO reimbToBeAdded) {
		if(reimbToBeAdded.getAmount() != 0 && reimbToBeAdded.getType()!=null) {
			Reimbursement reimbursement = reimbRepo.addReimbursement(reimbToBeAdded); 
			return reimbursement; 
		}
		return null; 
	}
}
