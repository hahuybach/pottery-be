package com.example.pottery.db1.serviceImpl;

import com.example.pottery.db1.models.Category;
import com.example.pottery.db1.repositories.CategoryRepository;
import com.example.pottery.db1.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    public List<Category> getCategories(){
         return categoryRepository.findAll();
    }
}
