package com.sda.onlinestore.mvcController;

import com.sda.onlinestore.service.CategoryService;
import com.sda.onlinestore.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryMVCController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryMVCController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
}
