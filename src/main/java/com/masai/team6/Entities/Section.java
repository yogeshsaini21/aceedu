package com.masai.team6.Entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Table(name="Section")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer sectionId;
	
	@Column(name="sections_name",unique=true)
	private String  sectionsName;
	
	private String Status="Active";
	

}


