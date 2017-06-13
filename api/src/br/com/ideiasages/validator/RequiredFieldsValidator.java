package br.com.ideiasages.validator;

import java.util.Map;
import java.util.Objects;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

/**
 * Classe responsável pela validação de campos obrigatórios.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 *
 **/
public class RequiredFieldsValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		String value = null;
        boolean flag = false;
		try {
			for (String key : valores.keySet()) {

				value = valores.get(key).toString();
				if (key.equals("tags") && (value.isEmpty() || Objects.isNull(value))) {
					continue;
				}
				if (Objects.isNull(value) || value.isEmpty()) {
					flag = true;
				}

			}
		} catch (Exception e) {
			throw new ValidationException(MensagemContantes.MSG_IDEA_NOT_SAVED);
		}

		if (flag) {
			throw new ValidationException(MensagemContantes.MSG_ERR_CAMPOS_OBRIGATORIOS);
		}

		if (!msgErro.toString().isEmpty()) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}

}
