package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.PasswordChangeRequest;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;

public class PasswordChangeRequestDAO {

	public PasswordChangeRequestDAO() {
	}
	
	public boolean addPasswordChangeRequest(PasswordChangeRequest passwordChangeRequestDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into password_change_request (request_id, request_date_time, user_cpf)");
			sql.append("values(?, ?, ?)");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, passwordChangeRequestDTO.getRequestId());
			statement.setTimestamp(2, new Timestamp(passwordChangeRequestDTO.getRequestDateTime().getTimeInMillis()));
			statement.setString(3, passwordChangeRequestDTO.getUser().getCpf());
			
			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}


	public PasswordChangeRequest getByToken(String token) throws PersistenciaException {

		PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();

		try {

			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from password_change_request pcr inner join user u " +
							"on pcr.user_cpf = u.cpf " +
							"where pcr.request_id = ? " +
							"and pcr.request_date_time > sysdate() - interval 1 day;");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				passwordChangeRequest.setRequestId(resultset.getString("request_id"));

				Timestamp timestamp= resultset.getTimestamp("request_date_time");
				Calendar cal = Calendar.getInstance();
				cal.setTime(timestamp);
				passwordChangeRequest.setRequestDateTime(cal);

				User user = new User();
				user.setCpf(resultset.getString("cpf"));
				user.setEmail(resultset.getString("email"));
				user.setName(resultset.getString("name"));
				user.setPhone(resultset.getString("phone"));
				user.setRole(resultset.getString("role_name"));
				user.setActive(resultset.getBoolean("active"));
				passwordChangeRequest.setUser(user);
			} else {
				passwordChangeRequest = null;
			}

			return passwordChangeRequest;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}

public void deleteByToken(String token) throws PersistenciaException {

		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from password_change_request where request_id = ?;");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}
	}
}
