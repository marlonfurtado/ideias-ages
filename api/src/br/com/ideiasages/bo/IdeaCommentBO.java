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
 * Realização de validações das regras de negócio para {@link br.com.ideiasages.model.IdeaComment}.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 08/06/2017
 **/
public class IdeaCommentBO {
	private IdeaCommentDAO ideaDAO = new IdeaCommentDAO();
	private UserBO userBO = new UserBO();
	private Map<String, Object> item;

	/**
	 * Invoca os validadores correspondentes ao comentário de uma idéia.
	 * 
	 * @param model Objeto comentário da idéia.{@link br.com.ideiasages.model.IdeaComment}
	 * @return Objeto referente ao comentário da idéia.{@link br.com.ideiasages.model.IdeaComment}.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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
	 * Invoca o validador de campos obrigatórios de {@link br.com.ideiasages.model.IdeaComment}.
	 * 
	 * @param model Objeto referente ao comentário da idéia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso todos os campos obrigatórios estão preenchidos.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 **/
	public boolean validateRequiredFields(IdeaComment model) throws ValidationException {
		RequiredFieldsValidator validator = new RequiredFieldsValidator();
		item = new HashMap<>();
		item.put("comment", model.getComment());

		return validator.validar(item);
	}
}
