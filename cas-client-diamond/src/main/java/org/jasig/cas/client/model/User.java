package org.jasig.cas.client.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 7000413800880277969L;

	protected Integer id;
	protected String username;
	protected String realname;
	protected Integer isadmin;
	protected String mobile;
	protected String email;
	protected String roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"username\":\"" + username + "\",\"realname\":\"" + realname
				+ "\",\"isadmin\":\"" + isadmin + "\",\"mobile\":\"" + mobile + "\",\"email\":\"" + email
				+ "\",\"roles\":\"" + roles + "\"}";
	}

}
