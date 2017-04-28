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
import java.io.IOException;

@Path("auth")
public class AuthController {
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

            session = request.getSession(true);

            //store the user into the session
            session.setAttribute("user", user);

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
        Object ret = request.getSession().getAttribute("user");

        if (ret != null)
            return (User) ret;

        return new User();
    }
}
