package com.codewithritik.blog.services.impl;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithritik.blog.exceptions.ResourceNotFoundException;
import com.codewithritik.blog.payloads.CategoryDto;
import com.codewithritik.blog.entities.*;
import com.codewithritik.blog.repositories.CategoryRepo;
import com.codewithritik.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if (categoryDto.getCategoryTitle() == null || categoryDto.getCategoryTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Category title is required");
        }
        
        Category category = new Category();
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        
        Category saved = this.categoryRepo.save(category);
        return mapToDto(saved);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=new ArrayList<>();
        for(Category category:categories){
            CategoryDto categoryDto=this.modelMapper.map(category,CategoryDto.class);
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category saved=this.categoryRepo.save(category);
        return this.modelMapper.map(saved, CategoryDto.class);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryTitle(category.getCategoryTitle());
        dto.setCategoryDescription(category.getCategoryDescription());
        return dto;
    }
}