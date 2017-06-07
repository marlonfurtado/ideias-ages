package br.com.ideiasages.bo;

import br.com.ideiasages.dao.IdeaCommentDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.validator.RequiredFieldsValidator;

import java.util.HashMap;
import java.util.Map;

public class IdeaCommentBO {
    private IdeaCommentDAO ideaDAO = new IdeaCommentDAO();
    private UserBO userBO = new UserBO();
    private Map<String, Object> item;

    public IdeaComment validateFields(IdeaComment model) throws NegocioException, ValidationException, PersistenciaException {
        try {
            validateRequiredFields(model);

            return model;
        } catch (ValidationException e) {
            throw new NegocioException(e);
        }
    }

    public boolean validateRequiredFields(IdeaComment model) throws ValidationException {
        RequiredFieldsValidator validator = new RequiredFieldsValidator();
        item = new HashMap<>();
        item.put("comment", model.getComment());

        return validator.validar(item);
    }
}
