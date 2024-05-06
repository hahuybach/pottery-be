package com.example.pottery.db1.service;

import com.example.pottery.db1.model.Category;
import com.example.pottery.db1.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public List<Category> getCategories(){
         return categoryRepository.findAll();
    }
}
