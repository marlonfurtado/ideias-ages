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
import br.com.ideiasages.validator.SenhaValidator;

public class UserBO {
	private UserDAO user = new UserDAO();
	private Map<String,Object> item;

	public void setUser(UserDAO user) {
		this.user = user;
	}

	public UserBO() {
	}

	public User userExists(User User) throws NegocioException {
		try {
			User returnedUser = null;
			// valida se o User existe na base
			returnedUser = this.user.getUser(User);
			if (user == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}
			
			return returnedUser;
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	public ArrayList<User> getActiveUsers() throws NegocioException {
		ArrayList<User> listUser = null;

		try {
			listUser = user.getActiveUsers();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listUser;
	}

	public boolean isAdmin(User user) throws NegocioException {
		if (user == null) {
			throw new NegocioException(MensagemContantes.MSG_INF_DENY);
		}

		if (user.getRole().equals(Constantes.ADMINISTRATOR_ROLE)) {
			throw new NegocioException(MensagemContantes.MSG_INF_ALLOW_ONLY_ADMINISTRATOR);
		}

		return true;
	}

	public User validate(User user) throws NegocioException, ValidationException{
		try {
			validateCPF(user);
			validateEmail(user);
			validatePassword(user);
			validatePhone(user);
			
			return user;
		}catch (ValidationException e) {
			throw new NegocioException(e);
		}
	}
	
	public boolean validatePassword(User user) throws ValidationException{
		if(user.getPasswordConfirmation() != null || !user.getPasswordConfirmation().isEmpty()){
			item = new HashMap<>();
			SenhaValidator senhaValidator = new SenhaValidator();
			String action = "confirmation";
			
			if(user.getPassword() != null || !user.getPassword().isEmpty()){
				action = "change";
			}
			
			item.put("senha", user.getPassword());
			item.put("outraSenha", user.getPasswordConfirmation());
			item.put("action", action);
			
			return senhaValidator.validar(item);
		}
		
		return false;
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
		
		item.put("telefone", user.getPhone());
		
		return phoneValidator.validar(item);
	}
	
	public boolean validateCPF(User user) throws ValidationException{
		CPFValidator  cpfValidator = new CPFValidator();
		item = new HashMap<>();
		item.put("cpf", user.getCpf());
		
		return cpfValidator.validar(item);
	}

}
