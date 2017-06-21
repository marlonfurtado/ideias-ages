package br.com.ideiasages.dto;

import br.com.ideiasages.model.User;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserFormattedDTO implements Serializable {
	private static final long serialVersionUID = -789863172532826108L;
	private String cpf;
	private String email;
	private String name;
	private String phone;
	private String role;
	private boolean active;

	public UserFormattedDTO() {}

	public String getCpf() {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
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

	public static UserFormattedDTO getFromUser(User user) {
		UserFormattedDTO dto = new UserFormattedDTO();

		dto.setCpf(user.getCpf());
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setPhone(user.getPhone());
		dto.setRole(user.getRole());
		dto.setActive(user.isActive());

		return dto;
	}

	public static List<UserFormattedDTO> getFromUser(List<User> user) {
		List<UserFormattedDTO> list = new LinkedList<>();

		for (User u : user)
			list.add(getFromUser(u));

		return list;
	}
}