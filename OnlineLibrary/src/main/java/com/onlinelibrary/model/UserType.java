package com.onlinelibrary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_type")
public class UserType implements Serializable {
	private Long userTypeId;
	private String type;
	private List<User> users = new ArrayList<User>();
	
	public UserType() {
	}
	
	@Id
	@Column(name = "user_type_id", unique = true, nullable = false )
	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	@Column(name = "type", nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userType")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
