package br.com.ideiasages.model;

import java.io.Serializable;

public class PasswordChangeWrapper implements Serializable {

	private static final long serialVersionUID = -655261086317253125L;
	private String token;
	private String password;
	
	public PasswordChangeWrapper() {}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}