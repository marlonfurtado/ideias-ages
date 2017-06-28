package br.com.ideiasages.controllers;

import br.com.ideiasages.bo.QuestionIdeaBO;
import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.dao.QuestionIdeaDAO;
import br.com.ideiasages.dto.StandardResponseDTO;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.QuestionIdea;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Classe controladora das requisi��es referentes aos questionamentos da id�ia.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 19/06/2017
 * 
 **/
@Path("/ideas/{ideaId}/question")
public class QuestionIdeaController {
	private QuestionIdeaBO boLayer = new QuestionIdeaBO();
	private QuestionIdeaDAO daoLayer = new QuestionIdeaDAO();
	private IdeaDAO ideaDAO = new IdeaDAO();

	Logger logger = Logger.getLogger("controller.IdeaCommentController");

	@Context
	private HttpServletRequest request;

	/**
	 * Adiciona uma nova pergunta � id�ia atrav�s do seu ID.
	 * 
	 * @param ideaId ID da id�ia.{@link br.com.ideiasages.model.Idea}
	 * @param model Objeto referente ao questionamento da id�ia.{@link br.com.ideiasages.model.QuestionIdea}
	 * @return Resposta do m�todo.{@link br.com.ideiasages.dto.StandardResponseDTO}
	 **/
	@POST
	@Path("/")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public StandardResponseDTO add(QuestionIdea model, @PathParam("ideaId") int ideaId) {
		StandardResponseDTO response = new StandardResponseDTO();
		QuestionIdea createdEntity = null;
		Idea idea = null;

		try {
			idea = ideaDAO.getIdea(ideaId);
			User loggedAnalyst = (User)request.getSession().getAttribute("user");
			model.setIdea(idea);
			model.setAnalyst(loggedAnalyst);
			logger.debug("Going to validate the fields");
			boLayer.validate(model);

			logger.debug("Going to create the question based on the value sent");
			createdEntity = daoLayer.add(model);

			if (createdEntity != null) {
				logger.debug("Entity has been created. Id = " + createdEntity.getId());
				ideaDAO.addQuestion(idea, createdEntity);

				logger.debug("Going to create the relationship between idea and comment.");
			}

			response.setSuccess(Boolean.TRUE);
			response.setMessage(MensagemContantes.MSG_IDEA_QUESTION_SAVED);
		}
		catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
		}

		return response;
	}
}
