package com.example.pottery.db1.controllers;

import com.example.pottery.db1.models.Category;
import com.example.pottery.db1.serviceImpl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryServiceImpl categoryService;
    @Autowired
    public CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
