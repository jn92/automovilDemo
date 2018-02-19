package com.automovil.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.automovil.demo.base.ModelCar;

@Entity
@Table(name = "user")
public class User extends ModelCar {

	@Column(name = "user_name", nullable = false, length = 45)
	private String userName;
	
	@Column(name = "password", nullable = false, length = 45)
	private String password;
	
	@Column(name = "enabled", nullable = false, length = 45)
	private Boolean enabled;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
	private Set<UserRole> userRole = new HashSet<UserRole>();

	public String getUserName() {
		return userName;
	}
//	@JoinColumn(name = "optional_id", nullable = true, referencedColumnName = "id")

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public User() {

	}

	public User(String userName, String password, Boolean enabled) {
		super();
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String userName, String password, Boolean enabled, Set<UserRole> userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

}
