package com.masai.team6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.team6.Entities.otpsaver;

public interface otpsaverRepo extends JpaRepository<otpsaver, Integer> {
	otpsaver findByEmail(String email);
}
