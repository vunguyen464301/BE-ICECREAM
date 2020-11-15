package com.icecream.IceCream.dto;

import java.sql.Date;
import java.util.List;

public class UpdateAccountDTO {
	private long id;
	private String username;
	private String fullname;
	private String gender;
	private String email;
	private List<Long> roleId;
	private long statusId;
	private Date created_date;

	public UpdateAccountDTO() {
	}

	public UpdateAccountDTO(long id, String username, String fullname, String gender, String email, List<Long> roleId,
			long statusId, Date created_date) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.gender = gender;
		this.email = email;
		this.roleId = roleId;
		this.statusId = statusId;
		this.created_date = created_date;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the roleId
	 */
	public List<Long> getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the statusId
	 */
	public long getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

}
