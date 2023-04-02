package com.masai.team6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.team6.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	
//	@Query("SELECT s from user s where s.batchId =:batchId")
//	List<User> getUserByBatchId(@Param("batchId") Integer batchId);
	
	List<User> findByBatchId(int batchId);
 
	
	
} 
