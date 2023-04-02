package com.masai.team6.Payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TagDto {

	private Integer tagId;
	@NotBlank
	@Size(min = 3, message = "Min size of tag title is 3")
	private String tagTitle;
}
