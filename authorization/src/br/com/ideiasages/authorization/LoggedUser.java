package br.com.ideiasages.authorization;

import javax.servlet.http.Cookie;

public class LoggedUser {
	private String cpf;
	private String name;
	private String role;

	public LoggedUser() {}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean hasAccessToModule(long rolesAllowedSum) {
		//get the equivalent Prime Number according this specific role
		long rolePrimeNumber = Role.getPrimeNumberFromRole(this.role);

		return (rolesAllowedSum % rolePrimeNumber == 0);
	}

	public static LoggedUser getByCookiesAttributes(Cookie[] cookies) {
		LoggedUser userEntity = new LoggedUser();

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