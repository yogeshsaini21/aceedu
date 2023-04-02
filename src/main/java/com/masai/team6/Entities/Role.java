package com.masai.team6.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Role {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="role")
	private String name;
	
	
}
