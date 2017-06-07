package br.com.ideiasages.dao;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.IdeaComment;
import br.com.ideiasages.util.ConexaoUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

    public IdeaComment get(long id) throws PersistenciaException {
        IdeaComment model = new IdeaComment();
        ResultSet rs = null;

        try {
            Connection connection = ConexaoUtil.getConexao();
            PreparedStatement statement = connection.prepareStatement(GET);
            statement.setLong(1, id);

            rs = statement.executeQuery();

            if (rs.next()) {
                model.setComment(rs.getString("comment"));
                model.setId(rs.getLong("id"));
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

        return model;
    }

    public IdeaComment add(IdeaComment model) throws PersistenciaException {
        ResultSet rs = null;
        int rowsAffected = 0;

        try {
            Connection connection = ConexaoUtil.getConexao();
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
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

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException(e);
        }

        return null;
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
