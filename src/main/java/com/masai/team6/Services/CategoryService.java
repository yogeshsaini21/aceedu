package com.masai.team6.Services;

import java.util.List;

import com.masai.team6.Payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public void deleteCategory(Integer categoryId);

	public CategoryDto getCategory(Integer categoryId);

	List<CategoryDto> getCategories();
}
