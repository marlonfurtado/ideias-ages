package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class IdeaCommentDAO {
    private final String GET_ALL_BY_IDEA =
            " SELECT ic.* " +
            " FROM idea_comments ic " +
            " INNER JOIN idea_has_idea_comments ihic ON ihic.idea_comments_id = ic.id " +
            " WHERE ihic.idea_id = ? ";

    private final String INSERT =
        " INSERT INTO idea_comments (comment) " +
        " VALUES (?) ";

    private final String UPDATE =
            " UPDATE idea_comments " +
            " SET comment = ? " +
            " WHERE id = ? ";

    public List<IdeaComment> getAllByIdea(long ideaId) throws PersistenciaException {
        List<IdeaComment> returnModel = new LinkedList<>();
        ResultSet rs = null;

        try {
            Connection connection = ConexaoUtil.getConexao();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_IDEA);
            statement.setLong(1, ideaId);

            rs = statement.executeQuery();

            while (rs.next()) {
                IdeaComment model = new IdeaComment();

                model.setComment(rs.getString("comment"));
                model.setId(rs.getLong("id"));

                returnModel.add(model);
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

        return returnModel;
    }

    public boolean add(IdeaComment model) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, model.getComment());

            return statement.execute();

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }

    public boolean update(IdeaComment model) throws PersistenciaException {
        try {
            Connection connection = ConexaoUtil.getConexao();
            PreparedStatement statement = connection.prepareStatement(UPDATE);

            statement.setString(1, model.getComment());
            statement.setLong(2, model.getId());

            return statement.execute();

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }
    }
}
