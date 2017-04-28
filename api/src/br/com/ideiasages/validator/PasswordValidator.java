package br.com.ideiasages.validator;

import java.util.Map;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

public class PasswordValidator implements Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		String senha = (String) valores.get("password");
		String outraSenha = (String) valores.get("otherPassword");

		if (!senha.equals(outraSenha)) {
			msgErro.append(MensagemContantes.MSG_ERR_SENHAS_DIFERENTES.concat("<br/>"));
		}

		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
