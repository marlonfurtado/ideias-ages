package br.com.ideiasages.validator;

import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

/**
 * Classe responsável pela validação de e-mail.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class EmailsValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		String email = null;

		try {
			email = valores.get("email").toString();

			EmailValidator emailValidator = EmailValidator.getInstance();

			if (!emailValidator.isValid(email)) {
				msgErro.append(MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "<b>email</b>").concat("<br/>"));
			}

			if (email.length() > 100) {
				msgErro.append(
						MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", "<b>email</b>").concat("<br/>"));
			}

		} catch (NullPointerException e) {
			msgErro.append(MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "<b>email</b>").concat("<br/>"));
		}


		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}


		return true;
	}

}
