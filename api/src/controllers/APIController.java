package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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

@Path("/")
public class APIController {

	// private static String endpointUrl;
	UserBO userBO = new UserBO();


	@Context
	private HttpServletRequest request;
	private HttpSession session;
	public APIController() {
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public String index() throws NegocioException {
		return "works";
	}

	@GET
	@Path("/user")
	@Produces("application/json")
	public User getCliente() throws NegocioException {
		User user = new User();
		user = (User) request.getSession().getAttribute("user");
		return userBO.buscaUserId(user.getIdUser());
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
	@GET
	@Path("/usuarios")
	@Produces("application/json")
	public ArrayList<User> getUsuarios() throws NegocioException {

		ArrayList<User> list = new ArrayList<>();
		list = userBO.listarUser();

		return list;
	}

	@POST
	@Path("/adduser")
	@Consumes("application/json")
	public String addUser(User user) {
		System.out.println(user);
		try {
			userBO.cadastraUser(user);
			return "sucesso";
		} catch (NegocioException | SQLException | ParseException e) {
			e.printStackTrace();
		}
		return "erro";
	}

	@POST
	@Path("/login")
	@Consumes("application/json")
	public String validaLogin(User userLogin) throws NegocioException, ServletException, IOException {

		User user = new User();
		user.setPassword(userLogin.getPassword());
		user.setEmail(userLogin.getEmail());

		if (userBO.validaUser(user)) {

			session = null;
			session = request.getSession(true);

			User userSession = new User();
			userSession = userBO.buscaUserEmail(user.getEmail());

			session.setAttribute("user", userSession);
			session.setAttribute("versao", Util.getVersion());
			session.setAttribute("msgAviso", "Login OK");

			System.out.println(user);
			return "sucesso";
		} else {
			request.setAttribute("msgAviso", "Usu?rio In?lido");
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
	@GET
	@Path("/kill")
	@Produces("application/json")
	public String killSession() {
		String date = " Kill " + request.getAttribute("user");
		return date;
	}

}
