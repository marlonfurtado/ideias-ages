package br.com.ideiasages.bo;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import br.com.ideiasages.dao.PasswordChangeRequestDAO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.exception.ValidationException;
import br.com.ideiasages.model.PasswordChangeRequest;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.Constantes;
import br.com.ideiasages.util.EncryptUtil;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.util.SendMail;
import br.com.ideiasages.util.Util;
import br.com.ideiasages.validator.CPFValidator;
import br.com.ideiasages.validator.EmailsValidator;
import br.com.ideiasages.validator.PasswordValidator;
import br.com.ideiasages.validator.PhoneNumberValidator;
import br.com.ideiasages.validator.RequiredFieldsValidator;

/**
 * Realização de validações das regras de negócio para {@link br.com.ideiasages.model.User}.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 *
 **/
public class UserBO {
	private UserDAO user = new UserDAO();
	private PasswordChangeRequestDAO passwordChangeRequestDAO = new PasswordChangeRequestDAO();
	private Util util = new Util();
	private EncryptUtil encryptUtil = new EncryptUtil();
	private SendMail sendMail = new SendMail();
	
	private Map<String,Object> item;

	public void setUser(UserDAO user) {
		this.user = user;
	}

	/**
	 * Método que valida se o usuário informado, existe no banco de dados.
	 *
	 * @param  user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Usuário já existente na base de dados.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 **/
	public User userExists(User user) throws NegocioException {
		try {
			User returnedUser = null;
			// valida se o User existe na base
			String encryptedPassword = encryptUtil.encrypt2(user.getPassword());
			user.setPassword(encryptedPassword);
			returnedUser = this.user.getUser(user);
			if (returnedUser == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}

			return returnedUser;
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}

	public User getUserByCpf(User User) throws NegocioException {
		try {
			User returnedUser = null;
			// valida se o User existe na base
			returnedUser = this.user.getUserByCpf(User);
			if (returnedUser == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_NAO_EXISTE);
			}
			
			return returnedUser;
		} catch (Exception e) {
			throw new NegocioException(e);
		}
	}
	/**
	 * Método que verifica se o usuário informado é o Administrador.
	 *
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso o usuário seja o administrador.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
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
	 * Método que verifica se o usuário informado é um analista.
	 *
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso o usuário seja um analista.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
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
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso o usuário seja um idealizador.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
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
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Usuário validado.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
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
	 * Invoca as classes que fazem as validações dos campos pertencentes ao {@link br.com.ideiasages.model.User}.
	 * Também verifica se o CPF ou o e-mail informados já existem na base de dados.
	 *
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Usuário validado.
	 * @throws br.com.ideiasages.exception.NegocioException Exceção de validação das regras de negócio.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 **/
	public User validateToUpdate(User user, User originalUser) throws NegocioException, ValidationException, PersistenciaException{
		try {
			validateRequiredFields(user);
			validateEmail(user);

			if((user.getPhone() != null) && (user.getPhone() != "")){
				validatePhone(user);
			}

			if(this.user.emailAlreadyRegistered(user.getEmail(), originalUser.getEmail())){
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
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @param confirmPassword Confirmação da senha informada para o usuário.
	 * @return Verdadeiro caso as senhas sejam idênticas.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
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
	 * @param  user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso o e-mail esteja com a estrutura válida.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
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
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso o telefone tenha a estrutura válida.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
	 **/
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

	/**
	 * Invoca o validador dos campos obrigatórios pertencentes ao {@link br.com.ideiasages.model.User}.
	 *
	 * @param user Objeto usuário.{@link br.com.ideiasages.model.User}
	 * @return Verdadeiro caso todos os campos obrigatórios estão preenchidos.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validação de campos.
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
	
	@SuppressWarnings("static-access")
	public void createPasswordChangeRequest(User user, String baseURL) throws NegocioException{
		String uuid = null;
		String encyptedUuid = null;
		
		try {
			uuid = util.generateUUID();
			encyptedUuid = encryptUtil.encrypt2(uuid);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_ENCRYPT_PASSWORD_CHANGE_REQUEST);
		}
		
		try {
			PasswordChangeRequest passwordChangeRequestDTO = new PasswordChangeRequest();
			passwordChangeRequestDTO.setRequestId(encyptedUuid);
			passwordChangeRequestDTO.setRequestDateTime(Calendar.getInstance());
			passwordChangeRequestDTO.setUser(user);
			passwordChangeRequestDAO.addPasswordChangeRequest(passwordChangeRequestDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_SAVE_PASSWORD_CHANGE_REQUEST);
		}
		
		try {
			String emailSubject = "Recuperar senha";
			String emailMessage = 	"Ideias AGES\n\n" +
					"\tRecuperar senha: " + baseURL + "recoverPassword?token=" + uuid +
					"\n\nObrigada,\n" +
					"AGES";
			
			sendMail.envio(user.getEmail(), user.getName(), emailSubject, emailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_EMAIL_PASSWORD_CHANGE_REQUEST);
		}
	}

}
