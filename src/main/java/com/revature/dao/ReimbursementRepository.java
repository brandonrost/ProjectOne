package com.revature.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.FMReimbursementDTO;
import com.revature.dto.ReimbursementActionDTO;
import com.revature.dto.addReimbursementDTO;
import com.revature.dto.deleteReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.models.StatusCode;
import com.revature.models.User;
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

	public Reimbursement updateReimbursementStatus(ReimbursementActionDTO reimbToBeUpdated) {
		try {
			Transaction tx = session.beginTransaction(); 
			Reimbursement reimbursement = session.find(Reimbursement.class, reimbToBeUpdated.getId());
			StatusCode statusCode = session.find(StatusCode.class, reimbToBeUpdated.getStatus_id());
			reimbursement.setReimb_resolver(session.get(User.class, reimbToBeUpdated.getResolver()));
			reimbursement.setReimb_status(statusCode);
			reimbursement.setReimb_resolve_time(LocalTime.now().atDate(LocalDate.now()));
			tx.commit();
			
			return reimbursement; 
			
		}catch(NoResultException e){
			return null; 
		}
	}

	public Reimbursement deleteReimbursement(deleteReimbursementDTO reimbToBeDeleted) {
		try {
			Transaction tx = session.beginTransaction();
			Reimbursement reimbursement = session.get(Reimbursement.class, reimbToBeDeleted.getId()); 
			User user = session.get(User.class, reimbToBeDeleted.getauthor()); 
			if(reimbursement.getReimb_status().getStatus_code_id()==1 && reimbursement.getReimb_author().getUserID() == reimbToBeDeleted.getauthor()) {
				session.delete(reimbursement);
				tx.commit();
			}
			return reimbursement; 
		}catch(NoResultException e){
			return null; 
		}
	}

	public Reimbursement addReimbursement(addReimbursementDTO reimbToBeAdded) {
		try {
			Map<String, Integer> typeMap = new HashMap<String,Integer>(); 
			typeMap.put("Lodging",1);
			typeMap.put("Travel",2);
			typeMap.put("Food",3);
			typeMap.put("Other",4);
			int typeid = 0; 
			for(String key:typeMap.keySet()) {
				if(key.toString().strip().trim().equals(reimbToBeAdded.getType().toString().strip().trim())) {
					typeid = typeMap.get(key); 
				}
			}
			Transaction tx = session.beginTransaction();
			StatusCode status = session.get(StatusCode.class, 1);
			ReimbursementType type = session.get(ReimbursementType.class, typeid); 
			Reimbursement reimbursement = new Reimbursement(reimbToBeAdded.getAmount(), LocalTime.now().atDate(LocalDate.now()),session.get(User.class,reimbToBeAdded.getSubmitter()), status, type);
			session.persist(reimbursement);
			tx.commit();
			return reimbursement; 
		}catch(NoResultException e){
			return null; 
		}
	}
	
}
