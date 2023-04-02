package com.masai.team6.Payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TypeDto {

	private Integer typeId;
	@NotBlank
	@Size(min = 2, message = "Min size of type title is 2")
	private String typeTitle;
	
}
