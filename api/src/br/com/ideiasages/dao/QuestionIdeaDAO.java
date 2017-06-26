package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.QuestionIdea;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.*;

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
		ResultSet rs = null;
		int rowsAffected = 0;

		try {
			Connection connection = ConexaoUtil.getConexao();
			PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
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

		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return null;
	}

	/**
	 * Consulta o �ltimo questionamento feito pela .
	 * 
	 * @param model Objeto questionamento da id�ia.{@link br.com.ideiasages.model.QuestionIdea} 
	 * @return Objeto questionamento da id�ia adicionado.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exce��o de opera��es realizadas
	 * na base de dados.
	 * 
	 * TODO refatorar e adicionar o inner join - Rodrigo
	 **/
	public QuestionIdea findByIdea(Idea idea) throws PersistenciaException{	
		QuestionIdea questionIdea = new QuestionIdea();

		try {
			Connection connection = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ")
			   .append("FROM idea_has_questions ")
			   .append("WHERE idea_id = ? ")
			   .append("ORDER BY question_id DESC;");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setInt(1, idea.getId());

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				questionIdea.setId(resultset.getInt("question_id"));
				
				sql = new StringBuilder();
				sql.append("SELECT * ")
				   .append("FROM questions ")
				   .append("WHERE id = ? ");
				statement = connection.prepareStatement(sql.toString());
				statement.setInt(1, questionIdea.getId());
				
				resultset = statement.executeQuery();
				
				if(resultset.next()){
					questionIdea.setId(resultset.getInt("id"));
					questionIdea.setQuestion(resultset.getString("question"));
					questionIdea.setAnalyst(userDao.getUserByCPF(resultset.getString("user_cpf")));
					questionIdea.setAnswer(resultset.getString("answer"));
				}
				
			} else {
				questionIdea = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return questionIdea;
	}
}
