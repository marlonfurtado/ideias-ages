package br.com.ideiasages.dao;

import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.dbutils.DbUtils;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.model.IdeaStatus;
import br.com.ideiasages.model.QuestionIdea;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;
import br.com.ideiasages.dao.UserDAO;

/**
 * Classe respons�vel pelas opera��es referente ao {@link br.com.ideiasages.model.Idea} no banco de dados.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 08/06/2017
 *
 **/
public class IdeaDAO {
	private final String ADD_COMMENT =
			" INSERT INTO idea_has_idea_comments " +
					" VALUES (?, ?) ";

	private final String ADD_QUESTION =
			" INSERT INTO idea_has_questions " +
					" VALUES (?, ?) ";
	UserDAO userDao = new UserDAO();
	/**
	 * Faz a consulta de uma id�ia atrav�s do seu ID.
	 *
	 * @param id ID da id�ia.
	 * @return Id�ia encontrada.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 **/
	public Idea getIdea(int id) throws PersistenciaException {
		Idea    idea = new Idea();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.*, u.name as analyst FROM idea i LEFT JOIN user u ON u.cpf = analyst_cpf WHERE id = ?");

			statement = connection.prepareStatement(sql.toString());
			statement.setInt(1, id);

			resultset = statement.executeQuery();
			if (resultset.next()) {
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name")));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setAnalyst(new User(resultset.getString("analyst_cpf"), resultset.getString("analyst")));
				idea.setCreationDate(resultset.getDate("creationDate"));
			} else {
				idea = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return idea;
	}

	/**
	 * Adiciona um coment�rio � id�ia informada por par�metro na base de dados.
	 *
	 * @param idea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @param comment Objeto coment�rio da id�ia.{@link br.com.ideiasages.model.IdeaComment}
	 * @return Verdadeiro em caso de sucesso na altera��o na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean addComment(Idea idea, IdeaComment comment) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(ADD_COMMENT);

			statement.setInt(1, idea.getId());
			statement.setLong(2, comment.getId());

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
	}


	/**
	 * Adiciona um questionamento � id�ia informada por par�metro na base de dados.
	 *
	 * @param idea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @param question Objeto de questionamento da id�ia.{@link br.com.ideiasages.model.QuestionIdea}
	 * @return Verdadeiro em caso de sucesso da inser��o na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean addQuestion(Idea idea, QuestionIdea question) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(ADD_QUESTION);

			statement.setInt(1, idea.getId());
			statement.setInt(2, question.getId());

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
	}

	/**
	 * Adiciona uma id�ia � na base de dados.
	 *
	 * @param newIdea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro em caso de sucesso na inclus�o na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public int addIdeia(Idea newIdea) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO idea(title, description, status_name, tags, user_cpf, goal, creationDate, analyst_cpf)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, NOW(), NULL)");
			statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newIdea.getTitle());
			statement.setString(2, newIdea.getDescription());
			statement.setString(3, newIdea.getStatus().name());
			statement.setString(4, newIdea.getTags());
			statement.setString(5, newIdea.getUser().getCpf());
			statement.setString(6, newIdea.getGoal());

			statement.executeUpdate();

			generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(generatedKeys);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return 0;
	}

	/**
	 * Altera uma id�ia existente na base de dados.
	 *
	 * @param newIdea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro em caso de sucesso na altera��o na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public int updateIdea(Idea newIdea) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idea SET title = ?, description = ?, tags = ?, goal = ?, status_name = ? WHERE id = ?");

			statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newIdea.getTitle());
			statement.setString(2, newIdea.getDescription());
			statement.setString(3, newIdea.getTags());
			statement.setString(4, newIdea.getGoal());
			statement.setString(5, newIdea.getStatus().name());
			statement.setInt(6, newIdea.getId());

			statement.executeUpdate();

			generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(generatedKeys);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return 0;
	}

	/**
	 * Altera o status de uma id�ia existente na base de dados.
	 *
	 * @param newIdea Objeto id�ia.{@link br.com.ideiasages.model.Idea}
	 * @return Verdadeiro em caso de sucesso na altera��o na base de dados.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 *
	 **/
	public boolean updateStatus(Idea newIdea) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idea SET status_name = ? WHERE id = ?");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, newIdea.getStatus().name());
			statement.setInt(2, newIdea.getId());

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
	}

