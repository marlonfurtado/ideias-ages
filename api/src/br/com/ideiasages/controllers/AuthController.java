package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.dto.UserFormattedDTO;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.EncryptUtil;
import br.com.ideiasages.util.MensagemContantes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import br.com.ideiasages.bo.PasswordChangeBO;
import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import java.util.Date;

/**
 * Classe controladora das requisiï¿½ï¿½es referentes a autenticaï¿½ï¿½o dos usuï¿½rios.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 *
 **/
@Path("auth")
public class AuthController {
	Logger logger = Logger.getLogger("controller.AuthController");

	private UserBO userBO = new UserBO();
	private UserDAO userDAO = new UserDAO();
    private PasswordChangeBO passwordChangeBO = new PasswordChangeBO();
    private EncryptUtil encryptUtil = new EncryptUtil();

	@Context
	private HttpServletRequest request;
	private HttpSession session = null;

	/**
	 * Faz as validaï¿½ï¿½es necessï¿½rias para efetuar o login do usuï¿½rio.
	 *
	 * @param userLogin Objeto usuï¿½rio com os dados para efetuar o login.{@link br.com.ideiasages.model.User}
	 * @return Objeto com a resposta do mï¿½todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 *
	 **/
	@POST
	@Path("/login")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO login(User userLogin) {
		User user;
		StandardResponseDTO response = new StandardResponseDTO();

		try {
			user = userBO.userExists(userLogin);

			if (!user.isActive()) {
				response.setMessage(MensagemContantes.MSG_ERR_USUARIO_INATIVO.replace("?", user.getName()));
				response.setSuccess(false);
				return response;
			}

			request.getSession().setAttribute("user", user);
			//store the user into the session
			/// session.setAttribute("user", user);

			logger.debug("User inserido na session: " + new Date() + " - " + user.toString());
			logger.debug("Session LOGIN: " + new Date() + " - " + request.getSession().hashCode());

			response.setSuccess(true);
			response.setMessage("Logado.");
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * @deprecated Mï¿½todo sem utilidade. Serï¿½ removido na refatoraï¿½ï¿½o de cï¿½digo.
	 * @param user Objeto user {@link br.com.ideiasages.model.User}.
	 * @return Objeto com a resposta do mï¿½todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 * @throws br.com.ideiasages.exception.ValidationException Exceï¿½ï¿½o de validaï¿½ï¿½o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceï¿½ï¿½o de operaï¿½ï¿½es realizadas
	 **/
	@POST
	@Path("/singup")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO create(User user) throws PersistenciaException, ValidationException {
		StandardResponseDTO response = new StandardResponseDTO();

		try {
			user = userBO.validate(user);

			user.setActive(true);
			user.setRole("idealizer");

			String encryptedPassword = encryptUtil.encrypt2(user.getPassword());
			user.setPassword(encryptedPassword);
			
			userDAO.addUser(user);

			response.setSuccess(true);
			response.setMessage(MensagemContantes.MSG_SUC_CADASTRO_USUARIO.replace("?", user.getName()));
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		return response;
	}

	/**
	 * Desloga o usuï¿½rio logado.
	 *
	 * @return Objeto com a resposta do mï¿½todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 **/
	@GET
	@Path("/logout")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO logoutUser() {
		request.getSession().invalidate();

		return new StandardResponseDTO(true, "Deslogado");
	}

	/**
	 * Verifica se existe um usuï¿½rio logado no sistema.
	 *
	 * @return Retorna o usuï¿½rio logado.{@link br.com.ideiasages.model.User}
	 **/
	@GET
	@Path("/me")
	@Produces("application/json; charset=UTF-8")
	public UserFormattedDTO getMe() {

		logger.debug("Session ME: " + new Date() + " - " + request.getSession().hashCode());

		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			logger.debug("User inserido na session: " + new Date() + " - " + user.toString());
			return UserFormattedDTO.getFromUser(user);
		}

		logger.debug("User nÃ£o existe na session");

        return null;
    }

    @POST
    @Path("/recoverPasswordRequest")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO recoverPasswordRequest(User userLogin) {
    	
        User user;
        StandardResponseDTO response = new StandardResponseDTO();

        try {
            user = userBO.getUserByCpf(userLogin);
            
            if(user.isActive()==false) {
            	response.setMessage(MensagemContantes.MSG_ERR_USUARIO_INATIVO.replace("?", user.getName()));
                response.setSuccess(false);
            	return response;
            }
            
            StringBuffer reqURL = request.getRequestURL();
            String reqURI = request.getRequestURI();
            String baseURL =  request.getRequestURL().substring(0, reqURL.length() - reqURI.length()) + "/";
            
            passwordChangeBO.createPasswordChangeRequest(user, baseURL);
            
            response.setSuccess(true);
            response.setMessage("Email de requisição de troca de senha enviado com sucesso para o email: " + user.getEmail());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
