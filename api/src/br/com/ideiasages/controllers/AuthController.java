package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

@Path("auth")
public class AuthController {
	
	Logger logger = Logger.getLogger("controller.AuthController");
	
    private UserBO userBO = new UserBO();
    private UserDAO userDAO = new UserDAO();

    @Context
    private HttpServletRequest request;
    private HttpSession session = null;

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

            userDAO.addUser(user);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_SUC_CADASTRO_USUARIO.replace("?", user.getName()));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @GET
    @Path("/logout")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO logoutUser() {
        request.getSession().invalidate();

        return new StandardResponseDTO(true, "Deslogado");
    }

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
        
        logger.debug("User não existe na session");

        return new User();
    }
}
