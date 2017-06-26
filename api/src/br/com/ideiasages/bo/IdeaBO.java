package br.com.ideiasages.bo;

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
 * Realiza��o de valida��es das regras de neg�cio para {@link br.com.ideiasages.model.Idea}.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 *
 **/
public class IdeaBO {

	private UserBO userBO = new UserBO();
	private Map<String, Object> item;

	/**
	 * Invoca os validadores correspondentes a {@link br.com.ideiasages.model.Idea}.
	 *
	 * @param idea Objeto id�ia. {@link br.com.ideiasages.model.Idea}
	 * @return Objeto id�ia. {@link br.com.ideiasages.model.Idea}
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
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
	 * Verifica se a {@link br.com.ideiasages.model.Idea} � rascunho.
	 *
	 * @param idea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso seja um rascunho.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 **/
	public boolean isDraft(Idea idea) throws NegocioException {
		if (!idea.getStatus().equals(IdeaStatus.DRAFT)) {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}

		return true;
	}

	/**
	 * Verifica se a {@link br.com.ideiasages.model.Idea} � ABERTA.
	 *
	 * @param idea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso esteja aberta.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 **/
	public boolean isOpened(Idea idea) throws NegocioException {
		if (!idea.getStatus().equals(IdeaStatus.OPEN)) {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}

		return true;
	}

    public void checkReadAccess(Idea idea, User user) throws NegocioException {
        //in case it is an idealizer
        if (userBO.isIdealizer(user))
            isOwnedByUser(idea, user);

        //otherwise, the owners (admin and analyst) has always read-access
    }

    /**
     * Verifica se o mesmo que o autor da id�ia.
     *
     * @param idea Objeto id�ia. {@link br.com.ideiasages.model.Idea}
     * @param user Objeto usu�rio. {@link br.com.ideiasages.model.User}
     * @return Verdadeiro caso o usu�rio seja o pr�prio autor da id�ia.
     * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
     **/
    public boolean isOwnedByUser(Idea idea, User user) throws NegocioException {
        if (!idea.getUser().getCpf().equals(user.getCpf())) {
            throw new NegocioException(MensagemContantes.MSG_NOT_AUTHORIZED);
        }

		return true;
	}

	/**
	 * Invoca o validador de campos obrigat�rios de {@link br.com.ideiasages.model.Idea}.
	 *
	 * @param idea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro caso todos os campos obrigat�rios est�o preenchidos.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��o de campos.
	 **/
	public boolean validateRequiredFields(Idea idea) throws ValidationException {
		RequiredFieldsValidator validator = new RequiredFieldsValidator();
		item = new HashMap<>();
		item.put("title", idea.getTitle());
		item.put("description", idea.getDescription());
		item.put("goal", idea.getGoal());

		return validator.validar(item);
	}

	/**
	 * Verifica o status da id�ia para checar se j� foi para an�lise.
	 *
	 * @param status Status da id�ia.
	 * @return Verdadeiro caso o status n�o seja nulo.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 **/
	public boolean validateStatus(String status) throws NegocioException {
		if (status == null) {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}

		return true;
	}

	/**
	 * Verifica se o status da id�ia � v�lido e compat�vel de acordo com o tipo de usu�rio.
	 *
	 * @param idea  Objeto id�ia. {@link br.com.ideiasages.model.Idea}
	 * @param user  Objeto usu�rio. {@link br.com.ideiasages.model.User}
	 * @return Verdadeiro se o usu�rio � v�lido conforme o status.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 **/
	public boolean validateStatusByUser(Idea idea, User user) throws NegocioException, PersistenciaException {
		IdeaStatus status = idea.getStatus();

		if (status != null) {
			if (status.equals(IdeaStatus.DRAFT) || status.equals(IdeaStatus.OPEN)) {
				if (userBO.isIdealizer(user)) {
					return true;
				}
			} else {
				if (userBO.isAnalyst(user)) {
					if (status.equals(IdeaStatus.UNDER_ANALYSIS)) {
						return true;
					} else {
						if (this.isTakenByAnalyst(idea.getId(), user.getCpf()) != null) {
							return true;
						}
					}
				}
			}
		} else {
			throw new NegocioException(MensagemContantes.MSG_NOT_AUTHORIZED);
		}

		return false;
	}

	private Idea isTakenByAnalyst(int id, String cpf) throws PersistenciaException {
		return ideaDAO.getIdeaByAnalyst(id, cpf);
	}

	/**
	 * Verifica se � poss�vel mudar o status de uma id�ia.
	 *
	 * @param idea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @param newStatus Novo status da id�ia.
	 * @return Verdadeiro se o status antigo e o novo status est�o de acordo com as regras de neg�cio.
	 * @throws br.com.ideiasages.exception.NegocioException Exce��o de valida��o das regras de neg�cio.
	 **/
	public boolean isPossibleChangeStatus(Idea idea, String newStatus) throws NegocioException {
		String ideaStatus = idea.getStatus().name();
		newStatus = newStatus.toUpperCase();

		if (ideaStatus.equals(IdeaStatus.DRAFT.name()) && newStatus.equals(IdeaStatus.OPEN.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.OPEN.name()) && newStatus.equals(IdeaStatus.UNDER_ANALYSIS.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.OPEN.name()) && newStatus.equals(IdeaStatus.REJECTED.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.UNDER_ANALYSIS.name()) && newStatus.equals(IdeaStatus.APPROVED.name())) {
			return true;
		} else if (ideaStatus.equals(IdeaStatus.UNDER_ANALYSIS.name()) && newStatus.equals(IdeaStatus.REJECTED.name())) {
			return true;
		} else {
			throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
		}
	}

}
