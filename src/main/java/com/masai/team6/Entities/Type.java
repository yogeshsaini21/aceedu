package com.masai.team6.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="types")
@NoArgsConstructor
@Getter
@Setter
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeId;
	
	@Column(length = 100 , nullable = false)
	private String typeTitle;
	
	
}
