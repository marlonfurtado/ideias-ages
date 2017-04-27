package br.com.ideiasages.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

// o path deve ser / pois deve ser definido no application context do tomcat � definido o modulo api como /api
// portanto o / aqui se refere � /api
@Path("/")
public class APIController {
	UserBO userBO = new UserBO();

	@Context
	private HttpServletRequest request;
	private HttpSession session;
	public APIController() {}

	@GET
	@Path("/")
	@Produces("application/json")
	public String index() throws NegocioException {
		return "works";
	}

	@GET
	@Path("/users")
	@Produces("application/json")
	public ArrayList<User> getActiveUsers() throws NegocioException {
		ArrayList<User> users = new ArrayList<User>();
		users = userBO.getActiveUsers();

		return users;
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public String login(User userLogin) throws NegocioException, ServletException, IOException {
		User user = new User();
		user.setPassword(userLogin.getPassword());
		user.setCpf(userLogin.getCpf());

		if (userBO.validate(user) != null) {
			session = null;
			session = request.getSession(true);

			session.setAttribute("user", user);
			session.setAttribute("version", Util.getVersion());
			session.setAttribute("msg", "Login OK");

			return "{\"success\": true}";
		}

		return "{\"success\": false}";
	}
	
	@POST
	@Path("/cadastroAnalista")
	@Consumes("application/json")
	@Produces("application/json")
	public String cadastroAnalista(User userLogin) throws NegocioException, ServletException, IOException {
		User user = new User();
		user.setPassword(userLogin.getPassword());
		user.setCpf(userLogin.getCpf());
		user.setEmail(userLogin.getEmail());
		user.setName(userLogin.getName());
		user.setPhone(userLogin.getPhone());
		user.setRole("Analista");
		user.setActive(true);

		userBO.saveUser(user);
		
		return "{\"success\": false}";
	}
}
