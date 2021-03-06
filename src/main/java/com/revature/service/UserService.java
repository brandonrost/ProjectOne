package com.revature.service;

import com.revature.dao.UserRepository;
import com.revature.dto.LoginDTO;
import com.revature.dto.RegisterDTO;
import com.revature.exceptions.BadParameterException;
import com.revature.exceptions.LoginException;
import com.revature.models.User;
import com.revature.util.PasswordUtil;

public class UserService {
	
	private UserRepository userRepository; 

	public UserService() {
		this.userRepository = new UserRepository(); 
	}
	
	//for mockito
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User login(LoginDTO loginDTO) throws BadParameterException, LoginException {
		if (loginDTO.getUsername().trim().equals("") || loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Cannot have blank username and/or password");
		}
		
		String securePW = PasswordUtil.generateSecurePassword(loginDTO.getPassword(), "EqdmPh53c9x33EygXpTpcoJvc4VXLK"); 
		loginDTO.setPassword(securePW); 
		
		User user = userRepository.getUserByUsernameAndPassword(loginDTO);
		
		if (user == null) {
			throw new LoginException("User was not able to login in with the supplied username and password");
		}
		
		return user;
	}

	public User register(RegisterDTO registerDTO) throws BadParameterException {
		if(registerDTO.getfName() == "" || registerDTO.getlName() =="") {
			throw new BadParameterException("First Name and Last Name can not be null.");
		}
		if(registerDTO.getUsername() == "" || registerDTO.getPassword() =="") {
			throw new BadParameterException("Cannot have blank username and/or password");
		}
		if(registerDTO.getEmail() == "") {
			throw new BadParameterException("Email must be valid and not null. User provided " + registerDTO.getEmail() +".");
		}
		
		String securePW = PasswordUtil.generateSecurePassword(registerDTO.getPassword(),"EqdmPh53c9x33EygXpTpcoJvc4VXLK"); 
		registerDTO.setPassword(securePW); 
		User user = userRepository.register(registerDTO); 
		
		return user; 
	}

	public User registerFM(RegisterDTO registerDTO) throws BadParameterException {
		if(registerDTO.getfName() == "" || registerDTO.getlName() =="") {
			throw new BadParameterException("First Name and Last Name can not be null.");
		}
		if(registerDTO.getUsername() == "" || registerDTO.getPassword() =="") {
			throw new BadParameterException("Cannot have blank username and/or password");
		}
		if(registerDTO.getEmail() == "") {
			throw new BadParameterException("Email must be valid and not null. User provided " + registerDTO.getEmail() +".");
		}
		
		String securePW = PasswordUtil.generateSecurePassword(registerDTO.getPassword(),"EqdmPh53c9x33EygXpTpcoJvc4VXLK"); 
		registerDTO.setPassword(securePW); 
		User user = userRepository.registerFM(registerDTO); 
		
		return user;
	}	

}
