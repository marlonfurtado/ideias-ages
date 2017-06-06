package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Perfil;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;

/**
 * Classe responsável pelas operações referente ao {@link br.com.ideiasages.model.User} no banco de dados.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 06/06/2017
 * 
 **/
public class UserDAO {
	private ArrayList<User> users;

	public UserDAO() {
		users = new ArrayList<>();
	}

	/**
	 * Faz a consulta de um usuário na base de dados baseado no UserDTO.
	 * 
	 * @param UserDTO Objeto de {@link br.com.ideiasages.model.User} que fará a transferência dos dados
	 * para a consulta no banco de dados.
	 * @return Retorna um objeto {@link br.com.ideiasages.model.User}.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
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
	 * Consulta a existência do e-mail informado por parâmetro no banco de dados.
	 * 
	 * @param email Email que será consultado.
	 * @return Verdadeiro caso exista ou falso caso contrário.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
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
	 * Consulta a existência do CPF informado por parâmetro no banco de dados.
	 * 
	 * @param cpf CPF que será consultado.
	 * @return Verdadeiro caso exista ou falso caso contrário.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
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
	 * Faz a inserção de {@link br.com.ideiasages.model.User} informado por parâmetro, na base de dados.
	 * 
	 * @param userDTO {@link br.com.ideiasages.model.User} Usuário que será inserido.
	 * @return Verdadeiro em caso de inserção ou falso caso contrário.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
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
	 * Realiza uma consulta de todos os usuários ativos na base de dados.
	 * 
	 * @return Lista de todos os usuários ativos.
	 * @throws {@link java.util.SQLException} Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public ArrayList<User> getActiveUsers() throws PersistenciaException, SQLException {
		Connection connection = null;
		// tentativa de readaptaÃ§Ã£o do users()
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
	 * Realiza uma consulta de todos os Analistas existentes na base de dados.
	 * 
	 * @return Lista de todos os Analistas existentes.
	 * @throws {@link java.util.SQLException} Exceção de operações realizadas
	 * na base de dados.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public ArrayList<User> getAnalyst() throws PersistenciaException, SQLException {
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user WHERE role_name='analyst'");

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
	 * Realiza uma consulta de todos os Idealizadores existentes na base de dados.
	 * 
	 * @return Lista de todos os Idealizadores existentes.
	 * @throws {@link java.util.SQLException} Exceção de operações realizadas
	 * na base de dados.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public ArrayList<User> getIdealizer() throws PersistenciaException, SQLException {
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * from user WHERE role_name='idealizer'");

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
	 * Edita um usuário na base de dados.
	 * 
	 * @return Verdadeiro em caso de sucesso e false caso contrário.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public boolean editUser(User user, Perfil userChanged) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET email = ?, name = ?, phone = ?, password = ? WHERE cpf = ?");
			String cpf = user.getCpf();

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			if(userChanged.getEmail().equals(""))
				statement.setString(1, user.getEmail());
			else
				statement.setString(1, userChanged.getEmail());
			if(userChanged.getName().equals(""))
				statement.setString(2, user.getName());
			else
				statement.setString(2, userChanged.getName());
			if(userChanged.getPhone().equals(""))
				statement.setString(3, user.getPhone());
			else
				statement.setString(3, userChanged.getPhone());
			if(userChanged.getPassword() == null)
				statement.setString(4, this.returnPassword(user));
			else
				statement.setString(4, userChanged.getPassword());
			statement.setString(5, cpf);

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

	}

	/**
	 * Consulta a senha do usuário informado por parâmetro.
	 * 
	 * @return A senha consultada, podendo ser nula em caso de inexistir o usuário.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
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
	 * Muda o status de um usuário.
	 * 
	 * @param cpf CPF do usuário.
	 * @param status Status que será alterado no usuário.
	 * @return Verdadeiro em caso de sucesso e false caso contrário.
	 * @throws {@link br.com.ideiasages.exception.PersistenciaException} Exceção de operações realizadas
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

}
