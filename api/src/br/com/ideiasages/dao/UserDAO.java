package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;

public class UserDAO {
	private ArrayList<User> users;

	public UserDAO() {
		users = new ArrayList<>();
	}

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


	public boolean addUser(User userDTO) throws PersistenciaException{
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
	
	public boolean editUser(User userDTO, User userChanged) throws PersistenciaException { 
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE user SET email = ?, name = ?, phone = ?, password = ? WHERE cpf = ?");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, userChanged.getEmail());
			statement.setString(2, userChanged.getName());
			statement.setString(3, userChanged.getPhone());
			statement.setString(4, userChanged.getPassword());
			statement.setString(5, userDTO.getCpf());


			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
		
	}

}

	
	
	

	
