package br.com.ideiasages.bo;

import br.com.ideiasages.dao.IdeaCommentDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.validator.RequiredFieldsValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Realiza��o de valida��es das regras de neg�cio para {@link br.com.ideiasages.model.IdeaComment}.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 08/06/2017
 **/
public class IdeaCommentBO {
	private IdeaCommentDAO ideaDAO = new IdeaCommentDAO();
	private UserBO userBO = new UserBO();
	private Map<String, Object> item;

	/**
	 * Invoca os validadores correspondentes ao coment�rio de uma id�ia.
	 * 
	 * @param model Objeto coment�rio da id�ia.{@link br.com.ideiasages.model.IdeaComment}
	 * @return Objeto referente ao coment�rio da id�ia.{@link br.com.ideiasages.model.IdeaComment}.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 **/
	public IdeaComment validateFields(IdeaComment model) throws NegocioException, ValidationException, PersistenciaException {
		try {
			validateRequiredFields(model);

			return model;
		} catch (ValidationException e) {
			throw new NegocioException(e);
		}
	}


	/**
	 * Invoca o validador de campos obrigat�rios de {@link br.com.ideiasages.model.IdeaComment}.
	 * 
	 * @param model Objeto referente ao coment�rio da id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso todos os campos obrigat�rios est�o preenchidos.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 **/
	public boolean validateRequiredFields(IdeaComment model) throws ValidationException {
		RequiredFieldsValidator validator = new RequiredFieldsValidator();
		item = new HashMap<>();
		item.put("comment", model.getComment());

		return validator.validar(item);
	}
}
