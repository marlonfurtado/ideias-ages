package br.com.ideiasages.dao;

import java.sql.*;
import java.util.ArrayList;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;

/**
 * Classe responsável pelas operações referente ao {@link br.com.ideiasages.model.Idea} no banco de dados.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 08/06/2017
 *
 **/
public class IdeaDAO {
    private final String ADD_COMMENT =
        " INSERT INTO idea_has_idea_comments " +
        " VALUES (?, ?) ";

	/**
	 * Faz a consulta de uma idéia através do seu ID.
	 *
	 * @param id ID da idéia.
	 * @return Idéia encontrada.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 **/
    public Idea getIdea(int id) throws PersistenciaException {
        Idea    idea = new Idea();

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
                idea.setCreationDate(resultset.getDate("creationDate"));
            } else {
                idea = null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

		return idea;
	}

    /**
	 * Adiciona um comentário à idéia informada por parâmetro na base de dados.
	 *
	 * @param idea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @param comment Objeto comentário da idéia.{@link br.com.ideiasages.model.IdeaComment}
	 * @return Verdadeiro em caso de sucesso na alteração na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 *
	 **/
    public boolean addComment(Idea idea, IdeaComment comment) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            PreparedStatement statement = connection.prepareStatement(ADD_COMMENT);

            statement.setInt(1, idea.getId());
            statement.setLong(2, comment.getId());

            return statement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    /**
 	 * Adiciona uma idéia à na base de dados.
 	 *
 	 * @param newIdea Objeto idéia.{@link br.com.ideiasages.model.Idea}
 	 * @return Verdadeiro em caso de sucesso na inclusão na base de dados.
 	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
 	 * na base de dados.
 	 *
 	 **/
    public int addIdeia(Idea newIdea) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO idea(title, description, status_name, tags, user_cpf, goal, creationDate)");
            sql.append(" VALUES(?, ?, ?, ?, ?, ?, NOW())");
            PreparedStatement statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newIdea.getTitle());
            statement.setString(2, newIdea.getDescription());
            statement.setString(3, newIdea.getStatus().name());
            statement.setString(4, newIdea.getTags());
            statement.setString(5, newIdea.getUser().getCpf());
            statement.setString(6, newIdea.getGoal());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

        return 0;
    }

	/**
	 * Altera uma idéia existente na base de dados.
	 *
	 * @param newIdea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro em caso de sucesso na alteração na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 *
	 **/
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

	/**
	 * Altera o status de uma idéia existente na base de dados.
	 *
	 * @param newIdea Objeto idéia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro em caso de sucesso na alteração na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 *
	 **/
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

	public ArrayList<Idea> getIdeas() throws PersistenciaException, ClassNotFoundException, SQLException {
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		Connection connection = ConexaoUtil.getConexao();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idea");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		ResultSet resultset = statement.executeQuery();
		try {
			while(resultset.next()){
				Idea idea = new Idea();
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name").toUpperCase()));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setCreationDate(resultset.getDate("creationDate"));
				ideas.add(idea);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ideas;
	}

	public ArrayList<Idea> getIdeas(User user) throws PersistenciaException, ClassNotFoundException, SQLException {
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		Connection connection = ConexaoUtil.getConexao();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM idea WHERE user_cpf = ? ");
		PreparedStatement statement = connection.prepareStatement(sql.toString());
		ResultSet resultset = statement.executeQuery();
		statement.setString(1, user.getCpf());
		try {
			while(resultset.next()){
				Idea idea = new Idea();
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name").toUpperCase()));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setCreationDate(resultset.getDate("creationDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ideas;
	}
}
