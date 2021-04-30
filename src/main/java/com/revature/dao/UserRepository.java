package com.revature.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dto.LoginDTO;
import com.revature.dto.RegisterDTO;
import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.models.UserType;
import com.revature.service.UserService;
import com.revature.util.SessionUtility;

public class UserRepository {
	
	private UserService userService;
	private Session session; 
	
	public UserRepository() {
		this.session = SessionUtility.getSessionFactory().openSession();
	}

	public User getUserByUsernameAndPassword(LoginDTO loginDTO) throws LoginException {
		try {
			User user = (User) session.createQuery("FROM User u WHERE u.username = :un and u.password = :pw")
					.setParameter("un", loginDTO.getUsername())
					.setParameter("pw", loginDTO.getPassword())
					.getSingleResult();	
			return user;
			
		} catch(NoResultException e) {
			return null; 			
		}		
	}

	public User register(RegisterDTO registerDTO) {
		try {
			Transaction tx = session.beginTransaction(); 
			User user = new User(
					registerDTO.getUsername(), 
					registerDTO.getPassword(),
					registerDTO.getfName(), 
					registerDTO.getlName(), 
					registerDTO.getEmail(), 
					session.get(UserType.class, 1)
					); 
			session.persist(user);
			tx.commit();
			
			return user;
			
		} catch(NoResultException e) {
			return null; 			
		}		
	}

}
