package com.masai.team6.Payloads;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchlectureDto {

	private String title;
	private Integer batchId;
	private Integer sectionId;
	private Integer typeId;
	private Integer createdBy;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime startTime;
	
	private Integer categoryId;
	
	private String Optional;
	private Integer week;
    private Integer day;
	
}
