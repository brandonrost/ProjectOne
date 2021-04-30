package com.revature.controllers;

import com.revature.dto.LoginDTO;
import com.revature.dto.MessageDTO;
import com.revature.dto.RegisterDTO;
import com.revature.models.User;
import com.revature.service.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller {

	private UserService userService;

	public UserController() {
		this.userService = new UserService(); 
	}

	private Handler loginHandler = (ctx) -> {
		LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
		
		User user = userService.login(loginDTO);
		
		ctx.sessionAttribute("currentlyLoggedInUser", user);
		ctx.json(user);
	};
	
	private Handler currentUserHandler = (ctx) -> {
		User user = (User) ctx.sessionAttribute("currentlyLoggedInUser");
		
		if (user == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not currently logged in!");
			ctx.json(messageDTO);
			ctx.status(400);			
		} else {
			ctx.json(user);
		}		
	};
	
	private Handler logoutHandler = (ctx) -> {
		ctx.req.getSession().invalidate();	
		MessageDTO messageDTO = new MessageDTO(); 
		
		if(ctx.status() == 200) {
			messageDTO.setMessage("User logged out successfully!");
		}else {
			messageDTO.setMessage("Could not log out current User.");
		}
		
		ctx.json(messageDTO); 
	};
	
	private Handler registerHandler = (ctx) -> {
		RegisterDTO registerDTO = ctx.bodyAsClass(RegisterDTO.class);
		User user = userService.register(registerDTO); 	
		MessageDTO messageDTO = new MessageDTO(); 
		
		if(ctx.status() == 200) {
			ctx.json(user); 
			
		}else {
			messageDTO.setMessage("Could not register new user. User already exists.");
		}
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/login", loginHandler);
		app.get("/current-user", currentUserHandler);
		app.post("/logout", logoutHandler);
		app.post("/register", registerHandler);
	}

}
