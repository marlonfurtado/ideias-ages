package br.com.ideiasages.model;

/**
 * Classe de modelo para o questionamento de uma idéia.
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
}