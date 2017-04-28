package br.com.ideiasages.validator;

import java.util.Map;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.util.Util;

public class CPFValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		String cpf = (String) valores.get("cpf");

		if (!Util.isCPF(cpf)) {
			msgErro.append(MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "<b>CPF</b>").concat("<br/>"));
		}

		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
