package br.com.ideiasages.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

/**
 * Classe responsável pela validação de datas.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class DataValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		String msgErro = "";
		for (String key : valores.keySet()) {
			String data = (String) valores.get(key);
			if (!"".equals(data)) {
				try {
					if (data.length() < 10) {
						msgErro += MensagemContantes.MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO.replace("?", key).concat("<br/>");
					}
					if (data.length() > 11) {				
						msgErro += MensagemContantes.MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO.replace("?", key).concat("<br/>");
					}
					new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} catch (ParseException e) {
					msgErro += MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
				}
			}
		}
		if (!"".equals(msgErro)) {
			throw new ValidationException(msgErro);
		}

		return true;
	}

	public boolean maisCedoQue(Date a, Date b){ //inventar um nome melhor
		if(a.before(b))return true;		
		return false;
	}
}
