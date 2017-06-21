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
 * Classe controladora das requisições referentes ao usuário.
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
	 * Consulta usuários através do seu papel (Role).
	 *
	 * @param role Papel do usuário no sistema.
	 * @return Lista de usuários encontrados.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 * @throws java.sql.SQLException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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

        //somente usuários que não são idealizadores podem listar algum tipo de usuário
        if (!loggedUser.getRole().equals("idealizer")) {
            role = role.toLowerCase();

            //somente admin pode filtrar, avaliadores só podem listar idealizadores
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
     * Ativa um usuário analyst ou idealizer.
     *
     * @param cpf cpf do Usuário ao qual o status será alterado.
     * @return Resposta do método.
     * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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
            response.setMessage("Usuário " + loggedUser.getName() + " sem autorização para alterar status.");
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
     * Desativa um usuário analyst ou idealizer.
     *
     * @param cpf cpf do Usuário ao qual o status será alterado.
     * @return Resposta do método.
     * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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
            response.setMessage("Usuário " + loggedUser.getName() + " sem autorização para alterar status.");
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
