package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.dto.UserFormattedDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
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
	public List<UserFormattedDTO> list(@QueryParam("role") String role) throws PersistenciaException, SQLException, NegocioException {
		ArrayList<String> roles = new ArrayList<>(asList("analyst", "idealizer"));
		ArrayList<User> users = null;

		session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");

        //somente usu�rios que n�o s�o idealizadores podem listar algum tipo de usu�rio
        if (!loggedUser.getRole().equals("idealizer")) {
            role = role.toLowerCase();

            //somente admin pode filtrar, avaliadores s� podem listar idealizadores
            if (loggedUser.getRole().equals("administrator")) {
                if (role.equals("analyst")) {
                    roles.remove("idealizer");
                } else if (role.equals("idealizer")) {
                    roles.remove("analyst");
                }
            } else {
                roles.remove("analyst");
            }

			users = userDAO.getUsersByRoles(roles);
		}

		return UserFormattedDTO.getFromUser(users);
	}

    /**
     * Ativa um usu�rio analyst ou idealizer.
     *
     * @param cpf cpf do Usu�rio ao qual o status ser� alterado.
     * @return Resposta do m�todo.
     * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
     **/
    @PUT
    @Path("/{cpf}/enable")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO enable(@PathParam("cpf") String cpf) throws PersistenciaException {
        StandardResponseDTO response = new StandardResponseDTO();
        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        if (!loggedUser.getRole().equalsIgnoreCase("ADMINISTRATOR")) {
            response.setMessage("Usu�rio " + loggedUser.getName() + " sem autoriza��o para alterar status.");
            return response;
        }

        try {
            userDAO.getUserByCPF(cpf);
            userDAO.changeStatus(cpf, true);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", cpf));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }

    /**
     * Desativa um usu�rio analyst ou idealizer.
     *
     * @param cpf cpf do Usu�rio ao qual o status ser� alterado.
     * @return Resposta do m�todo.
     * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
     **/
    @PUT
    @Path("/{cpf}/disable")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO disable(@PathParam("cpf") String cpf) throws PersistenciaException {
        StandardResponseDTO response = new StandardResponseDTO();
        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        if (!loggedUser.getRole().equalsIgnoreCase("ADMINISTRATOR")) {
            response.setMessage("Usu�rio " + loggedUser.getName() + " sem autoriza��o para alterar status.");
            return response;
        }

        try {
            userDAO.getUserByCPF(cpf);
            userDAO.changeStatus(cpf, false);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", cpf));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
