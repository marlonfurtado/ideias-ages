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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Classe controladora das requisi��es referentes ao tipo de usu�rio 'Analista'.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 * 
 **/
@Path("accounts/analyst")
public class AnalystController {
	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();

	@Context
	private HttpServletRequest request;
	private HttpSession session;


	/**
	 * Realiza a cria��o de um novo usu�rio do tipo analista.
	 * 
	 * @param user Objeto usu�rio.{@link br.com.ideiasages.model.User}
	 * @return Objeto com a resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
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
		session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");

		try {
			userBO.isAdmin(loggedUser);
			user = userBO.validate(user);

			user.setActive(true);
			user.setRole("analyst");

			userDAO.addUser(user);

			response.setSuccess(true);
			response.setMessage(MensagemContantes.MSG_SUC_CADASTRO_USUARIO.replace("?", user.getName()));
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		return response;

	}

	/**
	 * Lista todos os analistas cadastrados no sistema.
	 * 
	 * @return Lista de analistas.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * @throws java.sql.SQLException Exce��o de opera��es realizadas no banco de dados.
	 * 
	 **/
	@GET
	@Path("/list")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<User> list() throws PersistenciaException, SQLException {		
		return userDAO.getAnalyst();
	}

	/**
	 * Realiza a edi��o de um Analista logado.
	 * 
	 * @param perfil Objeto com os dados para a edi��o de um usu�rio.{@link br.com.ideiasages.model.Perfil}
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * 
	 **/
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
			System.out.println(perfil.getPassword());
			System.out.println(perfil.getPasswordToValidate());
			if(perfil.getPassword() == null && !perfil.getPasswordToValidate().equals(""))
				response.setMessage(MensagemContantes.MSG_ERR_SENHA_NULO);
			else{
				try{
					userDAO.editUser(loggedUser, perfil);
					response.setSuccess(true);
					response.setMessage(MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", perfil.getName()));
				} catch(Exception e){
					response.setMessage(e.getMessage());
				}
			}
		}
		return response;
	}
}
