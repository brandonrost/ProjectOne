package com.revature.service;

import java.util.ArrayList;

import com.revature.dao.ReimbursementRepository;
import com.revature.dto.FMReimbursementDTO;
import com.revature.dto.ReimbursementDTO;
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

	public static Reimbursement updateReimbursementStatus(ReimbursementDTO reimbToBeUpdated) throws ReimbursementNotUpdatedException {
		Reimbursement reimbursement = reimbRepo.updateReimbursementStatus(reimbToBeUpdated); 
		if(reimbursement == null) {
			throw new ReimbursementNotUpdatedException("Could not update reimbursement in the database.");
		}
		return reimbursement; 
	}
}
