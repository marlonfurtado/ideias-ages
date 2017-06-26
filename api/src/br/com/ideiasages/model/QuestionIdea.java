package br.com.ideiasages.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Classe de modelo para o questionamento de uma id�ia.
 *
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 19/06/2017
 *
 **/
public class QuestionIdea {
	private int id;
	private String question;
	private User analyst;
	private String answer;
	@JsonIgnore
	private Idea idea;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public User getAnalyst() {
		return analyst;
	}
	
	public void setAnalyst(User analyst) {
		this.analyst = analyst;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}
	
}