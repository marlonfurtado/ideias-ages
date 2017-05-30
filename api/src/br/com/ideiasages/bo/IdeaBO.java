package br.com.ideiasages.bo;

import br.com.ideiasages.dao.IdeaDAO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Constantes;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.RequiredFieldsValidator;

import java.util.HashMap;
import java.util.Map;

public class IdeaBO {
    private IdeaDAO ideaDAO = new IdeaDAO();
    private Map<String,Object> item;

    public Idea validateFields(Idea idea) throws NegocioException, ValidationException, PersistenciaException {
        try {
            validateRequiredFields(idea);

            return idea;
        }catch (ValidationException e) {
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
            throw new NegocioException(MensagemContantes.MSG_IDEA_IS_OWNED_BY_USER);
        }

        return true;
    }

    public boolean validateRequiredFields(Idea idea) throws ValidationException{
        RequiredFieldsValidator validator = new RequiredFieldsValidator();
        item = new HashMap<>();
        item.put("title", idea.getTitle());
        item.put("description", idea.getDescription());
        item.put("tags", idea.getTags());
        item.put("goal", idea.getGoal());

        return validator.validar(item);
    }
}
