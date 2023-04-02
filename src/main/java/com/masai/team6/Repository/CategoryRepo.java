package com.masai.team6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.team6.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
