package com.masai.team6.Payloads;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name="Batch", uniqueConstraints = @UniqueConstraint( columnNames = {"batchName"}))
@NoArgsConstructor
@Getter
@Setter
public class BatchDto {

	private Integer id;

	@Column(unique=true)
	private String batchName;
	
	private String Status="Active";
}
