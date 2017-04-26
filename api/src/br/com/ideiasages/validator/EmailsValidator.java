package br.com.ideiasages.validator;

import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.util.Util;

public class EmailsValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		
		for (String key : valores.keySet()) {
			String email = (String) valores.get(key);

			if (!"".equals(email)) {
				EmailValidator emailValidator = EmailValidator.getInstance();
				
				if (!emailValidator.isValid(email)) {
					msgErro.append(MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>"));
				}
				
				if (email.length() > 100) {				
					msgErro.append(MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", key).concat("<br/>"));
				}
			} else {
				msgErro.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>"));
			}
		}
		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
