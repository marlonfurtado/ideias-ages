package br.com.ideiasages.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable{

	private static final long serialVersionUID = -789863172532826108L;
	private String cpf;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String role;
	private boolean active;
	
	public User() {}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", name=" + name + ", password=" + password + ", phone=" + phone + ", role=" + role
				+ ", active=" + active + "]";
	}
	
	
}