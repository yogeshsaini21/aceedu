package com.masai.team6.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Table(name="Batch", uniqueConstraints = @UniqueConstraint( columnNames = {"batchName"}))
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Batch{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Integer batchId;
	
	@Column(unique=true)
	private String  batchName;
	
	private String Status="Active";

}

