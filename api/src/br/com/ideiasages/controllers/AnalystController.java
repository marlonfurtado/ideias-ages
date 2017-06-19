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
 * Classe controladora das requisições referentes ao tipo de usuário 'Analista'.
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
	 * Realiza a criação de um novo usuário do tipo analista.
	 * 
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Objeto com a resposta do método.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * @throws java.sql.SQLException Exceção de operações realizadas no banco de dados.
	 * 
	 **/
	@GET
	@Path("/list")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<User> list() throws PersistenciaException, SQLException {		
		return userDAO.getAnalyst();
	}

	/**
	 * Realiza a edição de um Analista logado.
	 * 
	 * @param perfil Objeto com os dados para a edição de um usuário.{@link br.com.ideiasages.model.Perfil}
	 * @return Resposta do método.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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
