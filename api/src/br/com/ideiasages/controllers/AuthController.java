package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Classe controladora das requisições referentes a autenticação dos usuários.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 06/06/2017
 * 
 **/
@Path("auth")
public class AuthController {
	
	Logger logger = Logger.getLogger("controller.AuthController");
	
    private UserBO userBO = new UserBO();

    @Context
    private HttpServletRequest request;
    private HttpSession session = null;

	/**
	 * Faz as validações necessárias para efetuar o login do usuário.
	 * 
	 * @param userLogin Objeto {@link br.com.ideiasages.model.User} com os dados para efetuar o login.
	 * @return Objeto {@link br.com.ideiasages.dto.StandardResponseDTO} com a resposta do método.
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
            
            if(user.isActive()==false) {
            	response.setMessage(MensagemContantes.MSG_ERR_USUARIO_INATIVO.replace("?", user.getName()));
                response.setSuccess(false);
            	return response;
            }
         
            request.getSession().setAttribute("user", user);
            //store the user into the session
           /// session.setAttribute("user", user);
            
            logger.debug("User inserido na session: " + new Date() + " - " + user.toString() );
            logger.debug("Session LOGIN: " + new Date() + " - " + request.getSession().hashCode() );

            response.setSuccess(true);
            response.setMessage("Logado.");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }

	/**
	 * Desloga o usuário logado.
	 * 
	 * @return Objeto {@link br.com.ideiasages.dto.StandardResponseDTO} com a resposta do método.
	 **/
    @GET
    @Path("/logout")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO logoutUser() {
        request.getSession().invalidate();

        return new StandardResponseDTO(true, "Deslogado");
    }

	/**
	 * Verifica se existe um usuário logado no sistema.
	 * 
	 * @return Retorna o {@link br.com.ideiasages.model.User} logado.
	 **/
    @GET
    @Path("/me")
    @Produces("application/json; charset=UTF-8")
    public User getMe() {
    	
    	logger.debug("Session ME: " + new Date() + " - " + request.getSession().hashCode() );
    	
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
        	logger.debug("User inserido na session: " + new Date() + " - " + user.toString() );
            return user;
        }
        
        logger.debug("User nÃ£o existe na session");

        return new User();
    }
}
