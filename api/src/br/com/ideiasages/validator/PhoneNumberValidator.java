package br.com.ideiasages.validator;

import java.util.Map;

import org.apache.commons.validator.routines.EmailValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.util.Util;

public class PhoneNumberValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		try {
			StringBuilder msgErro = new StringBuilder();

			for (String key : valores.keySet()) {
				String phone = (String) valores.get(key);

				if (!phone.isEmpty()) {
					PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
					PhoneNumber phoneNumber = phoneUtil.parse(phone, "BR");

					if (!phoneUtil.isValidNumber(phoneNumber)) {
						msgErro.append(MensagemContantes.MSG_ERR_TELEFONE_INVALIDO);
					}

					if (phoneNumber.getRawInput().length() > 100) {
						msgErro.append(
								MensagemContantes.MSG_ERR_CAMPO_EXCEDE_TAMANHO.replace("?", key).concat("<br/>"));
					}
				}else{
					return true;
				}
			}
			if (msgErro.length() > 0) {
				throw new ValidationException(msgErro.toString());
			}

			return true;
		} catch (NumberParseException e) {
			throw new ValidationException(MensagemContantes.MSG_ERR_TELEFONE_INVALIDO);
		}
	}

}
