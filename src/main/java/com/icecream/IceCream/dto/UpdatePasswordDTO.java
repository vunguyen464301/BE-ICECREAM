package com.icecream.IceCream.dto;

public class UpdatePasswordDTO {

	private String username;
	private String passwordOld;
	private String passwordNew;

	public UpdatePasswordDTO() {
	}

	public UpdatePasswordDTO(String username, String passwordOld, String passwordNew) {
		super();
		this.username = username;
		this.passwordOld = passwordOld;
		this.passwordNew = passwordNew;
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
	 * @return the passwordOld
	 */
	public String getPasswordOld() {
		return passwordOld;
	}

	/**
	 * @param passwordOld the passwordOld to set
	 */
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

	/**
	 * @return the passwordNew
	 */
	public String getPasswordNew() {
		return passwordNew;
	}

	/**
	 * @param passwordNew the passwordNew to set
	 */
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}

}
