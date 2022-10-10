package com.nagesh.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {

	private Integer id;
	
	@NotEmpty
	@Size(min=3, message="Name should be min of 3 characters.")
	private String name;
	
	@Email(message="Email not valid")
	private String email;
	
	@NotEmpty(message="password should not be blank")
	private String password;
	
	@NotEmpty(message="about should not be blank")
	private String about;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}

}
