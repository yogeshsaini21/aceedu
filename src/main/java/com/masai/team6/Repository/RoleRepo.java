package com.masai.team6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.team6.Entities.Role;
 

public interface RoleRepo extends JpaRepository<Role, Integer> {

	
}
