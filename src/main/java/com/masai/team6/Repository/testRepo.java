package com.masai.team6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.team6.Entities.test;

public interface testRepo extends JpaRepository<test, Integer> {

	@Query("SELECT  s from test s where s.userEmail =:useremail ")
	List<test> getListOfTest(@Param("useremail") String useremail);
	
	
	
	
}
