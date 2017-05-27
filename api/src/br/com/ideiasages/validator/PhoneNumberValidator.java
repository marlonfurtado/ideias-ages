package br.com.ideiasages.validator;

import java.util.Map;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.util.MensagemContantes;

public class PhoneNumberValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws ValidationException {
		StringBuilder msgErro = new StringBuilder();
		try {
			String phone = valores.get("phone").toString();
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneNumber = phoneUtil.parse(phone, "BR");

			if (!phoneUtil.isValidNumber(phoneNumber)) {
				msgErro.append(MensagemContantes.MSG_ERR_TELEFONE_INVALIDO);
			}
		} catch (NumberParseException | NullPointerException e) {
			throw new ValidationException(MensagemContantes.MSG_ERR_TELEFONE_INVALIDO);
		}

		if (msgErro.length() > 0) {
			throw new ValidationException(msgErro.toString());
		}

		return true;
	}
}
