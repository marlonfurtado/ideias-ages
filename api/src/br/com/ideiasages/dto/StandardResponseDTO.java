package br.com.ideiasages.dto;

/**
 * Classe responsável por enviar o resultado dos métodos dos controladores.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 08/06/2017
 */
public class StandardResponseDTO {
	private boolean success;
	private String message;

	public StandardResponseDTO(boolean s, String m) {
		success = s;
		message = m;
	}

	public StandardResponseDTO() {}

	public void setMessage(String m) {
		message = m;
	}

	public void setSuccess(boolean s) {
		success = s;
	}

	public String getMessage() {
		return message;
	}

	public boolean getSuccess() {
		return success;
	}
}