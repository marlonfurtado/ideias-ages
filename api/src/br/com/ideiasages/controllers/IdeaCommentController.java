package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.IdeaCommentBO;
import br.com.ideiasages.dao.IdeaCommentDAO;
import br.com.ideiasages.model.IdeaComment;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("/ideas/{ideaId}/comments")
public class IdeaCommentController {
    private IdeaCommentBO boLayer = new IdeaCommentBO();
    private IdeaCommentDAO daoLayer = new IdeaCommentDAO();

    Logger logger = Logger.getLogger("controller.IdeaCommentController");

    @Context
    private HttpServletRequest request;
    private HttpSession session;

    @GET
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public List<IdeaComment> getAllByIdea(@PathParam("ideaId") long ideaId) {
        List<IdeaComment> model = null;

        logger.debug("Going to retrieve all comments from the idea " + ideaId);

        try {
            model = daoLayer.getAllByIdea(ideaId);
        }
        catch (Exception e) {
            logger.error(e);
        }

        return model;
    }
}
