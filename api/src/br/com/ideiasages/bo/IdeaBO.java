package br.com.ideiasages.bo;

import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.RequiredFieldsValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Realização de validações das regras de negócio para {@link br.com.ideiasages.model.Idea}.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 *
 **/
public class IdeaBO {

	private IdeaDAO ideaDAO = new IdeaDAO();
	private UserBO userBO = new UserBO();
	private Map<String, Object> item;

	/**
	 * Invoca os validadores correspondentes a {@link br.com.ideiasages.model.Idea}.
	 *
	 * @param idea Objeto idéia. {@link br.com.ideiasages.model.Idea}
	 * @return Objeto idéia. {@link br.com.ideiasages.model.Idea}
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 **/
	public Idea validateFields(Idea idea) throws NegocioException, ValidationException, PersistenciaException {
		try {
			if (!idea.getTitle().isEmpty()) {
				return idea;
			}


			validateRequiredFields(idea);

			return idea;
		} catch (ValidationException e) {
			throw new NegocioException(e);
		}
	}

	/**
	 * Verifica se a {@link br.com.ideiasages.model.Idea} é rascunho.
	 *
	 * @param idea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso seja um rascunho.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public boolean isDraft(Idea idea) throws NegocioException {
		if (!idea.getStatus().equals(IdeaStatus.DRAFT)) {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}

		return true;
	}

	/**
	 * Verifica se a {@link br.com.ideiasages.model.Idea} é ABERTA.
	 *
	 * @param idea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso esteja aberta.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public boolean isOpened(Idea idea) throws NegocioException {
		if (!idea.getStatus().equals(IdeaStatus.OPEN)) {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}

		return true;
	}

	/**
	 * Verifica se o mesmo que o autor da idéia.
	 *
	 * @param idea Objeto idéia. {@link br.com.ideiasages.model.Idea}
	 * @param user Objeto usuário. {@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso o usuário seja o próprio autor da idéia.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public boolean isOwnedByUser(Idea idea, User user) throws NegocioException {
		if (!idea.getUser().getCpf().equals(user.getCpf())) {
			throw new NegocioException(MensagemContantes.MSG_NOT_AUTHORIZED);
		}

		return true;
	}

	/**
	 * Invoca o validador de campos obrigatórios de {@link br.com.ideiasages.model.Idea}.
	 *
	 * @param idea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso todos os campos obrigatórios estão preenchidos.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 **/
	public boolean validateRequiredFields(Idea idea) throws ValidationException {
		RequiredFieldsValidator validator = new RequiredFieldsValidator();
		item = new HashMap<>();
		item.put("title", idea.getTitle());
		item.put("description", idea.getDescription());
		item.put("tags", idea.getTags());
		item.put("goal", idea.getGoal());

		return validator.validar(item);
	}

	/**
	 * Verifica o status da idéia para checar se já foi para análise.
	 *
	 * @param status Status da idéia.
	 * @return Verdadeiro caso o status não seja nulo.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public boolean validateStatus(String status) throws NegocioException {
		if (status == null) {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}

		return true;
	}

	/**
	 * Verifica se o status da idéia é válido e compatível de acordo com o tipo de usuário.
	 *
	 * @param idea  Objeto idéia. {@link br.com.ideiasages.model.Idea}
	 * @param user  Objeto usuário. {@link br.com.ideiasages.model.User}
	 * @return Verdadeiro se o usuário é válido conforme o status.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public boolean validateStatusByUser(Idea idea, User user) throws NegocioException {
		IdeaStatus status = idea.getStatus();

		if (status != null) {
			if (status.equals(IdeaStatus.DRAFT) || status.equals(IdeaStatus.OPEN)) {
				if (userBO.isIdealizer(user)) {
					return true;
				}
			} else {
				if (userBO.isAnalyst(user)) {
					return true;
				}
			}
		} else {
			throw new NegocioException(MensagemContantes.MSG_NOT_AUTHORIZED);
		}

		return false;
	}

	/**
	 * Verifica se é possível mudar o status de uma idéia.
	 *
	 * @param idea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @param newStatus Novo status da idéia.
	 * @return Verdadeiro se o status antigo e o novo status estão de acordo com as regras de negócio.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public boolean isPossibleChangeStatus(Idea idea, String newStatus) throws NegocioException {
		String ideaStatus = idea.getStatus().name();
		newStatus = newStatus.toUpperCase();

		if (ideaStatus.equals(IdeaStatus.DRAFT.name()) && newStatus.equals(IdeaStatus.OPEN.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.OPEN.name()) && newStatus.equals(IdeaStatus.IN_ANALYSIS.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.IN_ANALYSIS.name()) && newStatus.equals(IdeaStatus.APPROVED.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.IN_ANALYSIS.name()) && newStatus.equals(IdeaStatus.REJECTED.name())) {
			return true;
		} else {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}
	}

}
