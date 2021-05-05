package com.revature.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.FMReimbursementDTO;
import com.revature.dto.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.StatusCode;
import com.revature.models.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.SessionUtility;

public class ReimbursementRepository {
	private Session session; 
	
	public ReimbursementRepository() {
		this.session = SessionUtility.getSessionFactory().openSession();
	}

	public ArrayList<FMReimbursementDTO> getReimbursements(User user) {
		try {
			List<Reimbursement> DAOreimbursements = (List<Reimbursement>) session.createQuery("SELECT r FROM Reimbursement r JOIN r.reimb_author u WHERE u.userID = :id")
					.setParameter("id", user.getUserID()).getResultList();
			ArrayList<FMReimbursementDTO> reimbursements = new ArrayList<FMReimbursementDTO>(); 
			for(Reimbursement r : DAOreimbursements) {
				FMReimbursementDTO reimb = new FMReimbursementDTO(); 
				reimb.setId(String.valueOf(r.getReimb_id()));
				reimb.setAmount(r.getReimb_amount());
				reimb.setDescription(r.getReimb_desc());
				reimb.setReceipt(r.getReimb_receipt());
				reimb.setAuthor(r.getReimb_author().getUsername());
				if(r.getReimb_resolver()!=null) {
					reimb.setResolver(r.getReimb_resolver().getUsername());
				}
				reimb.setStatus(r.getReimb_status().getStatus_code_id());
				reimb.setType(r.getReimb_type().getReimb_type());
				reimbursements.add(reimb); 
			}
			return reimbursements; 
			
		}catch(NoResultException e){
			return null; 
		}
	}

	public ArrayList<FMReimbursementDTO> getReimbursementsForFinanceManagers(User user) {
		try {
			List<Reimbursement> DAOreimbursements = (List<Reimbursement>) session.createQuery("SELECT r FROM Reimbursement r")
					.getResultList();
			ArrayList<FMReimbursementDTO> reimbursements = new ArrayList<FMReimbursementDTO>(); 
			for(Reimbursement r : DAOreimbursements) {
				FMReimbursementDTO reimb = new FMReimbursementDTO(); 
				reimb.setId(String.valueOf(r.getReimb_id()));
				reimb.setAmount(r.getReimb_amount());
				reimb.setSubmitted(r.getReimb_submit_time());
				reimb.setResolved(r.getReimb_resolve_time());
				reimb.setDescription(r.getReimb_desc());
				reimb.setReceipt(r.getReimb_receipt());
				reimb.setAuthor(r.getReimb_author().getUsername());
				if(r.getReimb_resolver()!=null) {
					reimb.setResolver(r.getReimb_resolver().getUsername());
				}
				reimb.setStatus(r.getReimb_status().getStatus_code_id());
				reimb.setType(r.getReimb_type().getReimb_type());
				reimbursements.add(reimb); 
			}
			return reimbursements; 
			
		}catch(NoResultException e){
			return null; 
		}
	}

	public Reimbursement updateReimbursementStatus(ReimbursementDTO reimbToBeUpdated) {
		try {
			Transaction tx = session.beginTransaction(); 
			Reimbursement reimbursement = session.find(Reimbursement.class, reimbToBeUpdated.getId());
			StatusCode statusCode = session.find(StatusCode.class, reimbToBeUpdated.getStatus()); 
			reimbursement.setReimb_status(statusCode);
			tx.commit();
			
			return reimbursement; 
			
		}catch(NoResultException e){
			return null; 
		}
	}
}
