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

	@GET
	@Path("/list")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<User> list() throws PersistenciaException, SQLException {
		return userDAO.getIdealizer();
	}

	@PUT
	@Path("/status")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO changeStatus(User user) throws PersistenciaException {
		StandardResponseDTO response = new StandardResponseDTO();
		session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");
		if (loggedUser.getRole().equalsIgnoreCase("ANALYST")) {
			response.setMessage("Usuário " + loggedUser.getName() + " sem autorização para alterar status.");
			return response;

		}
		String cpf = user.getCpf();
		boolean status = user.isActive();

		try {
			userDAO.changeStatus(cpf, status);

			response.setSuccess(true);
			response.setMessage(MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", cpf));
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		return response;
	}

}
