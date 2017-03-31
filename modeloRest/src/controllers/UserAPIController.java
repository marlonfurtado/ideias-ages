package controllers;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import models.User;

@Path("/api")
public class UserAPIController {

	@GET
	@Path("/user")
	@Produces("application/json")
	public User getCliente() {
		User user = new User();
		user.setName("Matheus Morcinek");
		user.setEmail("matheusmorcinek@gmail.com");
		user.setPassword("123456");
		user.setPhone(997033589);
		user.setActive(true);
		user.setRole(1);
		return user;
	}
	
	
	@GET
	@Path("/users")
	@Produces("application/json")
	public ArrayList<User> getClientes() {
		
		ArrayList<User> list = new ArrayList<>();
		
		for(int x = 0; x <= 10; x++){
			
			User user = new User();
			user.setName("User "+ x);
			user.setEmail("user"+x+"@gmail.com");
			user.setPassword(x + "0006");
			user.setPhone(1234);
			user.setActive(true);
			user.setRole(1);
			
			list.add(user);
		}				
		return list;		
	}


	@POST
	@Path("/adduser")
	@Consumes("application/json")
	public String addUser( User user ) {

		String response = user.toString();

		return response;
	}
	
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	public String userLogin(User userLogin) {

		User user = new User();
		user.setName("Matheus Morcinek");
		user.setEmail("matheusmorcinek@gmail.com");
		user.setPassword("123456");
		user.setPhone(997033589);
		user.setActive(true);
		user.setRole(1);
	
		
		if(userLogin.getEmail().equals(user.getEmail()) && userLogin.getPassword().equals(user.getPassword()))
			return "sucesso";
		else
			return "erro";
		
	}
	
}
