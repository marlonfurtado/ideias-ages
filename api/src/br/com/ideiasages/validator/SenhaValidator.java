package br.com.ideiasages.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

public class SenhaValidator implements Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();

		String senha = (String) valores.get("senha");
		String outraSenha = (String) valores.get("outraSenha");
		String action = (String) valores.get("action");
		
		if (!"".equals(senha) && senha != null) {
			if (!senha.equals(outraSenha)) {
				if(action.equals("confirmation")){
					msgErro.append(MensagemContantes.MSG_ERR_SENHAS_DIFERENTES.concat("<br/>"));
				}
				
				if(action.equals("change")){
					msgErro.append(MensagemContantes.MSG_ERR_SENHA_INVALIDA.concat("<br/>"));
				}
			}
		} else {
			msgErro.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", senha).concat("<br/>"));
		}

		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
