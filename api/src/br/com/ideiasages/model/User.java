package br.com.ideiasages.model;

import br.com.ideiasages.authorization.Role;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.servlet.http.Cookie;
import java.io.Serializable;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class User implements Serializable {

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

	public boolean isValid() {
		return (this.cpf != null);
	}

	@Override
	public String toString() {
		return "User [cpf=" + cpf + ", email=" + email + ", name=" + name + ", password=" + password + ", phone=" + phone + ", role=" + role
				+ ", active=" + active + "]";
	}

	public boolean hasAccessToModule(long rolesAllowedSum) {
		//get the equivalent Prime Number according this specific role
		long rolePrimeNumber = Role.getPrimeNumberFromRole(this.role);

		return (rolesAllowedSum % rolePrimeNumber == 0);
	}

	public static User getByCookiesAttributes(Cookie[] cookies) {
		User userEntity = new User();

		if (cookies != null) {
			for (Cookie c : cookies) {
				switch (c.getName()) {
					case "userName":
						userEntity.setName(c.getValue());
						break;

					case "userRole":
						userEntity.setRole(c.getValue());
						break;

					case "userCpf":
						userEntity.setCpf(c.getValue());
						break;
				}
			}
		}

		return userEntity;
	}
}