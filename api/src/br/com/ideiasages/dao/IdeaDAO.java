package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IdeaDAO {
    public boolean addIdeia(Idea newIdea) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO idea(title, description, status_name, tags, user_cpf, goal)");
            sql.append("VALUES(?, ?, ?, ?, ?, ?)");

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, newIdea.getTitle());
            statement.setString(2, newIdea.getDescription());
            statement.setString(3, newIdea.getStatus().name());
            statement.setString(4, newIdea.getTags());
            statement.setString(5, newIdea.getUser().getCpf());
            statement.setString(6, newIdea.getGoal());

            return statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
}
