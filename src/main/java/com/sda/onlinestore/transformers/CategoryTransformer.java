package com.sda.onlinestore.transformers;

import com.sda.onlinestore.dto.CategoryDto;
import com.sda.onlinestore.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryTransformer {

    public Category transform (CategoryDto categoryDto){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        return category;
    }

    public CategoryDto transformReversed(Category category){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }
}
