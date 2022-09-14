package com.omkar.expanseManagerApi.entity;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserModel {
	
	@NotBlank(message = "Please enter name")
	private String name;
	
	@NotNull(message = "Please enter email")
	@Email(message = "Please enter valid email")
	@Column(unique= true)
	private String Email;
	
	@NotNull(message = "Please enter password")
	@Size(min = 5, message = "Password should be atleast 5 characters")
	private String password;
	private Long age= 0L;

}
