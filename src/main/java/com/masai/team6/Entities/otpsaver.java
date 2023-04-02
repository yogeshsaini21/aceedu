package com.masai.team6.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class otpsaver {

	@Id
	@NotEmpty(message = "Email is required !!")
	@Email(message = "Email address is not valid")
	private String email;

	private int otp;

	@Column(name = "otp_requested_time")
	private LocalDateTime otpRequestedTime;

}
