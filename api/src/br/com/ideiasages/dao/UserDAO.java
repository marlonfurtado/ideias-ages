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
}

	
	
	

	
