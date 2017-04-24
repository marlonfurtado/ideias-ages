package br.com.ideiasages.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.exception.NegocioException;
import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.MensagemContantes;

public class UserBO {
	UserDAO user = new UserDAO();

	public void setUser(UserDAO user) {
		this.user = user;
	}

	public UserBO() {}

	public User validate(User User) throws NegocioException {
		User user = null;
		try {
			// valida se o User existe na base
			user = this.user.getUser(User);
			if (user == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return user;
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
}
