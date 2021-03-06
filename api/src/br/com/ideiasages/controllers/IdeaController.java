package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.IdeaBO;
import br.com.ideiasages.bo.UserBO;
import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Constantes;
import br.com.ideiasages.util.MensagemContantes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Classe controladora das requisi��es referentes a Id�ia.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 *
 **/
@Path("/ideas")
public class IdeaController {

	private IdeaBO ideaBO = new IdeaBO();
	private IdeaDAO ideaDAO = new IdeaDAO();
	private UserBO userBO = new UserBO();

	public IdeaController() {
		this.logger = Logger.getLogger("controller.IdeaController");
	}

	private Logger logger = null;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	@Context
	private HttpServletRequest request;
	private HttpSession session;

	/**
	 * Realiza a cria��o de uma nova id�ia.{@link br.com.ideiasages.model.Idea}
	 *
	 * @param body  Corpo que inclui todos os campos referente � id�ia.
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 **/
	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response create(HashMap<String, String> body) {
		HashMap<String, Object> map = new HashMap<>();

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

			int id = ideaDAO.addIdeia(idea);

			map.put("success", true);
			map.put("message", MensagemContantes.MSG_IDEA_SAVED);
			map.put("idea", ideaDAO.getIdea(id));
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", MensagemContantes.MSG_IDEA_NOT_SAVED);
		}

		return Response.ok().entity(map).build();
	}

	/**
	 * Realiza a altera��o de uma id�ia.{@link br.com.ideiasages.model.Idea}
	 *
	 * @param body Corpo que inclui todos os campos referente � id�ia.
	 * @param id ID da id�ia, informado pela URL.
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 **/
	@PUT
	@Path("/{id}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response update(
			HashMap<String, String> body,
			@PathParam("id") int id) {
		HashMap<String, Object> map = new HashMap<>();

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

			map.put("success", true);
			map.put("message", MensagemContantes.MSG_IDEA_SAVED);
			map.put("idea", ideaDAO.getIdea(id));
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", MensagemContantes.MSG_IDEA_NOT_SAVED);
		}

		return Response.ok().entity(map).build();
	}

	/**
	 * Invoca as classes de valida��es e faz a troca do status.
	 *
	 * @param body Corpo que inclui todos os campos referente � id�ia.
	 * @param id ID da id�ia.
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 **/
	@PUT
	@Path("/{id}/changeStatus")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response changeStatus(
			HashMap<String, String> body,
			@PathParam("id") int id) {
		HashMap<String, Object> map = new HashMap<>();

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

			if (idea.getStatus().equals(IdeaStatus.UNDER_ANALYSIS)) {
				ideaDAO.linkIdeaWithAnalyst(idea, loggedUser);
			}

			map.put("success", true);
			map.put("message", MensagemContantes.MSG_IDEA_SAVED);
			map.put("idea", ideaDAO.getIdea(id));
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", MensagemContantes.MSG_IDEA_NOT_SAVED);
		}

		return Response.ok().entity(map).build();
    }

    @GET
	@Path("/")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<Idea> list() throws PersistenciaException, SQLException, ClassNotFoundException, NegocioException {
    	session = request.getSession();
    	User loggedUser = (User) session.getAttribute("user");

		try {

			if(loggedUser.getRole().equals(Constantes.IDEALIZER_ROLE))
				return ideaDAO.getIdeas(loggedUser);
			else if(loggedUser.getRole().equals(Constantes.ADMINISTRATOR_ROLE))
				return ideaDAO.getAllIdeas();
			return ideaDAO.getIdeas();
		}
		catch (Exception e) {
			this.getLogger().error(e);
			//logger.error(e);
		}

		return null;
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
        	
        	this.getLogger().debug("Going to retrieve idea " + id);
            bag = ideaDAO.getIdea(id);

            if (bag != null) {
                //check if the user has access
                logger.debug("Checking read access");
                ideaBO.checkReadAccess(bag, loggedUser);
            }
        }
        catch (Exception e) {
        	this.getLogger().error(e);
            bag = null;
        }

        return bag;
    }
}
