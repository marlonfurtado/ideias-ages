package br.com.ideiasages.controllers;

import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.Date;

@Path("/ideas")
public class IdeaController {
    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO create(Idea newIdea) {
        StandardResponseDTO response = new StandardResponseDTO();

        try {
            response.setSuccess(false);
            response.setMessage(newIdea.toString());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(newIdea.toString());
        }

        return response;
    }
}
