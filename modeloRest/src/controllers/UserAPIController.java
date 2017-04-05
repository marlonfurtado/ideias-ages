package br.com.ideiasages.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Util;



@Path("/api")
public class UserAPIController {

	private static String endpointUrl;
	UserBO userBO = new UserBO();

	@Context
	private HttpServletRequest request;
	private HttpServletResponse response;

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

		for (int x = 0; x <= 10; x++) {

			User user = new User();
			user.setName("User " + x);
			user.setEmail("user" + x + "@gmail.com");
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
	public String addUser(User user) {

		String response = user.toString();

		return response;
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	public void validaLogin(User userLogin) throws NegocioException, ServletException, IOException {
	
		User user = new User();
		user.setName(userLogin.getEmail());
		user.setEmail(userLogin.getPassword());
		
		if (userBO.validaUser(user)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			request.getSession().setAttribute("versao", Util.getVersion());
			request.getRequestDispatcher("lista.html").forward(request, response);;
		} else {
			request.setAttribute("msgAviso", "Usuário Inválido");
			request.getRequestDispatcher("erro.html").forward(request, response);
		}

	}
	
	
	/**
	 * 
	 * @param userLogin
	 * @return
	 */
	
	@POST
	@Path("/login1")
	@Consumes("application/json")
	public String userLogin1(User userLogin) {
		
		User user = new User();
		user.setName("Matheus Morcinek");
		user.setEmail("admin");
		user.setPassword("admin");
		user.setPhone(997033589);
		user.setActive(true);
		user.setRole(1);
		
		if (userLogin.getEmail().equals(user.getEmail()) && userLogin.getPassword().equals(user.getPassword())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			return "sucesso";
		} else {
			return "erro";
		}
		
	}
	
	@GET
	@Path("/ping")
	@Produces("application/json")
	public String ping() {
		String date = " testetetetete";
		return date;
	}
 
}
