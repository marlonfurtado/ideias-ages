package br.com.ideiasages.validator;


import java.util.Map;

import br.com.ideiasages.exception.ValidationException;

/**
 * Interface que servirá para efetuar diversas validações do sistema.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public interface Validator {

	/**
	 * Metodo efetivo de validacao que recebe o mapa com os valores e os nomes
	 * 
	 * @param valores Parâmetro para informar o campo e o valor que serão validados.
	 * @return Verdadeiro em caso de sucesso na validação.
	 * @throws br.com.ideiasages.exception.ValidationException Exceção de validações. 
	 */
	public boolean validar(Map<String, Object> valores) throws ValidationException;

}
