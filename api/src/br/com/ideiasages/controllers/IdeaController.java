package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.IdeaBO;
import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.HashMap;

@Path("/ideas")
public class IdeaController {
    private IdeaBO ideaBO = new IdeaBO();
    private IdeaDAO ideaDAO = new IdeaDAO();
    private UserBO userBO = new UserBO();

    Logger logger = null;

    public IdeaController() {
        this.logger = Logger.getLogger("controller.IdeaController");
    }

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO create(HashMap<String, String> body) {
        StandardResponseDTO response = new StandardResponseDTO();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        Idea idea = new Idea();

        try {
            idea.setTitle(body.get("title"));
            idea.setTags(body.get("tags"));
            idea.setGoal(body.get("goal"));
            idea.setDescription(body.get("description"));
            idea.setStatus(IdeaStatus.valueOf(body.get("status").toUpperCase()));

            ideaBO.validateFields(idea);
            ideaBO.validateStatusByUser(idea, loggedUser);

            idea.setUser(loggedUser);

            ideaDAO.addIdeia(idea);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_SAVED);
        } catch (Exception e) {
            logger.error(e);
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_NOT_SAVED);
        }

        return response;
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO update(
            HashMap<String, String> body,
            @PathParam("id") int id) {
        StandardResponseDTO response = new StandardResponseDTO();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        Idea idea = new Idea();

        try {
            idea = ideaDAO.getIdea(id);

            ideaBO.isOwnedByUser(idea, loggedUser);
            ideaBO.isDraft(idea);

            idea.setTitle(body.get("title"));
            idea.setTags(body.get("tags"));
            idea.setGoal(body.get("goal"));
            idea.setDescription(body.get("description"));
            idea.setStatus(IdeaStatus.valueOf(body.get("status").toUpperCase()));

            ideaBO.validateFields(idea);
            ideaBO.validateStatusByUser(idea, loggedUser);

            ideaDAO.updateIdea(idea);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_SAVED);
        } catch (Exception e) {
            logger.error(e);
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_NOT_SAVED);
        }

        return response;
    }

    @PUT
    @Path("/{id}/changeStatus")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO changeStatus(
            HashMap<String, String> body,
            @PathParam("id") int id) {
        StandardResponseDTO response = new StandardResponseDTO();

        session = request.getSession();
        User loggedUser = (User) session.getAttribute("user");

        Idea idea;

        try {
            String status = body.get("status");
            ideaBO.validateStatus(status);

            idea = ideaDAO.getIdea(id);
            ideaBO.isPossibleChangeStatus(idea, status);

            idea.setStatus(IdeaStatus.valueOf(body.get("status").toUpperCase()));
            ideaBO.validateStatusByUser(idea, loggedUser);

            ideaDAO.updateStatus(idea);

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_SAVED);
        } catch (Exception e) {
            logger.error(e);
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_NOT_SAVED);
        }

        return response;
    }

    @GET
    @Path("/{id}/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public Idea getIdea(@PathParam("id") int id) {
        Idea bag = null;

        User loggedUser = (User) request.getSession().getAttribute("user");

        try {
            //get the idea from DB
            logger.debug("Going to retrieve idea " + id);
            bag = ideaDAO.getIdea(id);

            if (bag != null) {
                //check if the user has access
                logger.debug("Checking read access");
                ideaBO.checkReadAccess(bag, loggedUser);
            }
        }
        catch (Exception e) {
            logger.error(e);
        }

        return bag;
    }
}
