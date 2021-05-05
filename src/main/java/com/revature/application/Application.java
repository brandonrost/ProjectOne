package com.revature.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.Controller;
import com.revature.controllers.ExceptionController;
import com.revature.controllers.ReimbController;
import com.revature.controllers.StaticFileController;
import com.revature.controllers.UserController;
import com.revature.dbsetup.HibernateDBSetup;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	private static HibernateDBSetup setup = new HibernateDBSetup();

	public static void main(String[] args) {
		
		//setup.setUp(); //This can be used to reset the database and set up a new one
		
		ObjectMapper mapper = JsonMapper.builder()
				   .addModule(new JavaTimeModule())
				   .build();
		JavalinJackson.configure(mapper); 
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("static");
			config.enableCorsForAllOrigins(); 
		});
		
		
		mapControllers(app, new ExceptionController(), new UserController(), new StaticFileController(), new ReimbController());
		
		app.start(7000);
		
		
		

	}
	
	private static void mapControllers(Javalin app, Controller... controllers) {
		for (Controller c : controllers) {
			c.mapEndpoints(app);
		}
	}
}
