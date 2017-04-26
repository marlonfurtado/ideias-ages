package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Path("accounts/analyst")
public class AnalystController {
    private UserBO userBO = new UserBO();

    @Context
    private HttpServletRequest request;
    private HttpSession session = null;

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public StandardResponseDTO create(User user) {
        StandardResponseDTO response = new StandardResponseDTO();
        User loggedUser = (User) request.getSession().getAttribute("user");
        
        try {
        	userBO.isAdmin(loggedUser);
        	
        	UserBO userBO = new UserBO();
        	user = userBO.validate(user);

        	user.setActive(true);
        	user.setRole("analyst");
        
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
