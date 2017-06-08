package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Classe controladora das requisi��es referentes ao tipo de usu�rio 'Idealizador'.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 06/06/2017
 * 
 **/
@Path("accounts/idealizer")
public class IdealizerController {
	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();

	@Context
	private HttpServletRequest request;
	private HttpSession session;

	/**
	 * Realiza a cria��o de um novo usu�rio do tipo idealizador.
	 * 
	 * @param user Objeto usu�rio com os dados para a cria��o.{@link br.com.ideiasages.model.User}
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * 
	 **/
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

	/**
	 * Lista todos os idealizadores cadastrados no sistema.
	 * 
	 * @return Lista de analistas.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws java.sql.SQLException Exce��o de opera��es realizadas no banco de dados.
	 * 
	 **/
	@GET
	@Path("/list")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<User> list() throws PersistenciaException, SQLException {
		return userDAO.getIdealizer();
	}

	/**
	 * Altera o status do Idealizador.
	 * 
	 * @param user Usu�rio ao qual o status ser� alterado.{@link br.com.ideiasages.model.User}
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 **/
	@PUT
	@Path("/status")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO changeStatus(User user) throws PersistenciaException {
		StandardResponseDTO response = new StandardResponseDTO();
		session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");
		if (loggedUser.getRole().equalsIgnoreCase("ANALYST")) {
			response.setMessage("Usu�rio " + loggedUser.getName() + " sem autoriza��o para alterar status.");
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
