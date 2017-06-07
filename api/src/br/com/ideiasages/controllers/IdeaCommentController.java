package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.IdeaCommentBO;
import br.com.ideiasages.dao.IdeaCommentDAO;
import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.util.MensagemContantes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.HashMap;
import java.util.List;

@Path("/ideas/{ideaId}/comments")
public class IdeaCommentController {
    private IdeaCommentBO boLayer = new IdeaCommentBO();
    private IdeaCommentDAO daoLayer = new IdeaCommentDAO();
    private IdeaDAO ideaDAO = new IdeaDAO();

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

    @POST
    @Path("/")
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    public StandardResponseDTO add(IdeaComment model, @PathParam("ideaId") int ideaId) {
        StandardResponseDTO response = new StandardResponseDTO();
        IdeaComment createdEntity = null;
        Idea ideaSent = null;

        try {
            //get the idea
            ideaSent = ideaDAO.getIdea(ideaId);

            //validate the mandatory fields
            logger.debug("Going to validate the fields");
            boLayer.validateFields(model);

            //add into
            logger.debug("Going to create the comment based on the value sent");
            createdEntity = daoLayer.add(model);

            //Check if worked
            if (createdEntity != null) {
                logger.debug("Entity has been created. Id = " + createdEntity.getId());

                //going to append this comment into the idea
                ideaDAO.addComment(ideaSent, createdEntity);

                logger.debug("Going to create the relationship between idea and comment.");
            }

            response.setSuccess(true);
            response.setMessage(MensagemContantes.MSG_IDEA_COMMENT_SAVED);
        }
        catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(MensagemContantes.MSG_IDEA_COMMENT_NOT_SAVED);
        }

        return response;
    }
}
