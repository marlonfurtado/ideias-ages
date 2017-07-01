package br.com.ideiasages.bo;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import br.com.ideiasages.dao.PasswordChangeRequestDAO;
import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.model.PasswordChangeRequest;
import br.com.ideiasages.model.PasswordChangeWrapper;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.EncryptUtil;
import br.com.ideiasages.util.MensagemContantes;
import br.com.ideiasages.util.SendMail;
import br.com.ideiasages.util.Util;

public class PasswordChangeBO {
	
	private PasswordChangeRequestDAO passwordChangeRequestDAO = new PasswordChangeRequestDAO();
	private UserDAO userDAO = new UserDAO();
	
	private Util util = new Util();
	private EncryptUtil encryptUtil = new EncryptUtil();
	private SendMail sendMail = new SendMail();
		
	@SuppressWarnings("static-access")
	public void createPasswordChangeRequest(User user, String baseURL) throws NegocioException {
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
					"\tRecuperar senha: " + baseURL + "projetos/ideias/recuperar_senha.jsp?token=" + uuid +
					"\n\tEsse link irá expirar após 24 horas.\n" +
					"\n\nObrigada,\n" +
					"AGES";
			
			sendMail.envio(user.getEmail(), user.getName(), emailSubject, emailMessage);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_EMAIL_PASSWORD_CHANGE_REQUEST);
		}
	}
	
	@SuppressWarnings("static-access")
	public PasswordChangeRequest veifyToken(String token) throws NegocioException {
		try {
			String encryptedToken = encryptUtil.encrypt2(token);
			PasswordChangeRequest passwordChangeRequest = passwordChangeRequestDAO.getByToken(encryptedToken);
			return passwordChangeRequest;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_INVALID_PASSWORD_CHANGE_REQUEST);
		}
	}
	
	@SuppressWarnings("static-access")
	public void changePassword(PasswordChangeWrapper passwordChangeWrapper) throws NegocioException {
		try {
			String encryptedToken = encryptUtil.encrypt2(passwordChangeWrapper.getToken());
			PasswordChangeRequest passwordChangeRequest = passwordChangeRequestDAO.getByToken(encryptedToken);
			if (passwordChangeRequest == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_INVALID_PASSWORD_CHANGE_REQUEST);
			}
			
			User user = passwordChangeRequest.getUser();
			String encryptedPassword = encryptUtil.encrypt2(passwordChangeWrapper.getPassword());
			user.setPassword(encryptedPassword);
			
			userDAO.changePassword(user);
			
			passwordChangeRequestDAO.deleteByToken(encryptedToken);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_PASSWORD_CHANGE);
		}
	}

}
