package br.com.ideiasages.model;

/**
 * Enum para os possíveis status de uma idéia.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public enum IdeaStatus {
	DRAFT("draft"),
	OPEN("open"),
	IN_ANALYSIS("in_analysis"),
	APPROVED("approved"),
	REJECTED("rejected");

	private final String name;

	private IdeaStatus(String s) {
		name = s;
	}
}
