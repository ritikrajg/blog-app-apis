package com.codewithritik.blog.services;

import java.util.List;

import com.codewithritik.blog.payloads.CategoryDto;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryID);

    public void deleteCategory(Integer categoryId);

    public CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getCategories();
}
