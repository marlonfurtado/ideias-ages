package br.com.ideaisages.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import br.com.ideaisages.dao.UserDAO;
import br.com.ideaisages.exception.NegocioException;
import br.com.ideaisages.exception.PersistenciaException;
import br.com.ideaisages.model.User;
import br.com.ideaisages.util.MensagemContantes;

/**
 * Gerencia os comportamentos de neggocio do User Associa os parametros da
 * tela as propriedades da classe
 * 
 * @author Cassio Trindade
 * 
 */
public class UserBO {
	UserDAO userDAO = new UserDAO();
	
	public void setUserDAO(UserDAO UserDAO) {
		this.userDAO = UserDAO;
	}

	public UserBO() {}

	/**
	 * Valida User no sistema
	 * 
	 * @param request
	 * @return
	 * @throws NegocioException
	 */

	public boolean validaUser(User User) throws NegocioException {

		User user = null;
		try {
			// valida se o User existe na base

			user = userDAO.validarUser(User);
			if (user == null) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return true;
	}

	/**
	 * Valida os dados de usu�rio na tela de cadastro com erros aglutinados
	 * 
	 * @param User
	 * @return
	 * @throws NegocioException
	 */
	public boolean validaUserA(User User) throws NegocioException {
		boolean isValido = true;
		StringBuilder msg = new StringBuilder();
		msg.append(MensagemContantes.MSG_ERR_USUARIO_DADOS_INVALIDOS.concat("<br/>"));

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		try {
			// valida campos est�o preenchidos corretamente
			// Matricula
			/*
			 * if (User.getMatricula() == null ||
			 * "".equals(User.getMatricula())) { isValido = false;
			 * msg.append(MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?",
			 * "Matricula ").concat("<br/>"));
			 * 
			 * }
			 */
						if (!User.getEmail().matches(EMAIL_PATTERN)) {
				isValido = false;
				msg.append(MensagemContantes.MSG_ERR_EMAIL_INVALIDO.replace("?", "Email ").concat("<br/>"));
			}

		
			// valida se Pessoa esta ok
			if (!isValido) {
				throw new NegocioException(msg.toString());
			}
			//

		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;

	}
	/**
	 * Cadastra User em n�vel de neg�cio, chamando o DAO
	 * 
	 * @param pessoaDTO
	 * @throws NegocioException
	 * @throws SQLException
	 * @throws ParseException
	 */
	
	public int cadastraUser(User User) throws NegocioException, SQLException, ParseException {

		try {
			Integer result = userDAO.cadastrarUser(User);
			return result;			
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

	}
	/**
	 * Lista as pessoas a partir das classes de DAO
	 * 
	 * @return
	 * @throws NegocioException
	 */
	public List<User> listarUser() throws NegocioException {

		List<User> listUser = null;

		try {
			listUser = userDAO.listarUsers();
		} catch (PersistenciaException | SQLException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return listUser;

	}

	/**
	 * Remove User da base
	 * 
	 * @param idUser
	 * @throws NegocioException
	 * @throws SQLException
	 */
	public void removerUser(Integer idUser) throws NegocioException, SQLException {
		try {
				userDAO.removerUser(idUser);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(MensagemContantes.MSG_ERR_REMOVE_USUARIO_EM_PROJETO);
		}
	}

	
	/**
	 * 
	 * @param idUser
	 * @return
	 * @throws NegocioException
	 */
	
	public User buscaUserId(int idUser) throws NegocioException {
		try {
			User User = userDAO.buscaUserId(idUser);

			return User;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	/**
	 * 
	 * @param User
	 * @throws NegocioException
	 */
	public void editaUser(User User) throws NegocioException {
		try {
			userDAO.editaUser(User);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

	}

}
