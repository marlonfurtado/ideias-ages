package br.com.ideiasages.model;

import java.util.Calendar;

public class PasswordChangeRequest {

	private String requestId;
	private Calendar requestDateTime;
	private User user;
	
	public PasswordChangeRequest() {}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Calendar getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(Calendar requestDateTime) {
		this.requestDateTime = requestDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PasswordChangeRequest [requestId=" + requestId + ", requestDateTime=" + requestDateTime + ", user="
				+ user + "]";
	}

}