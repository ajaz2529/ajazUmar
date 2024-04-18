package com.ums.payload;

import lombok.Data;

@Data
public class UserDTO {
    private long id ;
    private String name;

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", emailId='" + emailId + '\'' +
				", password='" + password + '\'' +
				", userRole='" + userRole + '\'' +
				'}';
	}

	private String username;
    private String emailId;
    private String password;
	private String userRole;

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
