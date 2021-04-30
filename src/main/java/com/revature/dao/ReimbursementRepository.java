package com.revature.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.SessionUtility;

public class ReimbursementRepository {
	private Session session; 
	
	public ReimbursementRepository() {
		this.session = SessionUtility.getSessionFactory().openSession();
	}

	public List<Reimbursement> getReimbursements(User user) {
		try {
			List<Reimbursement> reimbursements = (List<Reimbursement>) session.createQuery("SELECT r FROM Reimbursement r JOIN r.reimb_author u WHERE u.userID = :id")
					.setParameter("id", user.getUserID()).getResultList();
			return reimbursements; 
			
		}catch(NoResultException e){
			return null; 
		}
	}
	
	

}
