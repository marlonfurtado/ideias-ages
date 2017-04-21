package controllers;

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

// o path deve ser / pois deve ser definido no application context do tomcat é definido o modulo api como /api
// portanto o / aqui se refere à /api
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
	public String login(User userLogin) throws NegocioException, ServletException, IOException {
		User user = new User();
		user.setPassword(userLogin.getPassword());
		user.setCpf(userLogin.getCpf());

		if (userBO.validate(user) != null) {
			session = null;
			session = request.getSession(true);

			session.setAttribute("user", user);
			session.setAttribute("versao", Util.getVersion());
			session.setAttribute("msgAviso", "Login OK");

			System.out.println(user);

			return "sucesso";
		}

		request.setAttribute("msgAviso", "Usu?rio In?lido");
		return "erro";
	}
}
