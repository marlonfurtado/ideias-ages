package br.com.ideiasages.model;

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
