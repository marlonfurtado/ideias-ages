package br.com.ideaisages.validator;

import java.util.Map;

import br.com.ideaisages.exception.ValidationException;
import br.com.ideaisages.util.MensagemContantes;
import br.com.ideaisages.util.Util;

public class CPFValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		String msgErro = "";
		for (String key : valores.keySet()) {
			String cpf = (String) valores.get(key);

			if (!"".equals(cpf)) {
				if (!Util.isCPF(cpf)) {
					msgErro += MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
				}
				if (cpf.length() < 11) {
					msgErro += MensagemContantes.MSG_ERR_CAMPO_CPF_MENOR_RECOMENDADO.replace("?", key).concat("<br/>");
				}
				if (cpf.length() > 11) {				
					msgErro += MensagemContantes.MSG_ERR_CAMPO_CPF_MAIOR_RECOMENDADO.replace("?", key).concat("<br/>");
				}
			} else {
				msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>");
			}
		}
		if (!"".equals(msgErro)) {
			throw new ValidationException(msgErro);
		}

		return true;
	}

}
