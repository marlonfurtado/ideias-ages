package br.com.ideiasages.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.validator.routines.EmailValidator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Constantes;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.validator.CPFValidator;
import br.com.ideiasages.validator.EmailsValidator;
import br.com.ideiasages.validator.PhoneNumberValidator;
import br.com.ideiasages.validator.RequiredFieldsValidator;
import br.com.ideiasages.validator.PasswordValidator;

public class UserBO {
	private UserDAO user = new UserDAO();
	private Map<String,Object> item;

	public void setUser(UserDAO user) {
		this.user = user;
	}

	public User userExists(User User) throws NegocioException {
		try {
			User returnedUser = null;
			// valida se o User existe na base
			returnedUser = this.user.getUser(User);
			if (returnedUser == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}

			return returnedUser;
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	public boolean isAdmin(User user) throws NegocioException {
		if (user == null) {
			throw new NegocioException(MensagemContantes.MSG_INF_DENY);
		}

		if (!user.getRole().equals(Constantes.ADMINISTRATOR_ROLE)) {
			throw new NegocioException(MensagemContantes.MSG_INF_ALLOW_ONLY_ADMINISTRATOR);
		}

		return true;
	}

	public User validate(User user) throws NegocioException, ValidationException, PersistenciaException{
		try {
			validateRequiredFields(user);
			validateCPF(user);
			validateEmail(user);

			if(user.getPhone() != null){
				validatePhone(user);
			}

			if(this.user.cpfAlreadyRegistered(user.getCpf())){
				throw new NegocioException(MensagemContantes.MSG_INF_CPF_ALREADY_REGISTERED);
			}

			if(this.user.emailAlreadyRegistered(user.getEmail())){
				throw new NegocioException(MensagemContantes.MSG_INF_EMAIL_ALREADY_REGISTERED);
			}

			return user;
		}catch (ValidationException e) {
			throw new NegocioException(e);
		}
	}

	public boolean validatePassword(User user, String confirmPassword) throws ValidationException{
			item = new HashMap<>();
			PasswordValidator senhaValidator = new PasswordValidator();

			item.put("password", user.getPassword());
			item.put("confirmPassword", confirmPassword);

			return senhaValidator.validar(item);
	}

	public boolean validateEmail(User user) throws ValidationException{
		item = new HashMap<>();
		item.put("email", user.getEmail());
		EmailsValidator emailValidator = new EmailsValidator();

		return emailValidator.validar(item);
	}

	public boolean validatePhone(User user) throws ValidationException{
		item = new HashMap<>();
		PhoneNumberValidator phoneValidator = new PhoneNumberValidator();

		item.put("phone", user.getPhone());

		return phoneValidator.validar(item);
	}

	public boolean validateCPF(User user) throws ValidationException{
		CPFValidator  cpfValidator = new CPFValidator();
		item = new HashMap<>();
		item.put("cpf", user.getCpf());

		return cpfValidator.validar(item);
	}

	public boolean validateRequiredFields(User user) throws ValidationException{
		RequiredFieldsValidator validator = new RequiredFieldsValidator();
		item = new HashMap<>();
		item.put("cpf", user.getCpf());
		item.put("email", user.getEmail());
		item.put("name", user.getName());
		item.put("password", user.getPassword());

		return validator.validar(item);
	}

}
