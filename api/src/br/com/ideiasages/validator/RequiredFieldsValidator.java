package br.com.ideiasages.validator;

import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.util.Util;

public class RequiredFieldsValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		
		for (String key : valores.keySet()) {
			String value = (String) valores.get(key);

			if (value == null || value.isEmpty()) {
				key = "'" + key.concat("'"); 
				msgErro.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>"));
			}
		}
		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
