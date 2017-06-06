package br.com.ideiasages.bo;

import java.util.HashMap;
import java.util.Map;

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

/**
 * Realização de validações das regras de negócio para {@link br.com.ideiasages.model.User}.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 06/06/2017
 * 
 **/
public class UserBO {
	private UserDAO user = new UserDAO();
	private Map<String,Object> item;

	public void setUser(UserDAO user) {
		this.user = user;
	}

	/**
	 * Método que valida se o usuário informado, existe no banco de dados.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @throws {@link br.com.ideiasages.exception.NegocioException} Exceção de validação das regras de negócio.
	 * 
	 **/
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

	/**
	 * Método que verifica se o usuário informado é o Administrador.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.NegocioException} Exceção de validação das regras de negócio.
	 **/
	public boolean isAdmin(User user) throws NegocioException {
		if (user == null) {
			throw new NegocioException(MensagemContantes.MSG_INF_DENY);
		}

		if (!user.getRole().equals(Constantes.ADMINISTRATOR_ROLE)) {
			throw new NegocioException(MensagemContantes.MSG_INF_ALLOW_ONLY_ADMINISTRATOR);
		}

		return true;
	}

	/**
	 * Método que verifica se o usuário informado é um Analista.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.NegocioException} Exceção de validação das regras de negócio.
	 **/
	public boolean isAnalyst(User user) throws NegocioException {
		if (user == null) {
			throw new NegocioException(MensagemContantes.MSG_INF_DENY);
		}

		if (!user.getRole().equals(Constantes.ANALYST_ROLE)) {
			throw new NegocioException(MensagemContantes.MSG_INF_ALLOW_ONLY_ADMINISTRATOR);
		}

		return true;
	}

	/**
	 * Método que verifica se o usuário informado é um Idealizador.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.NegocioException} Exceção de validação das regras de negócio.
	 **/
	public boolean isIdealizer(User user) throws NegocioException {
		if (user == null) {
			throw new NegocioException(MensagemContantes.MSG_INF_DENY);
		}

		if (!user.getRole().equals(Constantes.IDEALIZER_ROLE)) {
			throw new NegocioException(MensagemContantes.MSG_INF_ALLOW_ONLY_ADMINISTRATOR);
		}

		return true;
	}

	/**
	 * Invoca as classes que fazem as validações dos campos pertencentes ao {@link br.com.ideiasages.model.User}.
	 * Também verifica se o CPF ou o e-mail informados já existem na base de dados.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @throws {@link br.com.ideiasages.exception.NegocioException} Exceção de validação das regras de negócio.
	 * @throws {@link br.com.ideiasages.exception.ValidationException} Exceção de validação de campos.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
	 * na base de dados.
	 **/
	public User validate(User user) throws NegocioException, ValidationException, PersistenciaException{
		try {
			validateRequiredFields(user);
			validateCPF(user);
			validateEmail(user);

			if((user.getPhone() != null) && (user.getPhone() != "")){
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

	/**
	 * Invoca o validador dos campos 'senha' e 'confirmação de senha' pertencentes ao modelo {@link br.com.ideiasages.model.User}.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @param confirmPassword Confirmação da senha informada para o usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.ValidationException} Exceção de validação de campos.
	 **/
	public boolean validatePassword(User user, String confirmPassword) throws ValidationException{
		item = new HashMap<>();
		PasswordValidator senhaValidator = new PasswordValidator();

		item.put("password", user.getPassword());
		item.put("confirmPassword", confirmPassword);

		return senhaValidator.validar(item);
	}

	/**
	 * Invoca o validador de e-mail pertencente ao {@link br.com.ideiasages.model.User}.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.ValidationException} Exceção de validação de campos.
	 **/
	public boolean validateEmail(User user) throws ValidationException{
		item = new HashMap<>();
		item.put("email", user.getEmail());
		EmailsValidator emailValidator = new EmailsValidator();

		return emailValidator.validar(item);
	}

	/**
	 * Invoca o validador de telefone pertencente ao {@link br.com.ideiasages.model.User}.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.ValidationException} Exceção de validação de campos.
	 **/
	public boolean validatePhone(User user) throws ValidationException{
		item = new HashMap<>();
		PhoneNumberValidator phoneValidator = new PhoneNumberValidator();

		item.put("phone", user.getPhone());

		return phoneValidator.validar(item);
	}

	/**
	 * Invoca o validador de CPF pertencente ao {@link br.com.ideiasages.model.User}.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.ValidationException} Exceção de validação de campos.
	 **/
	public boolean validateCPF(User user) throws ValidationException{
		CPFValidator  cpfValidator = new CPFValidator();
		item = new HashMap<>();
		item.put("cpf", user.getCpf());

		return cpfValidator.validar(item);
	}

	/**
	 * Invoca o validador dos campos obrigatórios pertencentes ao {@link br.com.ideiasages.model.User}.
	 * 
	 * @param {@link br.com.ideiasages.model.User} Classe de modelo do usuário.
	 * @return boolean
	 * @throws {@link br.com.ideiasages.exception.ValidationException} Exceção de validação de campos.
	 **/
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
