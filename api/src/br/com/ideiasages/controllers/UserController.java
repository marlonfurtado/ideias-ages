package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.asList;
/**
 * Classe controladora das requisi��es referentes ao usu�rio.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 **/
@Path("users")
public class UserController {
	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();

	@Context
	private HttpServletRequest request;
	private HttpSession session;


	/**
	 * Consulta usu�rios atrav�s do seu papel (Role).
	 * 
	 * @param role Papel do usu�rio no sistema.
	 * @return Lista de usu�rios encontrados.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 * @throws java.sql.SQLException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * 
	 **/
	@GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<User> list(@QueryParam("role") String role) throws PersistenciaException, SQLException, NegocioException {
		ArrayList<String> roles = new ArrayList<>(asList("analyst", "idealizer"));
		ArrayList<User> users = new ArrayList<>();

		session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");

		//somente usu�rios que n�o s�o idealizadores podem listar algum tipo de usu�rio
		if (!userBO.isIdealizer(loggedUser)) {
			role = role.toLowerCase();

			//somente admin pode filtrar, avaliadores s� podem listar idealizadores
			if (userBO.isAdmin(loggedUser)) {
				if (role.equals("analyst")) {
					roles.remove("idealizer");
				} else if (role.equals("idealizer")) {
					roles.remove("analyst");
				}
			} else {
				roles.remove("analyst");
			}

			userDAO.getUsersByRoles(roles);
		}

		return users;
	}
}
