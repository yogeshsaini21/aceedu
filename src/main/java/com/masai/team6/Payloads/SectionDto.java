package com.masai.team6.Payloads;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SectionDto {

	private Integer id;
	@NotBlank
	@Size(min = 3, message = "Min size of section title is 3")
	@Column(unique=true)
	private String sectionsName;
	
	private String Status="Active";
}
