package com.masai.team6.Payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.masai.team6.Entities.Batch;
import com.masai.team6.Entities.Section;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters !!")
	private String name;

	@NotEmpty(message = "Email is required !!")
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty
	@Size(min = 8, max = 26, message = "password must be min of 8 chars and max of 26 chars !!")
	private String password;

	private int batchId;

	private int sectionId;
	
	private String parentEmail;

	private Set<RoleDto> roles = new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
