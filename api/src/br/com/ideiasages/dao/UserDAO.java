package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Perfil;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;

/**
 * Classe respons�vel pelas opera��es referente ao {@link br.com.ideiasages.model.User} no banco de dados.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 06/06/2017
 *
 **/
public class UserDAO {
	private ArrayList<User> users;

	public UserDAO() {
		users = new ArrayList<>();
	}

	/**
	 * Faz a consulta de um usu�rio na base de dados baseado no UserDTO.
	 *
	 * @param cpf
	 * para a consulta no banco de dados.
	 * @return Retorna um objeto {@link br.com.ideiasages.model.User}.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public User getUserByCPF(String cpf) throws PersistenciaException {
		User user = new User();

		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from user WHERE cpf = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, cpf);

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				user.setCpf(resultset.getString("cpf"));
				user.setEmail(resultset.getString("email"));
				user.setName(resultset.getString("name"));
				user.setPhone(resultset.getString("phone"));
				user.setRole(resultset.getString("role_name"));
				user.setActive(resultset.getBoolean("active"));
			} else {
				user = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return user;
	}

	/**
	 * Faz a consulta de um usu�rio na base de dados baseado no UserDTO.
	 *
	 * @param UserDTO Objeto de {@link br.com.ideiasages.model.User} que far� a transfer�ncia dos dados
	 * para a consulta no banco de dados.
	 * @return Retorna um objeto {@link br.com.ideiasages.model.User}.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public User getUser(User UserDTO) throws PersistenciaException {
		User user = new User();

		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from user WHERE cpf = ? AND password = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, UserDTO.getCpf());
			statement.setString(2, UserDTO.getPassword());

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				user.setCpf(resultset.getString("cpf"));
				user.setEmail(resultset.getString("email"));
				user.setName(resultset.getString("name"));
				user.setPhone(resultset.getString("phone"));
				user.setRole(resultset.getString("role_name"));
				user.setActive(resultset.getBoolean("active"));
			} else {
				user = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return user;
	}

	/**
	 * Consulta a exist�ncia do e-mail informado por par�metro no banco de dados.
	 *
	 * @param email Email que ser� consultado.
	 * @return Verdadeiro caso exista ou falso caso contr�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean emailAlreadyRegistered(String email) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT email from user WHERE email = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, email);

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				return true;
			}

			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}

	/**
	 * Consulta a exist�ncia do e-mail informado por par�metro no banco de dados.
	 *
	 * @param email Email que ser� consultado.
	 * @return Verdadeiro caso exista ou falso caso contr�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean emailAlreadyRegistered(String email, String actualEmail) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT email from user WHERE email = ? AND email != ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, email);
			statement.setString(2, actualEmail);

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				return true;
			}

			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}

	/**
	 * Consulta a exist�ncia do CPF informado por par�metro no banco de dados.
	 *
	 * @param cpf CPF que ser� consultado.
	 * @return Verdadeiro caso exista ou falso caso contr�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean cpfAlreadyRegistered(String cpf) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT cpf from user WHERE cpf = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, cpf);

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				return true;
			}

			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}

	/**
	 * Faz a inser��o de {@link br.com.ideiasages.model.User} informado por par�metro, na base de dados.
	 *
	 * @param userDTO {@link br.com.ideiasages.model.User} Usu�rio que ser� inserido.
	 * @return Verdadeiro em caso de inser��o ou falso caso contr�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean addUser(User userDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO user(cpf,email,name,phone,password,active,role_name)");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?)");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, userDTO.getCpf());
			statement.setString(2, userDTO.getEmail());
			statement.setString(3, userDTO.getName());
			statement.setString(4, userDTO.getPhone());
			statement.setString(5, userDTO.getPassword());
			statement.setBoolean(6, userDTO.isActive());
			statement.setString(7, userDTO.getRole());

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}

	/**
	 * Realiza uma consulta de todos os usu�rios ativos na base de dados.
	 *
	 * @return Lista de todos os usu�rios ativos.
	 * @throws java.sql.SQLException Exce��o de opera��es realizadas
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public ArrayList<User> getActiveUsers() throws PersistenciaException, SQLException {
		Connection connection = null;
		// tentativa de readaptação do users()
		try {
			connection = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from user WHERE active = 1");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				User dto = new User();
				dto.setCpf(resultset.getString("cpf"));
				dto.setEmail(resultset.getString("email"));
				dto.setName(resultset.getString("name"));
				dto.setPhone(resultset.getString("phone"));
				dto.setRole(resultset.getString("role_name"));
				dto.setActive(resultset.getBoolean("active"));

				users.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			connection.close();
		}
		return users;
	}

	/**
	 * Edita um usu�rio na base de dados.
	 *
	 * @param user Objeto usu�rio {@link br.com.ideiasages.model.User}.
	 * @param userChanged Objeto perfil {@link br.com.ideiasages.model.Perfil}.
	 * @return Verdadeiro em caso de sucesso e false caso contr�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean editUser(User user) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET email = ?, name = ?, phone = ?, password = ? WHERE cpf = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getCpf());

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

	}

	/**
	 * Consulta a senha do usu�rio informado por par�metro.
	 *
	 * @param user Objeto usu�rio.
	 * @return A senha consultada, podendo ser nula em caso de inexistir o usu�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public String returnPassword(User user) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT password FROM user WHERE cpf = ?");
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, user.getCpf());
			ResultSet resultset = statement.executeQuery();
			if (resultset.next())
				return resultset.getString("password");
			return null;
		}

		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}

	/**
	 * Muda o status de um usu�rio.
	 *
	 * @param cpf CPF do usu�rio.
	 * @param status Status que ser� alterado no usu�rio.
	 * @return Verdadeiro em caso de sucesso e false caso contr�rio.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean changeStatus(String cpf, boolean status) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET active = ? WHERE cpf = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setBoolean(1, status);
			statement.setString(2, cpf);

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}	

	public ArrayList<User> getUsersByRoles(ArrayList<String> roles) throws PersistenciaException, SQLException {
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from user WHERE role_name IN ('" + String.join("','", roles) + "')");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				User dto = new User();
				dto.setCpf(resultset.getString("cpf"));
				dto.setEmail(resultset.getString("email"));
				dto.setName(resultset.getString("name"));
				dto.setPhone(resultset.getString("phone"));
				dto.setRole(resultset.getString("role_name"));
				dto.setActive(resultset.getBoolean("active"));

				users.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			connection.close();
		}
		return users;
	}
	public User getUserByCpf(User UserDTO) throws PersistenciaException {
		User user = new User();

		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from user WHERE cpf = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, UserDTO.getCpf());

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				user.setCpf(resultset.getString("cpf"));
				user.setEmail(resultset.getString("email"));
				user.setName(resultset.getString("name"));
				user.setPhone(resultset.getString("phone"));
				user.setRole(resultset.getString("role_name"));
				user.setActive(resultset.getBoolean("active"));
			} else {
				user = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return user;
	}
	
	public boolean changePassword(User user) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET password = ? WHERE cpf = ?");
			String cpf = user.getCpf();
			
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			
			statement.setString(1, user.getPassword());
			statement.setString(2, cpf);

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

	}

}
