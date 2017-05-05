package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.model.User;

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

    @Context
    private HttpServletRequest request;
    private HttpSession session = null;

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public StandardResponseDTO login(User userLogin) {
        User user;
        StandardResponseDTO response = new StandardResponseDTO();

        try {
            user = userBO.userExists(userLogin);

            
         
            request.getSession().setAttribute("user", user);
            //store the user into the session
           /// session.setAttribute("user", user);
            
            logger.debug("User inserido na session: " + new Date() + " - " + user.toString() );
            logger.debug("Session LOGIN: " + new Date() + " - " + session.hashCode() );

            response.setSuccess(true);
            response.setMessage("Logado.");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @GET
    @Path("/logout")
    @Produces("application/json")
    public StandardResponseDTO logoutUser() {
        request.getSession().invalidate();

        return new StandardResponseDTO(true, "Deslogado");
    }

    @GET
    @Path("/me")
    @Produces("application/json")
    public User getMe() {
    	
    	logger.debug("Session ME: " + new Date() + " - " + request.getSession().hashCode() );
    	
        User user = (User) request.getSession().getAttribute("user");

        logger.debug("User inserido na session: " + new Date() + " - " + user.toString() );
        if (user != null)
            return user;

        return new User();
    }
    
    @POST
    @Path("/test")
    @Consumes("application/json")
    @Produces("application/json")
    public String getTest() {
    	session.setAttribute("parametroTeste", "string test");
    	logger.debug("Send TEST: " );
    	
    	return "---";
    	
    }
    @GET
    @Path("/returnTest")
    @Produces("application/json")
    public String getReturnTest() {
    	String s = (String) request.getSession().getAttribute("parametroTeste");
    	logger.debug("Return TEST: " + s );
    	
    	return s;
    }
}
