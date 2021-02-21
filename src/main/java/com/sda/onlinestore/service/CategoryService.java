package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.AuthorDto;
import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.entity.Author;
import com.sda.onlinestore.entity.Category;
import com.sda.onlinestore.entity.UserAccount;
import com.sda.onlinestore.exception.NotFoundException;
import com.sda.onlinestore.repository.AuthorRepository;
import com.sda.onlinestore.repository.CategoryRepository;
import com.sda.onlinestore.repository.UserAccountRepository;
import com.sda.onlinestore.transformers.AuthorTransformer;
import com.sda.onlinestore.transformers.CategoryTransformer;
import com.sda.onlinestore.transformers.UserAccountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private CategoryTransformer categoryTransformer;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryDto> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtosList = new ArrayList<>();
        for(Category category:categories){
            CategoryDto categoryDto = categoryTransformer.transformReversed(category);
            categoryDtosList.add(categoryDto);
        }
        return categoryDtosList;
    }


    public Category findCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            System.out.println(category.toString());
            return category;
        } else {
            System.out.println("Category with ID " + id + " does not exist");
            throw  new NotFoundException("Category with ID " + id + " does not exist.");
        }
    }

    public List<Category> findCategoryByName(String name){
        return this.categoryRepository.findCategoryByName(name);
    }

    public void deleteCategoryById(Long id) {
        this.findCategoryById(id);
        categoryRepository.deleteById(id);
    }
}
