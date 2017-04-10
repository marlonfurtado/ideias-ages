package br.com.ideiasages.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.ideiasages.exception.PersistenciaException;
import br.com.ideiasages.model.User;
import br.com.ideiasages.util.ConexaoUtil;
import br.com.ideiasages.util.MensagemContantes;

/**
 *
 * @author Cassio Trindade
 *
 */
public class UserDAO {

	private ArrayList<User> listarUsers;

	public UserDAO() {
		listarUsers = new ArrayList<>();
	}

	/**
	 * Autentica o User
	 *
	 * @author cassio trindade
	 * @param UserDTO
	 * @return
	 * @throws PersistenciaException
	 */

	public User validarUser(User UserDTO) throws PersistenciaException {
		User user = new User();
		try {

			Connection conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from TB_USER ");
			sql.append("where EMAIL = ? and SENHA = ?");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, UserDTO.getEmail());
			statement.setString(2, UserDTO.getPassword());

			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				user.setIdUser(resultset.getInt("ID_USER"));
				user.setEmail(resultset.getString("EMAIL"));
				user.setPassword(resultset.getString("SENHA"));
			} else
				user = null;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(e);
		}

		return user;
	}

	/**
	 * Lista os Users da basee
	 *
	 * @return
	 * @throws PersistenciaException
	 * @throws SQLException
	 */
	public ArrayList<User> listarUsers() throws PersistenciaException, SQLException {
		Connection conexao = null;
		// tentativa de readaptação do listarUsers()
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("ID_USER, ");
			sql.append("SENHA, ");
			sql.append("EMAIL ");
			sql.append("from TB_USER ;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				User dto = new User();
				dto.setIdUser(resultset.getInt("ID_User"));
				dto.setEmail(resultset.getString("EMAIL"));
				dto.setPassword(resultset.getString("SENHA"));

				listarUsers.add(dto);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			conexao.close();
		}
		return listarUsers;
	}

	public int cadastrarUser(User User) throws PersistenciaException, SQLException, ParseException {
		Connection conexao = null;

		try {
			Integer idUser = null;

			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into TB_User ( senha, email)");
			sql.append("values (?, ? )");

			/* converte a data para data Juliana, data que o banco reconhece;
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date dataCadastro = new java.sql.Date(utilDate.getTime());
			 */

			// Cadastra a pessoa e gera e busca id gerado
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, User.getPassword());
			statement.setString(2, User.getEmail());
			//statement.setDate(9, dataCadastro);

			statement.executeUpdate();

			ResultSet resultset = statement.getGeneratedKeys();
			if (resultset.first()) {
				idUser = resultset.getInt(1);

			}
			return idUser;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistenciaException(MensagemContantes.MSG_ERR_USUARIO_JA_EXISTENTE.replace("?", User.getEmail()));

		} finally {
			conexao.close();
		}
	}

	/**
	 * M?todo de remo??o de um usu?rio a partir do seu id.
	 *
	 * @param idPessoa
	 * @throws PersistenciaException
	 */
	public boolean removerUser(Integer idUser) throws PersistenciaException {
		boolean removidoOK = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT ID_TIPO_User FROM TB_User WHERE ID_User = ?
			// ")
			sql.append("delete from TB_User where id_User= ? ");
			// sql.append("DELETE FROM TB_TIPO_User WHERE
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idUser);

			removidoOK = statement.execute();

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return removidoOK;
	}


	public User buscaUserId(int idUser) throws PersistenciaException, SQLException {
		// adicionar informações de tipo de User?
		User User = new User();

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select id_User, senha, email ");
			sql.append("from TB_User ");
			sql.append("where id_User = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idUser);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				User.setIdUser(resultset.getInt("ID_USER"));
				User.setEmail(resultset.getString("EMAIL"));
				User.setPassword(resultset.getString("SENHA"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return User;

	}


	public boolean editaUser(User user) throws PersistenciaException {
		boolean okei = false;
		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();
			StringBuilder sql = new StringBuilder();
			int id = user.getIdUser();

			sql.append("update TB_User set senha = ?,  email = ?   where id_User = " + id + ";");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());

			statement.setString(1, user.getPassword());
			statement.setString(2, user.getEmail());
			okei = statement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return okei;
	}

	public br.com.ideiasages.model.User buscaUserEmail(String email) throws PersistenciaException {
		User User = new User();

		Connection conexao = null;
		try {
			conexao = ConexaoUtil.getConexao();

			StringBuilder sql = new StringBuilder();
			sql.append("select ");
			sql.append("id_User, senha, email ");
			sql.append("from tb_User ");
			sql.append("where email = ?;");

			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setString(1, email);
			ResultSet resultset = statement.executeQuery();

			while (resultset.next()) {
				User.setIdUser(resultset.getInt("ID_USER"));
				User.setEmail(resultset.getString("EMAIL"));
				User.setPassword(resultset.getString("SENHA"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return User;
	}
}

	
	
	

	
