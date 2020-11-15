package com.icecream.IceCream.dto;

public class SigninDTO {
	private String username;
	private String password;

	public SigninDTO() {
	}

	public SigninDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
		this.password = password;
	}

}
