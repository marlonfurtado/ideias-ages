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
	UNDER_ANALYSIS("in_analysis"),
	APPROVED("approved"),
	REJECTED("rejected");

	private final String name;

    private IdeaStatus(final String s) {
        this.name = s;
    }

    public String getName(){
    	return name;
    }

}
