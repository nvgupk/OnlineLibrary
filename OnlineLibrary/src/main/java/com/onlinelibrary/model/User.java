package com.onlinelibrary.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class User implements Serializable {
	private Long userId;
	private String name;
	private String email;
	private String password;
	private UserType userType;
	
	public User() {
	}
	
	@Id
	@Column(name = "customer_id", nullable = false, unique = true)
	@SequenceGenerator(name="customer_customer_id_seq",
		sequenceName="customer_customer_id_seq",
		allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator="customer_customer_id_seq")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_type_id", nullable = false)
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
