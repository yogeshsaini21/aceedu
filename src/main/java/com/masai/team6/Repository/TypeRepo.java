package com.masai.team6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.team6.Entities.Type;
import com.masai.team6.Entities.test;

public interface TypeRepo  extends JpaRepository<Type, Integer>{

	
}
