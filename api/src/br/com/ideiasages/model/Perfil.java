package br.com.ideiasages.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Perfil extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private User user;

	private String passwordToValidate;
	
	public Perfil() {
		super();
	}
	
	@JsonIgnore
	public String getPasswordToValidate(){
		return passwordToValidate;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setPasswordToValidate(String passwordToValidate) {
		this.passwordToValidate = passwordToValidate;
	}
	
	
	
}
