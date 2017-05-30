package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.IdeaBO;
import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@Path("/ideas")
public class IdeaController {
    private IdeaBO ideaBO = new IdeaBO();
    private IdeaDAO ideaDAO = new IdeaDAO();

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO create(Idea newIdea) {
        StandardResponseDTO response = new StandardResponseDTO();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        try {
            Idea idea = ideaBO.validateFields(newIdea);

            newIdea.setUser(loggedUser);
            newIdea.setStatus(IdeaStatus.DRAFT);

            ideaDAO.addIdeia(idea);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_SAVED);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_NOT_SAVED);
        }

        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO update(Idea newIdea, @PathParam("id") int id) {
        StandardResponseDTO response = new StandardResponseDTO();


        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        try {
            Idea idea = ideaBO.validateFields(newIdea);
            idea.setId(id);

            ideaBO.isOwnedByUser(idea, loggedUser);

            ideaDAO.updateIdea(idea);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_SAVED);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_NOT_SAVED);
        }

        return response;
    }

    @PUT
    @Path("/{id}/sendToAnalysis")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO sendToAnalysis(Idea newIdea, @PathParam("id") int id) {
        StandardResponseDTO response = new StandardResponseDTO();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        try {
            Idea idea = ideaBO.validateFields(newIdea);
            idea.setId(id);

            ideaBO.isDraft(idea);
            ideaBO.isOwnedByUser(idea, loggedUser);

            newIdea.setUser(loggedUser);
            newIdea.setStatus(IdeaStatus.OPEN);

            ideaDAO.sendToAnalysis(idea);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_SAVED);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_NOT_SAVED);
        }

        return response;
    }
}
