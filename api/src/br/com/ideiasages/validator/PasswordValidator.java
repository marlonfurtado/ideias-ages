package br.com.ideiasages.validator;

import java.util.Map;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

public class PasswordValidator implements Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		String senha, outraSenha = null;
		
		try {
			senha = valores.get("password").toString();
			outraSenha = valores.get("otherPassword").toString();

			if (!senha.equals(outraSenha)) {
				msgErro.append(MensagemContantes.MSG_ERR_SENHAS_DIFERENTES.concat("<br/>"));
			}
			
			if(senha.length() > 255){
				msgErro.append(MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", "senha").concat("<br/>"));
			}
			
			if(outraSenha.length() > 255){
				msgErro.append(MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", "confirmação de senha").concat("<br/>"));
			}
		} catch (NullPointerException e) {
			msgErro.append(MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", "senha ou confirmação de senha").concat("<br/>"));
		}

		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
