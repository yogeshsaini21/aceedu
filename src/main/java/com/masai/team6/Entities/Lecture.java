package com.masai.team6.Entities;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.validator.constraints.URL;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer lectureID;
	
//	@NotBlank(message = "Title Must Not be Blank")
//	@NotNull(message = "Title Cannot be Null!")
//	@NotEmpty(message = "Title Must Not be Empty")
	private String title;
	
//	@NotBlank(message = "createdBy Must Not be Blank")
//	@NotNull(message = "createdBy Cannot be Null!")
//	@NotEmpty(message = "createdBy Must Not be Empty")
	private Integer createdBy;
	
//	@NotBlank(message = "updatedBy Must Not be Blank")
//	@NotNull(message = "updatedBy Cannot be Null!")
//	@NotEmpty(message = "updatedBy Must Not be Empty")
	private String updatedBy;
	
//	@NotBlank(message = "startTime Must Not be Blank")
//	@NotNull(message = "startTime Cannot be Null!")
//	@NotEmpty(message = "startTime Must Not be Empty") 
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime startTime;
	
//	@NotBlank(message = "concludeTime Must Not be Blank")
//	@NotNull(message = "concludeTime Cannot be Null!")
//	@NotEmpty(message = "concludeTime Must Not be Empty")
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
	private LocalDateTime concludeTime;
	

//	@NotBlank(message = "zoomLink Must Not be Blank")
//	@NotNull(message = "zoomLink Cannot be Null!")
//	@NotEmpty(message = "zoomLink Must Not be Empty")
	@URL
	private String zoomLink;
	
//	@NotBlank(message = "typeId Must Not be Blank")
//	@NotNull(message = "typeId Cannot be Null!")
//	@NotEmpty(message = "typeId Must Not be Empty")
	private Integer typeId;
	
//	@NotBlank(message = "batchId Must Not be Blank")
//	@NotNull(message = "batchId Cannot be Null!")
//	@NotEmpty(message = "batchId Must Not be Empty")
	private Integer batchId;
	
//	@NotBlank(message = "sectionId Must Not be Blank")
//	@NotNull(message = "sectionId Cannot be Null!")
//	@NotEmpty(message = "sectionId Must Not be Empty")
	private Integer sectionId;
	
//	@NotBlank(message = "categoryId Must Not be Blank")
//	@NotNull(message = "categoryId Cannot be Null!")
//	@NotEmpty(message = "categoryId Must Not be Empty")
	private Integer categoryId;
	
//	@NotBlank(message = "tagId Must Not be Blank")
//	@NotNull(message = "tagId Cannot be Null!")
//	@NotEmpty(message = "tagId Must Not be Empty")
	private Integer tagId;
	
//	@NotBlank(message = "videoId Must Not be Blank")
//	@NotNull(message = "videoId Cannot be Null!")
//	@NotEmpty(message = "videoId Must Not be Empty")
    private Integer videoId;

    private String optional;
    private String hideVideo;
    
    
    private Integer copyLectureFrom;
    
//  @NotBlank(message = "notes Must Not be Blank")
//	@NotNull(message = "notes Cannot be Null!")
//	@NotEmpty(message = "notes Must Not be Empty")
    @Column(length = 6000)
    private String notes;
    
    private Integer week;
    private Integer day;
  
//    @NotBlank(message = "status Must Not be Blank")
//	@NotNull(message = "status Cannot be Null!")
//	@NotEmpty(message = "status Must Not be Empty")
	private char status='A';
	
	
}