	public ArrayList<Idea> getIdeas() throws PersistenciaException, ClassNotFoundException, SQLException {
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.*, u.name as analyst FROM idea i LEFT JOIN user u ON u.cpf = analyst_cpf WHERE status_name != 'DRAFT' ");

			statement = connection.prepareStatement(sql.toString());
			resultset = statement.executeQuery();

			while(resultset.next()){
				Idea idea = new Idea();
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name").toUpperCase()));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setAnalyst(new User(resultset.getString("analyst_cpf"), resultset.getString("analyst")));
				idea.setCreationDate(resultset.getDate("creationDate"));
				ideas.add(idea);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
		return ideas;
	}

	public ArrayList<Idea> getActiveIdeas() throws PersistenciaException, ClassNotFoundException, SQLException {
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.*, u.name as analyst FROM idea i LEFT JOIN user u ON u.cpf = analyst_cpf WHERE status_name NOT IN('REJECTED', 'DRAFT') ");
			
			statement = connection.prepareStatement(sql.toString());
			resultset = statement.executeQuery();

			while(resultset.next()){
				Idea idea = new Idea();
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name").toUpperCase()));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setAnalyst(new User(resultset.getString("analyst_cpf"), resultset.getString("analyst")));
				idea.setCreationDate(resultset.getDate("creationDate"));
				ideas.add(idea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
		return ideas;
	}

	public ArrayList<Idea> getIdeas(User user) throws PersistenciaException, ClassNotFoundException, SQLException {
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.*, u.name as analyst FROM idea i LEFT JOIN user u ON u.cpf = analyst_cpf WHERE user_cpf = ?");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, user.getCpf());
			resultset = statement.executeQuery();

			while(resultset.next()){
				Idea idea = new Idea();
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name").toUpperCase()));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setAnalyst(userDao.getUserByCPF(resultset.getString("analyst_cpf")));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setCreationDate(resultset.getDate("creationDate"));
				ideas.add(idea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
		return ideas;
	}

	public ArrayList<Idea> getAllIdeas() throws ClassNotFoundException, SQLException, PersistenciaException {
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM idea");

			statement = connection.prepareStatement(sql.toString());
			resultset = statement.executeQuery();

			while(resultset.next()){
				Idea idea = new Idea();
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name").toUpperCase()));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setAnalyst(userDao.getUserByCPF(resultset.getString("analyst_cpf")));
				idea.setCreationDate(resultset.getDate("creationDate"));
				ideas.add(idea);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
		return ideas;
	}

	public Idea getIdeaByAnalyst(int id, String cpf) throws PersistenciaException {
		Idea    idea = new Idea();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		
		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT i.*, u.name as analyst FROM idea i LEFT JOIN user u ON u.cpf = analyst_cpf WHERE analyst_cpf = ? AND id = ?");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, cpf);
			statement.setInt(2, id);

			resultset = statement.executeQuery();
			if (resultset.next()) {
				idea.setDescription(resultset.getString("description"));
				idea.setGoal(resultset.getString("goal"));
				idea.setId(resultset.getInt("id"));
				idea.setStatus(IdeaStatus.valueOf(resultset.getString("status_name")));
				idea.setTags(resultset.getString("tags"));
				idea.setTitle(resultset.getString("title"));
				idea.setAnalyst(new User(resultset.getString("analyst_cpf"), resultset.getString("analyst")));
				idea.setUser(new User(resultset.getString("user_cpf")));
				idea.setCreationDate(resultset.getDate("creationDate"));
			} else {
				idea = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return idea;
	}

	public boolean linkIdeaWithAnalyst(Idea idea, User analyst) throws PersistenciaException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE idea SET analyst_cpf = ? WHERE id = ?");

			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, analyst.getCpf());
			statement.setInt(2, idea.getId());

			return statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
	}
}
