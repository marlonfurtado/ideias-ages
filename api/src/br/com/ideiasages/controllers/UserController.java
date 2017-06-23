package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.dto.UserFormattedDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.Perfil;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Constantes;
import br.com.ideiasages.util.MensagemContantes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
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

    /**
     * Usu�rio realiza a sua pr�pria edi��o.
     *
     * @param body Objeto com os dados para a edi��o de um usu�rio.{@link br.com.ideiasages.model.Perfil}
     * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
     * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
     * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
     * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
     *
     **/
    @PUT
    @Path("/{cpf}/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response edit(HashMap<String, String> body, @PathParam("cpf") String cpf) throws PersistenciaException, ValidationException, NegocioException {
        HashMap<String, Object> map = new HashMap<>();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        String actualPassword = userDAO.returnPassword(loggedUser);

        if (!cpf.equals(loggedUser.getCpf())) {
            map.put("success", false);
            map.put("message", MensagemContantes.MSG_ERR_EDICAO_USUARIO);

            return Response.ok().entity(map).build();
        }

        if(body.get("password") != null && !(actualPassword.equals(body.get("passwordToValidate")))) {
            map.put("success", false);
            map.put("message", MensagemContantes.MSG_ERR_SENHA_INVALIDA);
        } else {
            if(body.get("password") == null && !body.get("passwordToValidate").equals("")) {
                map.put("success", false);
                map.put("message", MensagemContantes.MSG_ERR_SENHA_NULO);
            } else {
                try{
                    User actual = userDAO.getUserByCPF(loggedUser.getCpf());

                    if (body.get("name") != null) {
                        actual.setName(body.get("name"));
                    }

                    if (body.get("email") != null) {
                        actual.setEmail(body.get("email"));
                    }

                    if (body.get("phone") != null) {
                        actual.setPhone(body.get("phone"));
                    }

                    if (body.get("password") != null) {
                        actual.setPassword(body.get("password"));
                    } else {
                        actual.setPassword(userDAO.returnPassword(loggedUser));
                    }

                    userBO.validateToUpdate(actual, loggedUser);

                    userDAO.editUser(actual);

                    User edited = userDAO.getUserByCPF(loggedUser.getCpf());

                    request.getSession().setAttribute("user", edited);

                    map.put("success", true);
                    map.put("message", MensagemContantes.MSG_SUC_EDICAO_USUARIO.replace("?", loggedUser.getName()));
                    map.put("user", edited);
                } catch (Exception e) {
                    map.put("success", false);
                    map.put("message", e.getMessage());
                }
            }
        }

        return Response.ok().entity(map).build();
    }

    /**
     * Realiza a o cadastro de um analyst por um admin
     *
     * @param body Objeto com os dados para a edi��o de um usu�rio.{@link br.com.ideiasages.model.Perfil}
     * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
     * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
     * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
     * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
     *
     **/
    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Response edit(HashMap<String, String> body) throws PersistenciaException, ValidationException, NegocioException {
        HashMap<String, Object> map = new HashMap<>();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        User newUser = new User();

        try {
            userBO.isAdmin(loggedUser);

            newUser.setCpf(body.get("cpf"));
            newUser.setName(body.get("name"));
            newUser.setEmail(body.get("email"));
            newUser.setPassword(body.get("password"));
            newUser.setPhone(body.get("phone"));
            newUser.setRole(Constantes.ANALYST_ROLE);
            newUser.setActive(true);

            userBO.validate(newUser);

            map.put("success", true);
            map.put("message", MensagemContantes.MSG_SUC_CADASTRO_USUARIO);
            map.put("user", userDAO.getUserByCPF(newUser.getCpf()));
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", e.getMessage());
        }

        return Response.ok().entity(map).build();
    }
}
