package com.masai.team6.Payloads;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LectureResponseDto {

	
	private Integer lectureId;
	private String title;
	private String createdBy;
	private String updatedBy;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime startTime;
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime concludeTime;
	

	@URL
	private String zoomLink;
	private String type;
	private String batch;
	private String category;
	private String tag;
    private String section;
    

    private String optional;
    private String hideVideo;
    @Lob
    private Integer videoId;
    private Integer copyLectureFrom;
    @Column(length = 6000)
    private String notes;
    private Integer week;
    private Integer day;
	
}
