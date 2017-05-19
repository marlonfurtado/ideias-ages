package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.Perfil;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("accounts/idealizer")
public class IdealizerController {
	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();

	@Context
	private HttpServletRequest request;
	private HttpSession session;

	@POST
	@Path("/register")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO create(User user) throws PersistenciaException, ValidationException {
		StandardResponseDTO response = new StandardResponseDTO();

		try {
			user = userBO.validate(user);

			user.setActive(true);
			user.setRole("idealizer");

			userDAO.addUser(user);

			response.setSuccess(true);
			response.setMessage(MensagemContantes.MSG_SUC_CADASTRO_USUARIO.replace("?", user.getName()));
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		return response;
	}

	@PUT
	@Path("/edit")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO edit(Perfil perfil) throws PersistenciaException, ValidationException, NegocioException {
		StandardResponseDTO response = new StandardResponseDTO();

		session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");
		String actualPassword = userDAO.returnPassword(loggedUser);
		if(perfil.getPassword() != null && !(actualPassword.equals(perfil.getPasswordToValidate()))){
			response.setMessage(MensagemContantes.MSG_ERR_SENHA_INVALIDA);
		}
		else{
			try{
				if(perfil.getPassword() == null)
					userDAO.editUser(loggedUser.getCpf(), perfil);
				else
					userDAO.editUserWithPassword(loggedUser.getCpf(), perfil);

				response.setSuccess(true);
				response.setMessage(MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", perfil.getName()));
			} catch(Exception e){
				response.setMessage(e.getMessage());
			}
		}
		return response;
	}

	@GET
	@Path("/list")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<User> list() throws PersistenciaException, SQLException {
		return userDAO.getIdealizer();
	}
}
