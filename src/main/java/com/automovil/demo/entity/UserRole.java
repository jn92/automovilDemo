package com.automovil.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.automovil.demo.base.ModelCar;

@Entity
@Table(name = "user_role", uniqueConstraints = @UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole extends ModelCar {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false)
	private User user;

	@Column(name = "role", nullable = false, length = 45)
	private String role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserRole() {

	}

	public UserRole(User user, String role) {
		super();
		this.user = user;
		this.role = role;
	}

}
