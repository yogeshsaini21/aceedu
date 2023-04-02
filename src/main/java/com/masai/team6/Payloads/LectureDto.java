package com.masai.team6.Payloads;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LectureDto {

	private Integer lectureId;
	@NotEmpty
	@Size(min = 4, message = "Title must be min of 4 characters !!")
	private String title;
	@NotNull
	private Integer createdBy;
	private String updatedBy;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime startTime;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime concludeTime;

	@URL
	private String zoomLink;
	@NotNull
	private Integer typeId;
	@NotNull
	private Integer batchId;
	@NotNull
	private Integer categoryId;
	@NotNull
	private Integer tagId;
	private Integer videoId;
	@NotNull
	private Integer sectionId;

	@NotEmpty(message = "select a option from optional")
	private String optional;
	
	@NotEmpty(message = "select a option from hideVideo")
	private String hideVideo;
	
	private Integer copyLectureFrom;
	@Column(length = 6000)
	private String notes;
	private Integer week;
    private Integer day;

}
