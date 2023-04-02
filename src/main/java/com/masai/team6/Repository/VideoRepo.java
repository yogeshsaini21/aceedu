package com.masai.team6.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.team6.Entities.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {

	
}