package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.Idea;
import br.com.ideiasages.model.QuestionIdea;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
	 * @return Objeto questionamento da ideia adicionado.
	 * @throws br.com.ideiasages.exception.PersistenciaException Excecao de operacoes realizadas na base de dados.
	 **/
	public List<QuestionIdea> findByIdea(Idea idea) throws PersistenciaException{
		List<QuestionIdea> list = new LinkedList<>();
		QuestionIdea question = null;

		try {
			Connection connection = ConexaoUtil.getConexao();
			PreparedStatement statement = connection.prepareStatement(GET_BY_IDEA);
			statement.setInt(1, idea.getId());

			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				question = new QuestionIdea();
				question.setId(resultset.getInt("id"));
				question.setQuestion(resultset.getString("question"));
				question.setAnalyst(userDao.getUserByCPF(resultset.getString("user_cpf")));
				question.setAnswer(resultset.getString("answer"));

				list.add(question);
			}
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return list;
	}
}
