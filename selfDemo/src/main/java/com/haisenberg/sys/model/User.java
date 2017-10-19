package com.haisenberg.sys.model;

import java.util.List;

public class User {
	private Integer userId;

	private String username;

	private String password;
	
	private String salt;//shiro盐值

	private Integer age;
	
	
	/**用户角色**/ 
	private List<Role> roles;  
	
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getCredentialsSalt() {  
        return username + salt;  
    } 

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", age=" + age
				+ ", roles=" + roles + "]";
	}



}