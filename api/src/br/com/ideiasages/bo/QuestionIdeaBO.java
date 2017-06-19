package br.com.ideiasages.bo;

import br.com.ideiasages.dao.QuestionIdeaDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.QuestionIdea;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.CPFValidator;
import br.com.ideiasages.validator.RequiredFieldsValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.tomcat.util.codec.binary.StringUtils;

/**
 * Realiza��o de valida��es das regras de neg�cio para {@link br.com.ideiasages.model.QuestionIdea}.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 19/06/2017
 **/
public class QuestionIdeaBO {
	private QuestionIdeaDAO questionIdeaDAO = new QuestionIdeaDAO();
	private UserBO userBO = new UserBO();
	private Map<String, Object> item;

	/**
	 * Invoca os validadores correspondentes ao questionamento de uma id�ia.
	 * 
	 * @param model Objeto de questionamento da id�ia.{@link br.com.ideiasages.model.QuestionIdea}
	 * @return Objeto referente ao questionamento da id�ia.{@link br.com.ideiasages.model.QuestionIdea}.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 **/
	public QuestionIdea validate(QuestionIdea model) throws NegocioException, ValidationException, PersistenciaException {
		try {
			validateRequiredFields(model);
			validateUserCpf(model);
			existsAnalystByCpf(model.getAnalyst());
			
			return model;
		} catch (ValidationException e) {
			throw new NegocioException(e);
		}
	}


	/**
	 * Invoca o validador de campos obrigat�rios de {@link br.com.ideiasages.model.QuestionIdea}.
	 * 
	 * @param model Objeto referente ao questionamento dae uma id�ia.{@link br.com.ideiasages.model.QuestionIdea}
	 * @return Verdadeiro caso todos os campos obrigat�rios est�o preenchidos.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 **/
	public boolean validateRequiredFields(QuestionIdea model) throws ValidationException {
		RequiredFieldsValidator validator = new RequiredFieldsValidator();
		item = new HashMap<>();
		item.put("question", model.getQuestion());
		item.put("user_cpf", model.getAnalyst().getCpf());

		return validator.validar(item);
	}

	/**
	 * Invoca o validador de CPF referente ao {@link br.com.ideiasages.model.User}.
	 * 
	 * @param model Objeto referente ao questionamento de uma id�ia.{@link br.com.ideiasages.model.QuestionIdea}
	 * @return Verdadeiro caso o CPF seja v�lido.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 **/
	public boolean validateUserCpf(QuestionIdea model) throws ValidationException {
		CPFValidator validator = new CPFValidator();
		item = new HashMap<>();
		item.put("user_cpf", model.getAnalyst().getCpf());

		return validator.validar(item);
	}

	/**
	 * Verifica no banco de dados se o usu�rio existe no banco de dados e se � um administrador. {@link br.com.ideiasages.model.User}.
	 * 
	 * @param user Objeto referente ao usu�rio que ser� verificado.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso exista um avaliador com o CPF informado no objeto.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 **/
	public boolean existsAnalystByCpf(User user) throws ValidationException, NegocioException {
		User returnedUser = userBO.getUserByCpf(user);

		if(Objects.isNull(returnedUser) || returnedUser.getRole().equals("analyst")){
			throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_NAO_EXISTE);
		}

		return Boolean.TRUE;
	}
	
	/**
	 * Verifica se a id�ia informada possui perguntas n�o respondidas.
	 * 
	 * @param model Objeto referente ao questionamento de uma id�ia.{@link br.com.ideiasages.model.QuestionIdea}
	 * @return Verdadeiro caso exista um avaliador com o CPF informado no objeto.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 **/
	public boolean ideaHasNotQuestionAnswered(Idea idea) throws ValidationException, NegocioException, PersistenciaException {
		QuestionIdea questionIdea = questionIdeaDAO.findByIdea(idea);
		
		if(Objects.isNull(questionIdea.getAnswer()) || questionIdea.getAnswer().isEmpty()){
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
