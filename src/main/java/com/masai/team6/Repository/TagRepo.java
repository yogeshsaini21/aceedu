package com.masai.team6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.team6.Entities.Tag;

public interface TagRepo extends JpaRepository<Tag, Integer> {

}
