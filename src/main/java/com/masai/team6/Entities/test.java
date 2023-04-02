package com.masai.team6.Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class test {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer testId;
	
	private String testName;
	
	private LocalDate date;
	
	private String userEmail;
	
	private Integer maxMarks;
	
	private Integer getMarks;
	
	private String attendence;
	
}
