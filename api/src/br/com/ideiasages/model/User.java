package br.com.ideiasages.model;

import br.com.ideiasages.authorization.Role;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.servlet.http.Cookie;
import java.io.Serializable;

/**
 * Classe modelo para o acesso ao banco de dados.
 * 
 * @author Rodrigo Machado<rodrigo.domingos@acad.pucrs.br>.
 * @since 06/06/2017
 * 
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable {

	private static final long serialVersionUID = -789863172532826108L;
	private String cpf;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String role;
	private boolean active;

	/**
	 * Construtor vazio.
	 * 
	 **/
	public User() {}

	/**
	 * Construtor com o CPF.
	 * 
	 * @param cpf CPF de um usuário.
	 **/
	public User(String cpf) {
		this.cpf = cpf;
	}

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

	/**
	 * Valida se o usuário está ativo ou não.
	 * 
	 * @return boolean
	 **/
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Valida se o usuário é válido através do CPF.
	 * 
	 * @return boolean
	 **/
	public boolean isValid() {
		return (this.cpf != null);
	}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", name=" + name + ", password=" + password + ", phone=" + phone + ", role=" + role
				+ ", active=" + active + "]";
	}
}