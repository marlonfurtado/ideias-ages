package br.com.ideiasages.validator;


import java.util.Map;

import br.com.ideiasages.exception.ValidationException;

/**
 * Responsovel por validar todo os tipos de valores do sistema
 * 
 *
 * 
 */
public interface Validator {
	
	/**
	 * Metodo efetivo de validacao que recebe o mapa com os valores e os nomes
	 * 
	 * @param valores
	 * @return
	 * @throws ValidationException

	 * @throws br.com.ideiasages.exception.ValidationException 
	 */
	public boolean validar(Map<String, Object> valores) throws ValidationException, br.com.ideiasages.exception.ValidationException;

}
