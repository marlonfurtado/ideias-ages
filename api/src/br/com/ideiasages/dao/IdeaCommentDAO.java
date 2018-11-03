package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
/**
 * Classe responsável pelas operações referente a {@link br.com.ideiasages.model.IdeaComment} no banco de dados.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 08/06/2017
 **/
public class IdeaCommentDAO {
	private final String GET_ALL_BY_IDEA =
			" SELECT ic.* " +
					" FROM idea_comments ic " +
					" INNER JOIN idea_has_idea_comments ihic ON ihic.idea_comments_id = ic.id " +
					" WHERE ihic.idea_id = ? ";

	private final String GET =
			" SELECT ic.* " +
					" FROM idea_comments ic " +
					" WHERE ic.id = ? ";

	private final String INSERT =
			" INSERT INTO idea_comments (comment) " +
					" VALUES (?) ";

	private final String UPDATE =
			" UPDATE idea_comments " +
					" SET comment = ? " +
					" WHERE id = ? ";

	/**
	 * Faz a consulta de comentários da idéia através de seu ID.
	 * 
	 * @param ideaId ID da idéia.
	 * @return Lista de comentários da idéia encontrada.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public List<IdeaComment> getAllByIdea(long ideaId) throws PersistenciaException {
		List<IdeaComment> returnModel = new LinkedList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(GET_ALL_BY_IDEA);
			statement.setLong(1, ideaId);

			rs = statement.executeQuery();

			while (rs.next()) {
				IdeaComment model = new IdeaComment();

				model.setComment(rs.getString("comment"));
				model.setId(rs.getLong("id"));

				returnModel.add(model);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(rs);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return returnModel;
	}

	/**
	 * Faz a consulta de um comentário através do seu ID.
	 * 
	 * @param id ID do comentário.
	 * @return Comentário encontrado.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public IdeaComment get(long id) throws PersistenciaException {
		IdeaComment model = new IdeaComment();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(GET);
			statement.setLong(1, id);

			rs = statement.executeQuery();

			if (rs.next()) {
				model.setComment(rs.getString("comment"));
				model.setId(rs.getLong("id"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(rs);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return model;
	}

	/**
	 * Adiciona um comentário na base de dados.
	 * 
	 * @param model Objeto comentário da idéia.{@link br.com.ideiasages.model.IdeaComment} 
	 * @return Objeto comentário da idéia adicionado.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public IdeaComment add(IdeaComment model) throws PersistenciaException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Connection connection = null;
		int rowsAffected = 0;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, model.getComment());

			//insert into database
			rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				//in case it worked, try to get the INSERTED id
				rs = statement.getGeneratedKeys();

				//get the inserted keys
				if (rs.next())
					//return the entity
					return get(rs.getLong(1));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		} finally {
		    DbUtils.closeQuietly(rs);
		    DbUtils.closeQuietly(statement);
		    DbUtils.closeQuietly(connection);
		}

		return null;
	}

	/**
	 * Altera um comentário existente na base de dados.
	 * 
	 * @param model Objeto comentário da idéia.{@link br.com.ideiasages.model.IdeaComment} 
	 * @return Verdadeiro em caso de sucesso na alteração.
	 * @throws br.com.ideiasages.exception.PersistenciaException Exceção de operações realizadas
	 * na base de dados.
	 * 
	 **/
	public boolean update(IdeaComment model) throws PersistenciaException {
		PreparedStatement statement = null;
		Connection connection = null;

		try {
			connection = ConexaoUtil.getConexao();
			statement = connection.prepareStatement(UPDATE);

			statement.setString(1, model.getComment());
			statement.setLong(2, model.getId());

			//Verificar se de fato o execute retorna o número de linhas
			// afetados. Acho que só o executeUpdate faz isso. -- Rodrigo.
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
