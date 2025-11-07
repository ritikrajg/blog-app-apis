package com.codewithritik.blog.controllers;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithritik.blog.exceptions.ResourceNotFoundException;
import com.codewithritik.blog.payloads.ApiResponse;
import com.codewithritik.blog.payloads.CategoryDto;
import com.codewithritik.blog.repositories.CategoryRepo;
import com.codewithritik.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto create=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer categoryId,@RequestBody CategoryDto categoryDto){
        CategoryDto update=this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(update,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted sucessfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(this.categoryService.getCategories(),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

}
