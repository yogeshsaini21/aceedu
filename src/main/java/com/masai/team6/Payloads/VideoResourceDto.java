package com.masai.team6.Payloads;

import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResourceDto {

	private Integer videoId;
	
	private Integer lectureId;
	
	@Lob
    private byte[] data;
//	private String title;
//  private String description;
}
