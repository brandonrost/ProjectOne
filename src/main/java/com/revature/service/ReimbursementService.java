package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementRepository;
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

	public static List<Reimbursement> getReimbursements(User user) {
		List<Reimbursement> reimbursements = reimbRepo.getReimbursements(user); 
		return reimbursements; 
	}

}
