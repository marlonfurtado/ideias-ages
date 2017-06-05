package br.com.ideiasages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;

public class IdeaDAO {
    public Idea getIdea(int id) throws PersistenciaException {
        Idea idea = new Idea();

        try {
            Connection connection = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM idea WHERE id = ?");

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setInt(1, id);

            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                idea.setDescription(resultset.getString("description"));
                idea.setGoal(resultset.getString("goal"));
                idea.setId(resultset.getInt("id"));
                idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name")));
                idea.setTags(resultset.getString("tags"));
                idea.setTitle(resultset.getString("title"));
                idea.setUser(new User(resultset.getString("user_cpf")));
            } else {
                idea = null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

        return idea;
    }

    public boolean addIdeia(Idea newIdea) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO idea(title, description, status_name, tags, user_cpf, goal, creationDate)");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
            java.util.Date utilDate = new java.util.Date();
			java.sql.Date creationDate = new java.sql.Date(utilDate.getTime());
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, newIdea.getTitle());
            statement.setString(2, newIdea.getDescription());
            statement.setString(3, newIdea.getStatus().name());
            statement.setString(4, newIdea.getTags());
            statement.setString(5, newIdea.getUser().getCpf());
            statement.setString(6, newIdea.getGoal());
            statement.setDate(7, creationDate);
            

            
            return statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    public boolean updateIdea(Idea newIdea) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE idea SET title = ?, description = ?, tags = ?, goal = ?, status_name = ? WHERE id = ?");

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, newIdea.getTitle());
            statement.setString(2, newIdea.getDescription());
            statement.setString(3, newIdea.getTags());
            statement.setString(4, newIdea.getGoal());
            statement.setString(5, newIdea.getStatus().name());
            statement.setInt(6, newIdea.getId());
            

            return statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    public boolean updateStatus(Idea newIdea) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE idea SET status_name = ? WHERE id = ?");

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            statement.setString(1, newIdea.getStatus().name());
            statement.setInt(2, newIdea.getId());

            return statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
}
