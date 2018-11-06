package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.QuestionIdea;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

/**
 * Classe respons�vel pelas opera��es referente a {@link br.com.ideiasages.model.QuestionIdea} no banco de dados.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 19/06/2017
 **/
public class QuestionIdeaDAO {
	private UserDAO userDao;
	
	private final String INSERT =
		" INSERT INTO questions (question, user_cpf, answer) " +
		" VALUES (?, ?, ?) ";

	private final String GET_BY_IDEA =
		" SELECT q.* " +
		" FROM questions q " +
		" INNER JOIN idea_has_questions iq ON iq.question_id = q.id " +
		" WHERE iq.idea_id = ? ";

	private final String GET_NOT_ANSWERED_BY_IDEA =
			" SELECT q.* " +
			" FROM questions q " +
			" INNER JOIN idea_has_questions iq ON iq.question_id = q.id " +
			" WHERE iq.idea_id = ? " +
			" AND q.answer IS NULL ";

	private final String UPDATE_ANSWER =
			" UPDATE questions " +
			" SET answer = ? " +
			" WHERE id = ? ";

	
	public QuestionIdeaDAO(){
		userDao = new UserDAO();
	}
	
	/**
	 * Adiciona um questionamento de uma id�ia na base de dados.
	 * 
	 * @param model Objeto questionamento da id�ia.{@link br.com.ideiasages.model.QuestionIdea} 
	 * @return Objeto questionamento da id�ia adicionado.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 * 
	 **/
	public QuestionIdea add(QuestionIdea model) throws PersistenciaException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Connection connection = null;
		int rowsAffected = 0;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, model.getQuestion());
			statement.setString(2, model.getAnalyst().getCpf());
			statement.setString(3, model.getAnswer());

			rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				rs = statement.getGeneratedKeys();

				if (rs.next()){
					model.setId(rs.getInt(1));
					return model;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(rs);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return null;
	}

	public void saveAnswer(QuestionIdea model) throws PersistenciaException {
		PreparedStatement statement = null;
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(UPDATE_ANSWER);
			statement.setString(1, model.getAnswer());
			statement.setInt(2, model.getId());

			statement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}
	}

	/**
	 * Consulta o �ltimo questionamento feito pela .
	 * 
	 * @return Objeto questionamento da ideia adicionado.
	 * @throws br.com.ideiasages.exception.PersistenciaException Excecao de operacoes realizadas na base de dados.
	 **/
	public List<QuestionIdea> findByIdea(Idea idea) throws PersistenciaException {
		List<QuestionIdea> list = new LinkedList<>();
		QuestionIdea question = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(GET_BY_IDEA);
			statement.setInt(1, idea.getId());

			resultset = statement.executeQuery();
			while (resultset.next()) {
				question = createFromResultSet(resultset);

				list.add(question);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return list;
	}

	public QuestionIdea getNotAnsweredByIdea(Idea idea) throws PersistenciaException {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(GET_NOT_ANSWERED_BY_IDEA);
			statement.setInt(1, idea.getId());

			resultset = statement.executeQuery();

			if (resultset.next()) {
				return createFromResultSet(resultset);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(resultset);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return null;
	}

	private QuestionIdea createFromResultSet(ResultSet rs) throws SQLException, PersistenciaException {
		QuestionIdea entity = new QuestionIdea();

		entity.setId(rs.getInt("id"));
		entity.setQuestion(rs.getString("question"));
		entity.setAnalyst(userDao.getUserByCPF(rs.getString("user_cpf")));
		entity.setAnswer(rs.getString("answer"));

		return entity;
	}
}
