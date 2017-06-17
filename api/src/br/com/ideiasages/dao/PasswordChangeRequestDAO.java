package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.PasswordChangeRequest;
import br.com.ideiasages.util.ConexaoUtil;

public class PasswordChangeRequestDAO {

	public PasswordChangeRequestDAO() {
	}
	
	public boolean addPasswordChangeRequest(PasswordChangeRequest passwordChangeRequestDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO password_change_request(request_id, request_date_time, user_cpf) ");
			sql.append("VALUES(?, ?, ?)");

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

}
