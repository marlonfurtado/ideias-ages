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

public class IdeaBO {
    private IdeaDAO ideaDAO = new IdeaDAO();
    private UserBO userBO = new UserBO();
    private Map<String, Object> item;

    public Idea validateFields(Idea idea) throws NegocioException, ValidationException, PersistenciaException {
        try {
            validateRequiredFields(idea);

            return idea;
        } catch (ValidationException e) {
            throw new NegocioException(e);
        }
    }

    public boolean isDraft(Idea idea) throws NegocioException {
        if (!idea.getStatus().equals(IdeaStatus.DRAFT)) {
            throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
        }

        return true;
    }

    public boolean isOwnedByUser(Idea idea, User user) throws NegocioException {
        if (!idea.getUser().getCpf().equals(user.getCpf())) {
            throw new NegocioException(MensagemContantes.MSG_NOT_AUTHORIZED);
        }

        return true;
    }

    public boolean validateRequiredFields(Idea idea) throws ValidationException {
        RequiredFieldsValidator validator = new RequiredFieldsValidator();
        item = new HashMap<>();
        item.put("title", idea.getTitle());
        item.put("description", idea.getDescription());
        item.put("tags", idea.getTags());
        item.put("goal", idea.getGoal());

        return validator.validar(item);
    }

    public boolean validateStatus(String status) throws NegocioException {
        if (status == null) {
            throw new NegocioException(MensagemContantes.MSG_IDEA_IS_NOT_DRAFT);
        }

        return true;
    }

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
