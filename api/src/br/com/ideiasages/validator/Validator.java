package br.com.ideiasages.validator;


import java.util.Map;

import br.com.ideiasages.exception.ValidationException;

/**
 * Interface que servir� para efetuar diversas valida��es do sistema.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public interface Validator {

	/**
	 * Metodo efetivo de validacao que recebe o mapa com os valores e os nomes
	 * 
	 * @param valores Par�metro para informar o campo e o valor que ser�o validados.
	 * @return Verdadeiro em caso de sucesso na valida��o.
	 * @throws br.com.ideiasages.exception.ValidationException Exce��o de valida��es. 
	 */
	public boolean validar(Map<String, Object> valores) throws ValidationException;

}
