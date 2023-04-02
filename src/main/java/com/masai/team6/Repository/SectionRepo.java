package com.masai.team6.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.masai.team6.Entities.Section;


@Repository
public interface SectionRepo extends JpaRepository<Section, Integer> {


	
}
