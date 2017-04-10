package br.com.ideiasages.exception;

/**
 * Exce��es de neg�cio
 * 
 * @author C�ssio Trindade
 *
 */
public class NegocioException extends Exception{

	private static final long serialVersionUID = 1L;

	public NegocioException(Exception e) {
		super(e.getMessage());
	}

	public NegocioException(String msg) {
		super(msg);
	}

}
