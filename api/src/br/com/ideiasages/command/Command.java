package br.com.ideiasages.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import br.com.ideiasages.exception.NegocioException;

/**
 * Interface que implementa o Designer Partner Command (GoF)
 * 
 * @author C�ssio Trindade
 *
 */
public interface Command {
	/**
	 * execu��o de comando dos request das telas.
	 * @param request
	 * @return
	 * @throws SQLException 
	 * @throws NegocioException
	 */

	public String execute(HttpServletRequest request) throws SQLException, NegocioException ;
}
