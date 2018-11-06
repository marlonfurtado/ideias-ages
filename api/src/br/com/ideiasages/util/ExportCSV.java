package br.com.ideiasages.util;

import java.io.BufferedWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;

import br.com.ideiasages.dao.UserDAO;
import br.com.ideiasages.model.User;

/**
 * Classe de exportação de dados para arquivo CSV.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class ExportCSV {

	private static void generateCsvFile(String sFileName) {
		FileWriter writer = null;

		try {
			writer = new FileWriter(sFileName);

			writer.append("DisplayName");
			writer.append(',');
			writer.append("Age");
			writer.append('\n');

			writer.append("MKYONG");
			writer.append(',');
			writer.append("26");
			writer.append('\n');

			writer.append("YOUR NAME");
			writer.append(',');
			writer.append("29");
			writer.append('\n');

			writer.flush();
		} catch (IOException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
		        } catch (IOException e) {}		
			}
		}
	}

	public static void exportData(String filename) throws ClassNotFoundException, SQLException {
		Connection conexao = null;
		Statement stmt = null;
		String query;

		try {
			conexao = ConexaoUtil.getConexao();

			stmt = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			query = "SELECT id_usuario,nome,matricula into OUTFILE  '" + filename + "' FIELDS TERMINATED BY ',' FROM tb_usuario t";
			stmt.executeQuery(query);

		} catch (Exception e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
		} finally {
		    DbUtils.closeQuietly(stmt);
		    DbUtils.closeQuietly(conexao);
		}
	}

	public static void writeToCSV(List<Map> objectList) {
		String CSV_SEPARATOR = ",";
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("c:\\CAWT\\results.csv");
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);

			for (Map objectDetails : objectList) {
				StringBuffer oneLine = new StringBuffer();
				Iterator it = objectDetails.values().iterator();

				while (it.hasNext()) {
					Object value = it.next();

					if(value !=null){
						oneLine.append(value.toString());
					}

					if (it.hasNext()) {
						oneLine.append(CSV_SEPARATOR);
					}
				}
				bw.write(oneLine.toString());
				bw.newLine();
			}
			bw.flush();
		}
		catch (UnsupportedEncodingException e) {}
		catch (FileNotFoundException e) {}
		catch (IOException e) {}
		finally {
		    try { osw.close(); } catch (IOException e) {}
		    try { fos.close(); } catch (IOException e) {}
		    try { bw.close(); } catch (IOException e) {}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {

			List<Map> listMap = new ArrayList<>();
			Map listaUsuarios = (Map) new ArrayList<User>();
			UserDAO usuarioDAO = new UserDAO();
			listaUsuarios = (Map) usuarioDAO.getActiveUsers();

			listMap.add(listaUsuarios);		

			writeToCSV(listMap);

			System.out.println("******");

		} catch (Exception e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "an exception was thrown", e);
		}

	}
}
