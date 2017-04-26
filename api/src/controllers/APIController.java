package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import br.com.ideiasages.dto.StandardResponseDTO;
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
	private HttpSession session = null;
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

	@GET
	@Path("/me")
	@Produces("application/json")
	public User getMe() {
		Object ret = request.getSession().getAttribute("user");

		if (ret != null)
			return (User) ret;

		return new User();
	}

	@GET
	@Path("/logout")
	@Produces("application/json")
	public StandardResponseDTO logoutUser() {
		request.getSession().invalidate();

		return new StandardResponseDTO(true, "Deslogado");
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public StandardResponseDTO login(User userLogin) throws NegocioException, ServletException, IOException {
		User user = null;
		StandardResponseDTO response = new StandardResponseDTO();

		try {
			user = userBO.validate(userLogin);

			session = request.getSession(true);

			//store the user into the session
			session.setAttribute("user", user);

			response.setSuccess(true);
			response.setMessage("Logado.");
		}
		catch (NegocioException ne) {
			response.setSuccess(false);
			response.setMessage(ne.getMessage());
		}
		catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}

		return response;
	}
}
