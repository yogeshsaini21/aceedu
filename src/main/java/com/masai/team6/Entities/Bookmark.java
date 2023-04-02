package com.masai.team6.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import com.univocity.parsers.annotations.Validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity 
@Table(name="Bookmarks",
uniqueConstraints = @UniqueConstraint( columnNames = {"userId","lectureId"})
 )
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookmarkId;
	
	private Integer userId;

	@Column(unique = true, nullable = false)
	private Integer  lectureId;
	

}
