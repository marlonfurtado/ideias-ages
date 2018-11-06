package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.dbutils.DbUtils;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.PasswordChangeRequest;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PasswordChangeRequestDAO {

	public PasswordChangeRequestDAO() {
	}
	
	public boolean addPasswordChangeRequest(PasswordChangeRequest passwordChangeRequestDTO) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into password_change_request (request_id, request_date_time, user_cpf)");
			sql.append("values(?, ?, ?)");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, passwordChangeRequestDTO.getRequestId());
			statement.setTimestamp(2, new Timestamp(passwordChangeRequestDTO.getRequestDateTime().getTimeInMillis()));
			statement.setString(3, passwordChangeRequestDTO.getUser().getCpf());
			
			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
}


	public PasswordChangeRequest getByToken(String token) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from password_change_request pcr inner join user u " +
							"on pcr.user_cpf = u.cpf " +
							"where pcr.request_id = ? " +
							"and pcr.request_date_time > sysdate() - interval 1 day;");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);

			resultset = statement.executeQuery();
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
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
}

	public void deleteByToken(String token) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from password_change_request where request_id = ?;");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, token);

		} catch (ClassNotFoundException | SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
	}
}
