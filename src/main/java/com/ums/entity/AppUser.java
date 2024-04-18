package com.ums.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Column(name = "name", nullable = false,  length = 50)
    private String name;

    @Column(name = "username", nullable = false, unique = true, length = 150)
    private String username;

    @Column(name = "email_id", nullable = false, unique = true, length = 150)
    private String emailId;
	@JsonIgnore
    @Column(name = "password", nullable = false, length = 150)
    private String password;
	@JsonIgnore
	@Column(name = "user_role", nullable = false, length = 20)
	private String userRole;

}